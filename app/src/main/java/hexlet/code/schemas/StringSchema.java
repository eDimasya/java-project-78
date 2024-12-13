package hexlet.code.schemas;

import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

@NoArgsConstructor
public class StringSchema extends BaseSchema<String> {

    private String contains = null;
    private Integer minLength = null;
    private Integer maxLength = null;

    public StringSchema minLength(int minLength) {
        if (minLength >= 0) {
            this.minLength = minLength;
        }
        return this;
    }

    private boolean checkMinLength() {
        if (Objects.isNull(minLength)) {
            return true;
        } else {
            return StringUtils.length(obj) >= minLength;
        }
    }

    public StringSchema maxLength(int maxLength) {
        if (maxLength > 0) {
            this.maxLength = maxLength;
        }
        return this;
    }

    private boolean checkMaxLength() {
        if (Objects.isNull(maxLength)) {
            return true;
        } else {
            return StringUtils.length(obj) <= maxLength;
        }
    }

    private boolean checkLength() {
        return checkMinLength() && checkMaxLength();
    }

    public StringSchema contains(String contains) {
        this.contains = contains;
        return this;
    }

    private boolean checkContains() {
        if (StringUtils.isEmpty(contains)) {
            return true;
        } else {
            return StringUtils.contains(obj, contains);
        }
    }

    public StringSchema required() {
        super.required();
        return this;
    }

    public boolean isValid(String valid) {
        return super.isValid(valid) && (!super.required || StringUtils.isNotEmpty(valid))
                && checkLength() && checkContains();
    }

}
