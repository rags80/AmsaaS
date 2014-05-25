package com.ams.billingandpayment.application.api.dto;

public class ServicePriceCommand {
    private String srvcPlanName;
    private String srvcCode;
    private String srvcPriceCategory;
    private Double srvcPriceAmountValue;
    private String srvcPriceAmountCurrency;
    private String srvcPriceUnitOfMeasure;

    public String getSrvcCode() {
        return this.srvcCode;
    }

    public void setSrvcCode(String srvcCode) {
        this.srvcCode = srvcCode;
    }

    public String getSrvcPlanName() {
        return this.srvcPlanName;
    }

    public void setSrvcPlanName(String srvcPlanName) {
        this.srvcPlanName = srvcPlanName;
    }

    public String getSrvcPriceAmountCurrency() {
        return this.srvcPriceAmountCurrency;
    }

    public void setSrvcPriceAmountCurrency(String srvcPriceAmountCurrency) {
        this.srvcPriceAmountCurrency = srvcPriceAmountCurrency;
    }

    public Double getSrvcPriceAmountValue() {
        return this.srvcPriceAmountValue;
    }

    public void setSrvcPriceAmountValue(Double srvcPriceAmountValue) {
        this.srvcPriceAmountValue = srvcPriceAmountValue;
    }

    public String getSrvcPriceCategory() {
        return this.srvcPriceCategory;
    }

    public void setSrvcPriceCategory(String srvcPriceCategory) {
        this.srvcPriceCategory = srvcPriceCategory;
    }

    public String getSrvcPriceUnitOfMeasure() {
        return this.srvcPriceUnitOfMeasure;
    }

    public void setSrvcPriceUnitOfMeasure(String srvcPriceUnitOfMeasure) {
        this.srvcPriceUnitOfMeasure = srvcPriceUnitOfMeasure;
    }

}
