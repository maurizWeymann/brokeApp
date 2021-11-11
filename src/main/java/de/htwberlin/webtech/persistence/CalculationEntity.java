package de.htwberlin.webtech.persistence;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity (name="calculations")
public class CalculationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "initial_investment", nullable = true)
    private BigDecimal initialInvestment;

    @Column(name = "year_to_accumulate", nullable = true)
    private Integer yearsToAccumulate;

    @Column(name = "adiitonal_contribution", nullable = true)
    private BigDecimal additionalContribution;

    @Column(name = "compound_frequency", nullable = true)
    private String compoundFrequency;//hier evt. Array mit Auswahl für Monat, Jahr etc.

    @Column(name = "interest_rate")
    private int interestRate;

    public CalculationEntity(BigDecimal initialInvestment, Integer yearsToAccumulate, BigDecimal monthlyContribution, String compoundFrequency, int interestRate) {
        this.initialInvestment = initialInvestment;
        this.yearsToAccumulate = yearsToAccumulate;
        this.additionalContribution = monthlyContribution;
        this.compoundFrequency = compoundFrequency;
        this.interestRate = interestRate;
    }

    protected CalculationEntity() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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
