package hexlet.code.schemas;

import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor
public class BaseSchema<T> {
    protected T obj = null;
    protected boolean required = false;

    public BaseSchema<T> required() {
        this.required = true;
        return this;
    }

    protected boolean checkRequired() {
        return !required || Objects.nonNull(obj);
    }

    protected boolean isValid(T valid) {
        this.obj = valid;
        return checkRequired();
    }
}
