package hexlet.code.schemas;

import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Objects;

@NoArgsConstructor
public class NumberSchema extends BaseSchema<Integer> {

    private boolean positiveCondition = false;
    private Integer minRangeCondition = null;
    private Integer maxRangeCondition = null;

    public NumberSchema positive() {
        this.positiveCondition = true;
        return this;
    }

    private boolean checkPositive() {
        return !positiveCondition || obj > 0;
    }

    public NumberSchema required() {
        super.required();
        return this;
    }

    public NumberSchema range(@NonNull Integer min, @NonNull Integer max) {
        if (min < max) {
            this.minRangeCondition = min;
            this.maxRangeCondition = max;
        } else {
            throw new RuntimeException("Invalid range");
        }
        return this;
    }

    private boolean checkRange() {
        if (Objects.nonNull(minRangeCondition) && Objects.nonNull(maxRangeCondition)) {
            return obj >= minRangeCondition && obj <= maxRangeCondition;
        } else {
            return true;
        }
    }

    public boolean isValid(Integer valid) {
        return super.isValid(valid) && checkPositive() && checkRange();
    }
}