package tracker;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;
import static tracker.Main.*;

class MainTest {
    @ParameterizedTest
    @DisplayName("firstName positive tests")
    @ValueSource(strings = {"Jean-Clause","Je", "O'Neal", "Teddy-Bear", "Verylondnamewith-hyphensand'apostoroth"})
    void testFirstNamePositive(String name) {
        assertTrue(isFirstNameCorrect(name));
    }


    @ParameterizedTest
    @DisplayName("firstName negative tests")
    @ValueSource(strings = {"J","陳", "O''Neal", "Jean--Clause", "-Jean", "Jean-", "'Neal", "Neal'", "Ne'-al", "F1edor", "F!edor"})
    void testFirstNameNegative(String name) {
        assertFalse(isFirstNameCorrect(name));
    }


    @ParameterizedTest
    @DisplayName("lastName positive tests")
    @ValueSource(strings = {"Je Jean-Clause O'Neal", "Robert Jemison Van de Graaff or John Ronald Reuel Tolkien", "O'Connor" })
    void testLastNamePositive(String name) {
        assertTrue(isLastNameCorrect(name));
    }


    @ParameterizedTest
    @DisplayName("email positive tests")
    @ValueSource(strings = {"jdoe@mail.net", "jane.doe@yahoo.com", "j@d.c"})
    void testEmailPositive(String email) {
        assertTrue(isEmailCorrect(email));
    }

    @ParameterizedTest
    @DisplayName("email positive tests")
    @ValueSource(strings = {"email", "jane.doe@yahoocom", "j@@d.c"})
    void testEmailNegative(String email) {
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
        String[] credentials = {"Jane", "Doe","jane.doe@yahoo.com"};
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