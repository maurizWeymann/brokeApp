package de.htwberlin.webtech.service;

import de.htwberlin.webtech.persistence.CalculationEntity;
import de.htwberlin.webtech.persistence.CalculationRepository;
import de.htwberlin.webtech.web.api.Calculation;
import de.htwberlin.webtech.web.api.CalculationManipulationRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CalculationService {

    private final CalculationRepository calculationRepository;

    public CalculationService(CalculationRepository calculationRepository) {
        this.calculationRepository = calculationRepository;
    }

    public List<Calculation> findAll(){
        List<CalculationEntity> calculations = calculationRepository.findAll();
        return calculations.stream()
                .map(this::transformEntity)
                .collect(Collectors.toList());
    }

    public Calculation findById(Long id){
        var calculationEntity = calculationRepository.findById(id);
        return calculationEntity.map(this::transformEntity).orElse(null);
    }

    public Calculation create(CalculationManipulationRequest request){
        var calculationEntity = new CalculationEntity(
                request.getInitialInvestment(),
                request.getYearsToAccumulate(),
                request.getAdditionalContribution(),
                request.getCompoundFrequency(),
                request.getInterestRate()
        );
        calculationRepository.save(calculationEntity);
        return transformEntity(calculationEntity);
    }

    public Calculation update(Long id, CalculationManipulationRequest request){
        var calculationEntityOptional = calculationRepository.findById(id);
        if(calculationEntityOptional.isEmpty()){
            return null;
        }
        var calculationEntity = calculationEntityOptional.get();
        calculationEntity.setInitialInvestment(request.getInitialInvestment());
        calculationEntity.setYearsToAccumulate(request.getYearsToAccumulate());
        calculationEntity.setAdditionalContribution(request.getAdditionalContribution());
        calculationEntity.setCompoundFrequency(request.getCompoundFrequency());
        calculationEntity.setInterestRate(request.getInterestRate());
//        calculationEntity.setCompoundFrequency(request.getCompoundFrequency());
        calculationEntity = calculationRepository.save(calculationEntity);

        return transformEntity(calculationEntity);
    }

    public boolean deleteById(Long id){
        if(!calculationRepository.existsById(id)){
            return false;
        }
        calculationRepository.deleteById(id);
        return true;
    }

    private Calculation transformEntity(CalculationEntity calculationEntity) {
    return new Calculation(
            calculationEntity.getId(),
            calculationEntity.getInitialInvestment(),
            calculationEntity.getYearsToAccumulate(),
            calculationEntity.getAdditionalContribution(),
            calculationEntity.getCompoundFrequency(),
            calculationEntity.getInterestRate()
    );

    }

}
