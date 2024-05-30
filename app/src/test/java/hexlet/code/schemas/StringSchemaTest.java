package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StringSchemaTest {

    @Test
    void isValid() {
        Validator validator = new Validator();
        StringSchema stringSchema1 = validator.string();
        //Not required
        Assertions.assertTrue(stringSchema1.isValid(null));
        Assertions.assertTrue(stringSchema1.isValid(""));
        Assertions.assertTrue(stringSchema1.isValid("test"));
        //Required
        stringSchema1.required();
        Assertions.assertFalse(stringSchema1.isValid(null));
        Assertions.assertFalse(stringSchema1.isValid(""));
        Assertions.assertTrue(stringSchema1.isValid("test"));
        StringSchema stringSchema2 = validator.string();
        //Length
        stringSchema2.minLength(1).maxLength(2);
        Assertions.assertFalse(stringSchema2.isValid("test"));
        Assertions.assertTrue(stringSchema2.isValid("te"));
        Assertions.assertTrue(stringSchema2.isValid("t"));
        Assertions.assertFalse(stringSchema2.isValid(""));
        Assertions.assertFalse(stringSchema2.isValid(null));
        StringSchema stringSchema3 = validator.string();
        //Contains
        Assertions.assertTrue(stringSchema3.contains("test").isValid("test"));
        Assertions.assertTrue(stringSchema3.contains("test").isValid("&test?"));
        Assertions.assertFalse(stringSchema3.contains("test").isValid("&te*st?"));
        Assertions.assertFalse(stringSchema3.contains("test").isValid(null));
        Assertions.assertFalse(stringSchema3.contains("test").isValid(""));
        //Complex
        StringSchema stringSchema4 = validator.string();
        Assertions.assertFalse(stringSchema4.required().contains("test").minLength(1).maxLength(2).isValid("test"));
    }
}
