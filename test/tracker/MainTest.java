package tracker;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Test
    @DisplayName("firstName Jean-Clause")
    void testFirstName1() {
        Main main = new Main();
        String name = "Jean-Clause";
        assertTrue(main.isFirstNameCorrect(name));
    }

    @Test
    @DisplayName("firstName Je")
    void testFirstName2() {
        Main main = new Main();
        String name = "Je";
        assertTrue(main.isFirstNameCorrect(name));
    }

    @Test
    @DisplayName("firstName J")
    void testFirstName3() {
        Main main = new Main();
        String name = "J";
        assertFalse(main.isFirstNameCorrect(name));
    }

    @Test
    @DisplayName("firstName 陳")
    void testFirstName4() {
        Main main = new Main();
        String name = "陳";
        assertFalse(main.isFirstNameCorrect(name));
    }

    @Test
    @DisplayName("firstName O'Neal")
    void testFirstName5() {
        Main main = new Main();
        String name = "O'Neal";
        assertTrue(main.isFirstNameCorrect(name));
    }



}