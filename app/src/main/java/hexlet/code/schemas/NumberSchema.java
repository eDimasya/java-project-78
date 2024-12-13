package hexlet.code.schemas;

import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Objects;

@NoArgsConstructor
public class NumberSchema extends BaseSchema<Integer> {

    private boolean positive = false;
    private Integer minRange = null;
    private Integer maxRange = null;

    public NumberSchema positive() {
        this.positive = true;
        return this;
    }

    private boolean checkPositive() {
        return !positive || obj > 0;
    }

    public NumberSchema required() {
        super.required();
        return this;
    }

    public NumberSchema range(@NonNull Integer min, @NonNull Integer max) {
        if (min < max) {
            this.minRange = min;
            this.maxRange = max;
        } else {
            throw new RuntimeException("Invalid range");
        }
        return this;
    }

    private boolean checkRange() {
        if (Objects.nonNull(minRange) && Objects.nonNull(maxRange)) {
            return obj >= minRange && obj <= maxRange;
        } else {
            return true;
        }
    }

    public boolean isValid(Integer valid) {
        return super.isValid(valid) && checkPositive() && checkRange();
    }
}
