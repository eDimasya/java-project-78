package hexlet.code.schemas;

import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@NoArgsConstructor
public class MapSchema extends BaseSchema<Map> {
    /**
     * Size
     */
    private Integer sizeCondition = null;
    /**
     * Schemas
     */
    private Map<String, BaseSchema<String>> schemas = new HashMap<>();


    /**
     * @param size size of Map
     * @return this
     */
    public MapSchema sizeof(int size) {
        this.sizeCondition = size;
        return this;
    }

    /**
     * @return check size of map
     */
    private boolean checkSize() {
        if (Objects.nonNull(sizeCondition)) {
            if (Objects.isNull(obj)) {
                return false;
            } else {
                return obj.size() == sizeCondition;
            }
        } else {
            return true;
        }
    }

    /**
     * @param schema schemas
     * @return this
     */
    public MapSchema shape(Map<String, BaseSchema<String>> schema) {
        this.schemas = schema;
        return this;
    }

    /**
     * @return schemas check
     */
    private boolean checkSchemas() {
        if (!schemas.isEmpty()) {
            for (Map.Entry<String, BaseSchema<String>> schemaEntry : schemas.entrySet()) {
                if (obj.containsKey(schemaEntry.getKey())) {
                    if (!schemaEntry.getValue().isValid(
                            obj.get(schemaEntry.getKey()).toString())) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * @return required
     */
    public MapSchema required() {
        super.required();
        return this;
    }

    /**
     * @param map map
     * @return validation
     */
    public boolean isValid(Map map) {
        return super.isValid(map) && checkSize() && checkSchemas();
    }
}
