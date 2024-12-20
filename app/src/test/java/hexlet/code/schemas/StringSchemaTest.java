package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StringSchemaTest {

    @Test
    void isValid() {
        Validator validator = new Validator();
        StringSchema stringSchema1 = validator.string();
        //Not required
        assertTrue(stringSchema1.isValid(null));
        assertTrue(stringSchema1.isValid(""));
        assertTrue(stringSchema1.isValid("test"));
        //Required
        stringSchema1.required();
        assertFalse(stringSchema1.isValid(null));
        assertFalse(stringSchema1.isValid(""));
        assertTrue(stringSchema1.isValid("test"));
        StringSchema stringSchema2 = validator.string();
        //Length
        stringSchema2.minLength(1);
        stringSchema2.maxLength(2);
        assertFalse(stringSchema2.isValid("test"));
        assertTrue(stringSchema2.isValid("te"));
        assertTrue(stringSchema2.isValid("t"));
        assertFalse(stringSchema2.isValid(""));
        assertFalse(stringSchema2.isValid(null));
    }
}
