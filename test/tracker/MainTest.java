package tracker;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static tracker.Main.*;

class MainTest {
    @Test
    @DisplayName("firstName Jean-Clause")
    void testFirstName1() {
        String name = "Jean-Clause";
        assertTrue(isFirstNameCorrect(name));
    }

    @Test
    @DisplayName("firstName Je")
    void testFirstName2() {
        String name = "Je";
        assertTrue(isFirstNameCorrect(name));
    }

    @Test
    @DisplayName("firstName J")
    void testFirstName3() {

        String name = "J";
        assertFalse(isFirstNameCorrect(name));
    }

    @Test
    @DisplayName("firstName 陳")
    void testFirstName4() {
        String name = "陳";
        assertFalse(isFirstNameCorrect(name));
    }

    @Test
    @DisplayName("firstName O'Neal")
    void testFirstName5() {
        String name = "O'Neal";
        assertTrue(isFirstNameCorrect(name));
    }

    @Test
    @DisplayName("firstName O''Neal with double apostrophes")
    void testFirstName6() {
        String name = "O''Neal";
        assertFalse(isFirstNameCorrect(name));
    }

    @Test
    @DisplayName("firstName Jean--Clause with double hyphens")
    void testFirstName7() {
        String name = "O''Neal";
        assertFalse(isFirstNameCorrect(name));
    }

    @Test
    @DisplayName("firstName -Jean hyphen isn't at first place")
    void testFirstName8() {
        String name = "-Jean";
        assertFalse(isFirstNameCorrect(name));
    }

    @Test
    @DisplayName("firstName Jean- hyphen isn't at last place")
    void testFirstName9() {
        String name = "Jean-";
        assertFalse(isFirstNameCorrect(name));
    }

    @Test
    @DisplayName("firstName 'Neal apostrophes isn't at first place")
    void testFirstName10() {
        String name = "'Neal";
        assertFalse(isFirstNameCorrect(name));
    }

    @Test
    @DisplayName("firstName Neal' apostrophes isn't at last place")
    void testFirstName11() {
        String name = "Neal'";
        assertFalse(isFirstNameCorrect(name));
    }

    @Test
    @DisplayName("lastName Je Jean-Clause O'Neal")
    void testLastName1() {
        String name = "Je Jean-Clause O'Neal";
        assertTrue(isLastNameCorrect(name));
    }

    @Test
    @DisplayName("lastName Robert Jemison Van de Graaff or John Ronald Reuel Tolkien")
    void testLastName2() {
        String name = "Robert Jemison Van de Graaff or John Ronald Reuel Tolkien";
        assertTrue(isLastNameCorrect(name));
    }

    @Test
    @DisplayName("jdoe@mail.net")
    void testEmail1() {
        String email = "jdoe@mail.net";
        assertTrue(isEmailCorrect(email));
    }

    @Test
    @DisplayName("jane.doe@yahoo.com")
    void testEmail2() {
        String email = "jane.doe@yahoo.com";
        assertTrue(isEmailCorrect(email));
    }

    @Test
    @DisplayName("email")
    void testEmail3() {
        String email = "email";
        assertFalse(isEmailCorrect(email));
    }





}