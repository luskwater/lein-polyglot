package {{namespace}};
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class {{java-class-name}}Test {
    @BeforeEach
    void doJustOnce() {
        System.out.println("Do this once before the tests");
    }
    @Test
    void testSomething() {
        assertNotNull("foo");
    }
    @Test
    void testSomethingElse() {
        assertThrows(IllegalStateException.class,
                     () -> {
                         throw new IllegalStateException("show how it's done");
                     },
                     "Should be a problem");
    }
}
