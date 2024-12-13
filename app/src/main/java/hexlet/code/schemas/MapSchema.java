package hexlet.code.schemas;

import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@NoArgsConstructor
public class MapSchema extends BaseSchema<Map> {
    private Integer sizeCondition = null;
    private Map<String, BaseSchema<String>> schemas = new HashMap<>();

    public MapSchema sizeof(int size) {
        this.sizeCondition = size;
        return this;
    }

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

    public MapSchema shape(Map<String, BaseSchema<String>> schema) {
        this.schemas = schema;
        return this;
    }

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

    public MapSchema required() {
        super.required();
        return this;
    }

    public boolean isValid(Map map) {
        return super.isValid(map) && checkSize() && checkSchemas();
    }
}
