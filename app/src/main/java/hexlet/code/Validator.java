package hexlet.code;

import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Validator {

    /**
     * @return string schema
     */
    public StringSchema string() {
        return new StringSchema();
    }

    /**
     * @return num schema
     */
    public NumberSchema number() {
        return new NumberSchema();
    }

    /**
     * @return map schema
     */
    public MapSchema map() {
        return new MapSchema();
    }
}
