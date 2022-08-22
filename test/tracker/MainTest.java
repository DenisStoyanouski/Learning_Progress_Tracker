package tracker;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static tracker.Main.*;

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

    @Test
    @DisplayName("firstName O''Neal with double apostrophes")
    void testFirstName6() {
        Main main = new Main();
        String name = "O''Neal";
        assertFalse(main.isFirstNameCorrect(name));
    }

    @Test
    @DisplayName("firstName Jean--Clause with double hyphens")
    void testFirstName7() {
        Main main = new Main();
        String name = "O''Neal";
        assertFalse(main.isFirstNameCorrect(name));
    }

    @Test
    @DisplayName("firstName -Jean hyphen isn't at first place")
    void testFirstName8() {
        Main main = new Main();
        String name = "-Jean";
        assertFalse(main.isFirstNameCorrect(name));
    }

    @Test
    @DisplayName("firstName Jean- hyphen isn't at last place")
    void testFirstName9() {
        Main main = new Main();
        String name = "Jean-";
        assertFalse(main.isFirstNameCorrect(name));
    }

    @Test
    @DisplayName("firstName 'Neal apostrophes isn't at first place")
    void testFirstName10() {
        Main main = new Main();
        String name = "'Neal";
        assertFalse(main.isFirstNameCorrect(name));
    }

    @Test
    @DisplayName("firstName Neal' apostrophes isn't at last place")
    void testFirstName11() {
        Main main = new Main();
        String name = "Neal'";
        assertFalse(isFirstNameCorrect(name));
    }

    @Test
    @DisplayName("lastName Je Jean-Clause O'Neal")
    void testLastName1() {
        Main main = new Main();
        String name = "Je Jean-Clause O'Neal";
        assertTrue(isLastNameCorrect(name));
    }

    @Test
    @DisplayName("lastName Robert Jemison Van de Graaff or John Ronald Reuel Tolkien")
    void testLastName2() {
        Main main = new Main();
        String name = "Robert Jemison Van de Graaff or John Ronald Reuel Tolkien";
        assertTrue(isLastNameCorrect(name));
    }





}