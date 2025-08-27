package app.ludrive.core.service.validation;

import app.ludrive.core.exception.ValidationException;

import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ValidatorTest {

    private final Validator validator = new Validator();

    public ValidatorTest() {}

    @Test
    @Order(1)
    public void validatePath() {

        validator.validatePath("/");
        validator.validatePath("/a");
        validator.validatePath("/a/b");
        validator.validatePath("/a/b/c");
        validator.validatePath("/a11/b22/c33/d44");
        validator.validatePath("/.a");
        validator.validatePath("/.ab");
        validator.validatePath("/..abc");
        validator.validatePath("/...abc");
        validator.validatePath("/....abc");
        validator.validatePath("/. a");
        validator.validatePath("/. . .. a");

        Assertions.assertThrows(ValidationException.class, () -> validator.validatePath(null));
        Assertions.assertThrows(ValidationException.class, () -> validator.validatePath(""));
        Assertions.assertThrows(ValidationException.class, () -> validator.validatePath(" "));
        Assertions.assertThrows(ValidationException.class, () -> validator.validatePath("  "));
        Assertions.assertThrows(ValidationException.class, () -> validator.validatePath("//"));
        Assertions.assertThrows(ValidationException.class, () -> validator.validatePath("a//"));
        Assertions.assertThrows(ValidationException.class, () -> validator.validatePath("//a"));
        Assertions.assertThrows(ValidationException.class, () -> validator.validatePath("/a//"));
        Assertions.assertThrows(ValidationException.class, () -> validator.validatePath("a"));
        Assertions.assertThrows(ValidationException.class, () -> validator.validatePath("/a/"));
        Assertions.assertThrows(ValidationException.class, () -> validator.validatePath("a/"));
        Assertions.assertThrows(ValidationException.class, () -> validator.validatePath(" /a"));
        Assertions.assertThrows(ValidationException.class, () -> validator.validatePath("a/b"));
        Assertions.assertThrows(ValidationException.class, () -> validator.validatePath("a/b/.."));
        Assertions.assertThrows(ValidationException.class, () -> validator.validatePath("/."));
        Assertions.assertThrows(ValidationException.class, () -> validator.validatePath("/.."));
        Assertions.assertThrows(ValidationException.class, () -> validator.validatePath("/a/."));
        Assertions.assertThrows(ValidationException.class, () -> validator.validatePath("/a/.."));
    }

    @Test
    @Order(2)
    public void getParentPath() {

        Assertions.assertNull(validator.getParentPath("/"));
        Assertions.assertEquals("/", validator.getParentPath("/a"));
        Assertions.assertEquals("/", validator.getParentPath("/abc"));
        Assertions.assertEquals("/", validator.getParentPath("/ a "));
        Assertions.assertEquals("/", validator.getParentPath("/ "));
        Assertions.assertEquals("/a", validator.getParentPath("/a/b"));
        Assertions.assertEquals("/a", validator.getParentPath("/a/ "));
        Assertions.assertEquals("/a/b/c", validator.getParentPath("/a/b/c/def"));

        Assertions.assertThrows(ValidationException.class, () -> validator.validatePath(null));
        Assertions.assertThrows(ValidationException.class, () -> validator.validatePath("/a/"));
        Assertions.assertThrows(ValidationException.class, () -> validator.validatePath("//"));
    }
}
