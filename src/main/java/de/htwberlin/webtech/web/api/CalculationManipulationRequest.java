package de.htwberlin.webtech.web.api;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

public class CalculationManipulationRequest {

    @NotNull(message = "Please insert an amount to start with")
    private BigDecimal initialInvestment;

    @NotNull(message = "Please enter a timespan to calculate")
    private Integer yearsToAccumulate;

    @NotNull(message = "Please enter an amount or 0 for additional contributions")
    private BigDecimal additionalContribution;

    @NotNull
    @Pattern(
            regexp = "daily|monthly|yearly",
            message = "Please choose one of the options"
            )
    private String compoundFrequency;//hier evt. Array mit Auswahl für Monat, Jahr etc.

    @NotNull(message = "Please enter an percentage to calculate")
    private int interestRate;

    public CalculationManipulationRequest(BigDecimal initialInvestment, Integer yearsToAccumulate, BigDecimal monthlyContribution, String compoundFrequency, int interestRate) {
        this.initialInvestment = initialInvestment;
        this.yearsToAccumulate = yearsToAccumulate;
        this.additionalContribution = monthlyContribution;
        this.compoundFrequency = compoundFrequency;
        this.interestRate = interestRate;
    }

    public CalculationManipulationRequest() {}

    public BigDecimal getInitialInvestment() {
        return initialInvestment;
    }

    public void setInitialInvestment(BigDecimal initialInvestment) {
        this.initialInvestment = initialInvestment;
    }

    public Integer getYearsToAccumulate() {
        return yearsToAccumulate;
    }

    public void setYearsToAccumulate(Integer yearsToAccumulate) {
        this.yearsToAccumulate = yearsToAccumulate;
    }

    public BigDecimal getAdditionalContribution() {
        return additionalContribution;
    }

    public void setAdditionalContribution(BigDecimal additionalContribution) {
        this.additionalContribution = additionalContribution;
    }

    public String getCompoundFrequency() {
        return compoundFrequency;
    }

    public void setCompoundFrequency(String compoundFrequency) {
        this.compoundFrequency = compoundFrequency;
    }

    public int getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(int interestRate) {
        this.interestRate = interestRate;
    }
}
