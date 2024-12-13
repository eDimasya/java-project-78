package hexlet.code.schemas;

import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor
public class BaseSchema<T> {
    protected T obj = null;
    protected boolean isRequired = false;

    public BaseSchema<T> required() {
        this.isRequired = true;
        return this;
    }

    protected boolean checkRequired() {
        return !isRequired || Objects.nonNull(obj);
    }

    protected boolean isValid(T valid) {
        this.obj = valid;
        return checkRequired();
    }
}
