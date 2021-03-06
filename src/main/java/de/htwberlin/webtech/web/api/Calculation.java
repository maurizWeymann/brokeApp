package de.htwberlin.webtech.web.api;

import java.math.BigDecimal;

public class Calculation {
    private long id;
    private BigDecimal initialInvestment;
    private Integer yearsToAccumulate;
    private BigDecimal additionalContribution;
    private String compoundFrequency;//hier evt. Array mit Auswahl für Monat, Jahr etc.
    private int interestRate;

    public Calculation(long id, BigDecimal initialInvestment, Integer yearsToAccumulate, BigDecimal monthlyContribution, String compoundFrequency, int interestRate) {
        this.id=id;
        this.initialInvestment = initialInvestment;
        this.yearsToAccumulate = yearsToAccumulate;
        this.additionalContribution = monthlyContribution;
        this.compoundFrequency = compoundFrequency;
        this.interestRate = interestRate;
    }
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


