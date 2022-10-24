package io.github.aasaru.drools.intermediate.section04.internal;

public class VerdictRuleData {

    private int ruleNumber;

    private int minAge;
    private int maxAge;
    private Boolean isUnemployed;
    private Boolean hasBooking;
    private double percentageToInterview;

    public int getRuleNumber() {
        return ruleNumber;
    }

    public int getMinAge() {
        return minAge;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public Boolean getUnemployed() {
        return isUnemployed;
    }

    public Boolean getHasBooking() {
        return hasBooking;
    }

    public double getPercentageToInterview() {
        return percentageToInterview;
    }


    public static VerdictRuleData.VerdictRuleDataBuilder newBuilder() {
        return new VerdictRuleData.VerdictRuleDataBuilder();
    }

    public static class VerdictRuleDataBuilder {
        private int ruleNumber;
        private int minAge;
        private int maxAge;
        private Boolean isUnemployed;
        private Boolean hasBooking;
        private double percentageToInterview;

        public VerdictRuleData.VerdictRuleDataBuilder withRuleNumber(int ruleNumber) {
            this.ruleNumber = ruleNumber;
            return this;
        }

        public VerdictRuleData.VerdictRuleDataBuilder withMinAge(int minAge) {
            this.minAge = minAge;
            return this;
        }

        public VerdictRuleData.VerdictRuleDataBuilder withMaxAge(int maxAge) {
            this.maxAge = maxAge;
            return this;
        }

        public VerdictRuleData.VerdictRuleDataBuilder withIsUnemployed(Boolean isUnemployed) {
            this.isUnemployed = isUnemployed;
            return this;
        }

        public VerdictRuleData.VerdictRuleDataBuilder withHasBooking(Boolean hasBooking) {
            this.hasBooking = hasBooking;
            return this;
        }

        public VerdictRuleData.VerdictRuleDataBuilder withPercentageToInterview(double percentageToInterview) {
            this.percentageToInterview = percentageToInterview;
            return this;
        }

        public VerdictRuleData build() {
            VerdictRuleData verdictRuleData = new VerdictRuleData();
            verdictRuleData.ruleNumber = this.ruleNumber;
            verdictRuleData.minAge = this.minAge;
            verdictRuleData.maxAge = this.maxAge;
            verdictRuleData.isUnemployed = this.isUnemployed;
            verdictRuleData.hasBooking = this.hasBooking;
            verdictRuleData.percentageToInterview = this.percentageToInterview;

            return verdictRuleData;
        }

    }
}
