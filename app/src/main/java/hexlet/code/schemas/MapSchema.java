package hexlet.code.schemas;

import lombok.NoArgsConstructor;

import java.util.*;

@NoArgsConstructor
public class MapSchema extends BaseSchema<Map> {
    private Integer size = null;
    private Map<String, BaseSchema> schemas = new HashMap<>();

    public MapSchema sizeof(int size) {
        this.size = size;
        return this;
    }

    private boolean checkSize() {
        if (Objects.nonNull(size)) {
            if (Objects.isNull(obj)) {
                return false;
            } else {
                return obj.size() == size;
            }
        } else {
            return true;
        }
    }

    public MapSchema shape(Map<String, BaseSchema> schema) {
        this.schemas = schema;
        return this;
    }

    private boolean checkSchemas() {
        if (schemas.isEmpty()) {
            return true;
        } else {
            List<Boolean> results = new ArrayList<>();
            schemas.forEach((key, baseSchema) ->
                    results.add(baseSchema.isValid(obj.get(key))));
            return !results.contains(false);
        }
    }

    public boolean isValid(Map map) {
        return super.isValid(map) && checkSize() && checkSchemas();
    }
}
