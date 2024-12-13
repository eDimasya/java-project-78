package hexlet.code.schemas;

import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

@NoArgsConstructor
public class StringSchema extends BaseSchema<String> {

    /**
     * contains.
     */
    private String containsCondition = null;
    /**
     * min length.
     */
    private Integer minLengthCondition = null;
    /**
     * max length.
     */
    private Integer maxLengthCondition = null;

    /**
     * @param minLength min.
     * @return this.
     */
    public StringSchema minLength(int minLength) {
        if (minLength >= 0) {
            this.minLengthCondition = minLength;
        }
        return this;
    }

    /**
     * @return required.
     */
    public StringSchema required() {
        super.required();
        return this;
    }

    /**
     * @return min.
     */
    private boolean checkMinLength() {
        if (Objects.isNull(minLengthCondition)) {
            return true;
        } else {
            return StringUtils.length(obj) >= minLengthCondition;
        }
    }

    /**
     * @param maxLength max.
     * @return this.
     */
    public StringSchema maxLength(int maxLength) {
        if (maxLength > 0) {
            this.maxLengthCondition = maxLength;
        }
        return this;
    }

    /**
     * @return max.
     */
    private boolean checkMaxLength() {
        if (Objects.isNull(maxLengthCondition)) {
            return true;
        } else {
            return StringUtils.length(obj) <= maxLengthCondition;
        }
    }

    /**
     * @return length.
     */
    private boolean checkLength() {
        return checkMinLength() && checkMaxLength();
    }

    /**
     * @param contains str.
     * @return this.
     */
    public StringSchema contains(String contains) {
        this.containsCondition = contains;
        return this;
    }

    /**
     * @return contains.
     */
    private boolean checkContains() {
        if (StringUtils.isEmpty(containsCondition)) {
            return true;
        } else {
            return StringUtils.contains(obj, containsCondition);
        }
    }

    /**
     * @param valid str.
     * @return valid.
     */
    public boolean isValid(String valid) {
        return super.isValid(valid) && (!super.isRequired || StringUtils.isNotEmpty(valid))
                && checkLength() && checkContains();
    }
}
