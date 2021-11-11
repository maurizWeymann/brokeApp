package de.htwberlin.webtech.web;

import de.htwberlin.webtech.service.CalculationService;
import de.htwberlin.webtech.web.api.Calculation;
import de.htwberlin.webtech.web.api.CalculationManipulationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class CalculationRestController {

    private final CalculationService calculationService;

    public CalculationRestController(CalculationService calculationService) {
        this.calculationService = calculationService;
    }

    @GetMapping(path = "/api/v1/calculations")
    public ResponseEntity<List<Calculation>> fetchCalculations(){
        return ResponseEntity.ok(calculationService.findAll());
    }

    @GetMapping(path = "/api/v1/calculations/{id}")
    public ResponseEntity<Calculation> fetchCalculationById(@PathVariable Long id){
        var calculation = calculationService.findById(id);
        return calculation != null? ResponseEntity.ok(calculation) : ResponseEntity.notFound().build();
    }

    @PostMapping(path = "/api/v1/calculations")
    public ResponseEntity<Void> createCalculation(@RequestBody CalculationManipulationRequest request) throws URISyntaxException{
        var calculation = calculationService.create(request);
        URI uri = new URI("/api/v1/calculations" + calculation.getId());
        return ResponseEntity.created(uri).build();
    }
    @PutMapping(path = "/api/v1/calculations/{id}")
    public ResponseEntity<Calculation> updateCalculation(@PathVariable Long id, @RequestBody CalculationManipulationRequest request){
        var calculation = calculationService.update(id, request);
        return calculation != null? ResponseEntity.ok(calculation) : ResponseEntity.notFound().build();
    }

    @DeleteMapping(path = "/api/v1/calculations/{id}")
    public ResponseEntity<Void> deleteCalculation(@PathVariable Long id){
        boolean successful = calculationService.deleteById(id);
        return successful? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

}
