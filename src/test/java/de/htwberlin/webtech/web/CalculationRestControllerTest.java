package de.htwberlin.webtech.web;

import static org.junit.jupiter.api.Assertions.*;
import de.htwberlin.webtech.service.CalculationService;
import de.htwberlin.webtech.web.api.Calculation;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CalculationRestController.class)
class CalculationRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CalculationService calculationService;

    @Test
    @DisplayName("should return found calculations from calculation service")
    void should_return_found_calculation_from_calculation_service() throws Exception {
        BigDecimal initialInvestOne = BigDecimal.valueOf(10);
        BigDecimal initialInvestTwo = BigDecimal.valueOf(20);

        // given
        var calculations = List.of(
                new Calculation(1, initialInvestOne, 10, initialInvestOne, "daily", 10),
                new Calculation(2, initialInvestTwo, 20, initialInvestTwo, "monthly", 20)
        );
        doReturn(calculations).when(calculationService).findAll();

        // when
        mockMvc.perform(get("/api/v1/calculations"))
                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].initialInvestment").value(10))
                .andExpect(jsonPath("$[0].yearsToAccumulate").value(10))
                .andExpect(jsonPath("$[0].additionalContribution").value(10))
                .andExpect(jsonPath("$[0].compoundFrequency").value("daily"))
                .andExpect(jsonPath("$[0].interestRate").value("10"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].initialInvestment").value(20))
                .andExpect(jsonPath("$[1].yearsToAccumulate").value(20))
                .andExpect(jsonPath("$[1].additionalContribution").value(20))
                .andExpect(jsonPath("$[1].compoundFrequency").value("monthly"))
                .andExpect(jsonPath("$[1].interestRate").value("20"));
    }

    @Test
    @DisplayName("should return 404 if calculation is not found")
    void should_return_404_if_calculation_is_not_found() throws Exception {
        // given
        doReturn(null).when(calculationService).findById(anyLong());

        // when
        mockMvc.perform(get("/api/v1/calculations/123"))
                // then
                .andExpect(status().isNotFound());
    }
    @Test
    @DisplayName("should return 201 http status and Location header when creating a calculation")
    void should_return_201_http_status_and_location_header_when_creating_a_calculation() throws Exception {
        // given
        BigDecimal initialInvestOne = BigDecimal.valueOf(10);
        String calculationToCreateAsJson = "{\"initialInvestment\": "+ initialInvestOne +", \"yearsToAccumulate\": 10, \"additionalContribution\": "+ initialInvestOne +", \"compoundFrequency\": \"daily\", \"interestRate\":10}";
        var calculation = new Calculation(192, initialInvestOne, 10, initialInvestOne, "daily", 10);
        doReturn(calculation).when(calculationService).create(any());

        // when
        mockMvc.perform(
                        post("/api/v1/calculations")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(calculationToCreateAsJson)
                )
                // then
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(header().string("Location", Matchers.equalTo(("/api/v1/calculations" + calculation.getId()))));
//            .andExpect(header().string("Location", Matchers.containsString(Long.toString(person.getId()))));

    }
    @Test
    @DisplayName("should validate create calculation request")
    void should_validate_create_validation_request() throws Exception {
        // given
        BigDecimal initialInvestOne = BigDecimal.valueOf(10);
        String calculationToCreateAsJson = "{\"initialInvestment\": "+ initialInvestOne +", \"yearsToAccumulate\":10, \"additionalContribution\": 'causeToFail', \"compoundFrequency\": \"daily\", \"interestRate\":10}";
//        String calculationToCreateAsJson = "{\"initialInvestment\": "+ initialInvestOne +", \"yearsToAccumulate\": 10, \"additionalContribution\": "+ initialInvestOne +", \"compoundFrequency\": \"daily\", \"interestRate\":10}";

        // when
        mockMvc.perform(
                        post("/api/v1/calculations")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(calculationToCreateAsJson)
                )
                // then
                .andExpect(status().isBadRequest());
    }

}
