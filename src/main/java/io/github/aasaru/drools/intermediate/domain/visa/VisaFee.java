package io.github.aasaru.drools.intermediate.domain.visa;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class VisaFee {

    private Integer applicationId;
    private Integer feeAmount;

    public VisaFee(Integer applicationId, Integer feeAmount) {
        this.applicationId = applicationId;
        this.feeAmount = feeAmount;
    }

    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }

    public Integer getFeeAmount() {
        return feeAmount;
    }

    public void setFeeAmount(Integer feeAmount) {
        this.feeAmount = feeAmount;
    }

    @Override
    public String toString() {
        return "VisaFee(applicationId:" + applicationId + ", feeAmount:" + feeAmount + ")";
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

}
