package tracker;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Test
    @DisplayName("Check firstName from credentials")
    void testFirstName() {
        Main main = new Main();
        String credentials = "John Doe email";
        assertEquals("John", main.isFirstName(credentials));
    }

    @Test
    @DisplayName("Check lastName from credentials")
    void testLastName() {
        Main main = new Main();
        String credentials = "John Doe email";
        assertEquals("Doe", main.isLastName(credentials));
    }

    @Test
    @DisplayName("Check email from credentials")
    void testEmail() {
        Main main = new Main();
        String credentials = "John Doe email";
        assertEquals("email", main.isEmail(credentials));
    }

}