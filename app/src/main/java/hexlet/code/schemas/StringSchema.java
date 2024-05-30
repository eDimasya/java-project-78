package hexlet.code.schemas;

import org.apache.commons.lang3.StringUtils;

public class StringSchema {

    public StringSchema() {

    }

    private String containsCondition = null;
    private boolean requiredCondition = false;
    private int minLengthCondition = -1;
    private int maxLengthCondition = -1;
    private String validatedCondition = null;

    public StringSchema required() {
        this.requiredCondition = true;
        return this;
    }

    private boolean checkRequired() {
        if (requiredCondition) {
            return (StringUtils.isNoneEmpty(validatedCondition));
        } else {
            return true;
        }
    }

    public StringSchema minLength(int minLength) {
        this.minLengthCondition = minLength;
        return this;
    }

    private boolean checkMinLength() {
        return StringUtils.length(validatedCondition) >= minLengthCondition;
    }

    public StringSchema maxLength(int maxLength) {
        this.maxLengthCondition = maxLength;
        return this;
    }

    private boolean checkMaxLength() {
        return maxLengthCondition < 0 || StringUtils.length(validatedCondition) <= maxLengthCondition;
    }

    public StringSchema contains(String contains) {
        this.containsCondition = contains;
        return this;
    }

    private boolean checkContains() {
        if (StringUtils.isEmpty(containsCondition)) {
            return true;
        } else {
            return StringUtils.contains(validatedCondition, containsCondition);
        }
    }

    public boolean isValid(String validated) {
        this.validatedCondition = validated;
        return checkRequired() & checkMinLength() & checkMaxLength() & checkContains();
    }

}
