package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NumberSchemaTest {
    @Test
    void isValid() {
        Validator validator = new Validator();
        NumberSchema numberSchema1 = validator.number();
        assertTrue(numberSchema1.isValid(null));
        assertTrue(numberSchema1.isValid(1));
        assertTrue(numberSchema1.isValid(0));
        assertTrue(numberSchema1.isValid(-1));
        numberSchema1.required();
        assertTrue(numberSchema1.isValid(1));
        assertFalse(numberSchema1.isValid(null));
        numberSchema1.positive();
        assertTrue(numberSchema1.isValid(1));
        assertFalse(numberSchema1.isValid(0));
        assertFalse(numberSchema1.isValid(-1));
    }
}
