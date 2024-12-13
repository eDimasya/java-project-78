package hexlet.code.schemas;

import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

@NoArgsConstructor
public class StringSchema extends BaseSchema<String> {

    private String containsCondition = null;
    private Integer minLengthCondition = null;
    private Integer maxLengthCondition = null;

    public StringSchema minLength(int minLength) {
        if (minLength >= 0) {
            this.minLengthCondition = minLength;
        }
        return this;
    }

    private boolean checkMinLength() {
        if (Objects.isNull(minLengthCondition)) {
            return true;
        } else {
            return StringUtils.length(obj) >= minLengthCondition;
        }
    }

    public StringSchema maxLength(int maxLength) {
        if (maxLength > 0) {
            this.maxLengthCondition = maxLength;
        }
        return this;
    }

    private boolean checkMaxLength() {
        if (Objects.isNull(maxLengthCondition)) {
            return true;
        } else {
            return StringUtils.length(obj) <= maxLengthCondition;
        }
    }

    private boolean checkLength() {
        return checkMinLength() && checkMaxLength();
    }

    public StringSchema contains(String contains) {
        this.containsCondition = contains;
        return this;
    }

    private boolean checkContains() {
        if (StringUtils.isEmpty(containsCondition)) {
            return true;
        } else {
            return StringUtils.contains(obj, containsCondition);
        }
    }

    public boolean isValid(String valid) {
        return super.isValid(valid) && (!super.required || StringUtils.isNotEmpty(valid))
                && checkLength() && checkContains();
    }
}
