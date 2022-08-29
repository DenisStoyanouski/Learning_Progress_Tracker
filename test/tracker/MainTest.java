package tracker;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;
import static tracker.Main.*;

class MainTest {

    @ParameterizedTest
    @DisplayName("firstName is correct")
    @ValueSource(strings = {"Jean-Clause", "Je", "O'Neal"})
    void testFirstNameTrue(String name) {
        assertTrue(isFirstNameCorrect(name));
    }

    @ParameterizedTest
    @DisplayName("firstName is incorrect")
    @ValueSource(strings = {"J", "陳", "O''Neal", "Jean--Clause", "-Jean", "Jean-", "'Neal" })
    void testFirstNameFalse(String name) {
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
    @DisplayName("lastName O'Connor")
    void testLastName3() {
        String name = "O'Connor";
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

    @Test
    @DisplayName("John Doe jdoe@mail.net - correct")
    void testCorrectCredentials1() {
        String[] credentials = {"John", "Doe", "jdoe@mail.net"};
        assertTrue(isCorrectCredentials(credentials));
    }

    @Test
    @DisplayName("Jane Doe jane.doe@yahoo.com - correct")
    void testCorrectCredentials2() {
        String[] credentials = {"Jane", "Doe", "jane.doe@yahoo.com"};
        assertTrue(isCorrectCredentials(credentials));
    }

    @Test
    @DisplayName(" help - incorrect")
    void testCorrectCredentials3() {
        String[] credentials = {"help"};
        assertFalse(isCorrectCredentials(credentials));
    }

    @Test
    @DisplayName("Jean-Claude O'Connor jcda123@google.net- correct")
    void testCorrectCredentials4() {
        String[] credentials = {"Jean-Claude", "O'Connor", "jcda123@google.net"};
        assertTrue(isCorrectCredentials(credentials));
    }





}