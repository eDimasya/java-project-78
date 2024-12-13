package hexlet.code.schemas;

import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Objects;

@NoArgsConstructor
public class NumberSchema extends BaseSchema<Integer> {

    /**
     * positive.
     */
    private boolean positiveCondition = false;
    /**
     * min Range.
     */
    private Integer minRangeCondition = null;
    /**
     * max Range.
     */
    private Integer maxRangeCondition = null;

    /**
     * @return positive.
     */
    public NumberSchema positive() {
        this.positiveCondition = true;
        return this;
    }

    /**
     * @return required.
     */
    public NumberSchema required() {
        super.required();
        return this;
    }

    /**
     * @return positive.
     */
    private boolean checkPositive() {
        if (positiveCondition && Objects.nonNull(obj)) {
            return obj > 0;
        } else {
            return true;
        }
    }

    /**
     * @param min min.
     * @param max max.
     * @return range.
     */
    public NumberSchema range(@NonNull Integer min, @NonNull Integer max) {
        if (min < max) {
            this.minRangeCondition = min;
            this.maxRangeCondition = max;
        } else {
            throw new RuntimeException("Invalid range");
        }
        return this;
    }

    /**
     * @return check range.
     */
    private boolean checkRange() {
        if (Objects.nonNull(minRangeCondition) && Objects.nonNull(maxRangeCondition)) {
            return obj >= minRangeCondition && obj <= maxRangeCondition;
        } else {
            return true;
        }
    }

    /**
     * @param valid num.
     * @return valid.
     */
    public boolean isValid(Integer valid) {
        return super.isValid(valid) && checkPositive() && checkRange();
    }
}
