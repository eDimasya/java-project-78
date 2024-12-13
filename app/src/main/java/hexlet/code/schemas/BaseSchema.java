package hexlet.code.schemas;

import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor
public class BaseSchema<T> {
    /**
     * obj
     */
    protected T obj = null;
    /**
     * required
     */
    protected boolean isRequired = false;

    /**
     * @return required
     */
    public BaseSchema<T> required() {
        this.isRequired = true;
        return this;
    }

    /**
     * @return required
     */
    protected boolean checkRequired() {
        return !isRequired || Objects.nonNull(obj);
    }

    /**
     * @param valid obj
     * @return valid
     */
    protected boolean isValid(T valid) {
        this.obj = valid;
        return checkRequired();
    }
}
