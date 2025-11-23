package factory_method;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class FactoryLoaderTest {

    @Test
    void testGetInstanceValid() throws Exception {
        Object instance = FactoryLoader.getInstance("java.util.ArrayList");
        assertNotNull(instance);
        assertTrue(instance instanceof ArrayList);
    }

    @Test
    void testGetInstanceInvalid() {
        assertThrows(ClassNotFoundException.class, () -> {
            FactoryLoader.getInstance("com.nonexistent.Class");
        });
    }
}
