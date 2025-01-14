import java.util.ArrayList; 
import org.junit.jupiter.api.Test; 
import org.junit.jupiter.api.DisplayName; 
import static org.junit.jupiter.api.Assertions.assertEquals; 

public class TestUnit {

    @Test
    @DisplayName("Sample test: verifying addition")
    void testGetPlayerDetails() {
        // Exemple de test
        int result = 2 + 3;
        assertEquals(5, result, "Addition should return 5");
    }
}
