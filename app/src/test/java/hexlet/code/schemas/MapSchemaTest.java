package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MapSchemaTest {

    @Test
    void isValid() {
        Validator validator = new Validator();
        MapSchema mapSchema1 = validator.map();
        assertTrue(mapSchema1.isValid(null));
        assertTrue(mapSchema1.isValid(new HashMap<>()));
    }
}
