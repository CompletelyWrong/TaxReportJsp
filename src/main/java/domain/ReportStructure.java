package domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "root")
@XmlAccessorType(XmlAccessType.FIELD)
public class ReportStructure {
    private String fullName;
    private String type;
    private String innCode;
    private String periodStart;
    private String periodEnd;
    private String incomeCode;
    private String incomeValue;
    private String outcomeCode;
    private String outcomeValue;
    private String percentCode;
    private String percentValue;
    private String clearCode;
    private String clearValue;


    public ReportStructure(Builder builder) {
        this.fullName = builder.fullName;
        this.type = builder.type;
        this.innCode = builder.innCode;
        this.periodStart = builder.periodStart;
        this.periodEnd = builder.periodEnd;
        this.incomeCode = builder.incomeCode;
        this.incomeValue = builder.incomeValue;
        this.outcomeCode = builder.outcomeCode;
        this.outcomeValue = builder.outcomeValue;
        this.percentCode = builder.percentCode;
        this.percentValue = builder.percentValue;
        this.clearCode = builder.clearCode;
        this.clearValue = builder.clearValue;
    }

    private ReportStructure(){
    }

    public String getFullName() {
        return fullName;
    }

    public String getType() {
        return type;
    }

    public String getInnCode() {
        return innCode;
    }

    public String getPeriodStart() {
        return periodStart;
    }

    public String getPeriodEnd() {
        return periodEnd;
    }

    public String getIncomeCode() {
        return incomeCode;
    }

    public String getIncomeValue() {
        return incomeValue;
    }

    public String getOutcomeCode() {
        return outcomeCode;
    }

    public String getOutcomeValue() {
        return outcomeValue;
    }

    public String getPercentCode() {
        return percentCode;
    }

    public String getPercentValue() {
        return percentValue;
    }

    public String getClearCode() {
        return clearCode;
    }

    public String getClearValue() {
        return clearValue;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private String fullName;
        private String type;
        private String innCode;
        private String periodStart;
        private String periodEnd;
        private String incomeCode;
        private String incomeValue;
        private String outcomeCode;
        private String outcomeValue;
        private String percentCode;
        private String percentValue;
        private String clearCode;
        private String clearValue;

        private Builder() {
        }

        public ReportStructure build(){
            return new ReportStructure(this);
        }

        public Builder setFullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public Builder setType(String type) {
            this.type = type;
            return this;
        }

        public Builder setInnCode(String innCode) {
            this.innCode = innCode;
            return this;
        }

        public Builder setPeriodStart(String periodStart) {
            this.periodStart = periodStart;
            return this;
        }

        public Builder setPeriodEnd(String periodEnd) {
            this.periodEnd = periodEnd;
            return this;
        }

        public Builder setIncomeCode(String incomeCode) {
            this.incomeCode = incomeCode;
            return this;
        }

        public Builder setIncomeValue(String incomeValue) {
            this.incomeValue = incomeValue;
            return this;
        }

        public Builder setOutcomeCode(String outcomeCode) {
            this.outcomeCode = outcomeCode;
            return this;
        }

        public Builder setOutcomeValue(String outcomeValue) {
            this.outcomeValue = outcomeValue;
            return this;
        }

        public Builder setPercentCode(String percentCode) {
            this.percentCode = percentCode;
            return this;
        }

        public Builder setPercentValue(String percentValue) {
            this.percentValue = percentValue;
            return this;
        }

        public Builder setClearCode(String clearCode) {
            this.clearCode = clearCode;
            return this;
        }

        public Builder setClearValue(String clearValue) {
            this.clearValue = clearValue;
            return this;
        }
    }

    @Override
    public String toString() {
        return "ReportStructure{" +
                "fullName='" + fullName + '\'' +
                ", type='" + type + '\'' +
                ", innCode='" + innCode + '\'' +
                ", periodStart='" + periodStart + '\'' +
                ", periodEnd='" + periodEnd + '\'' +
                ", incomeCode='" + incomeCode + '\'' +
                ", incomeValue='" + incomeValue + '\'' +
                ", outcomeCode='" + outcomeCode + '\'' +
                ", outcomeValue='" + outcomeValue + '\'' +
                ", percentCode='" + percentCode + '\'' +
                ", percentValue='" + percentValue + '\'' +
                ", clearCode='" + clearCode + '\'' +
                ", clearValue='" + clearValue + '\'' +
                '}';
    }
}
