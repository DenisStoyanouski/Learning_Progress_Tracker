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
    @ValueSource(strings = {"J", "陳", "O''Neal", "Jean--Clause", "-Jean", "Jean-", "'Neal", "Neal'" })
    void testFirstNameFalse(String name) {
        assertFalse(isFirstNameCorrect(name));
    }


    @ParameterizedTest
    @DisplayName("lastName is correct")
    @ValueSource(strings = {"Je Jean-Clause O'Neal", "Robert Jemison Van de Graaff or John Ronald Reuel Tolkien", "O'Connor"})
    void testLastNameTrue(String name) {
        assertTrue(isLastNameCorrect(name));
    }


    @ParameterizedTest
    @DisplayName("email is correct")
    @ValueSource(strings = {"jdoe@mail.net", "jane.doe@yahoo.com", "jjlajldjglq145466@thoooo.dom.dom"})
    void testEmailTrue(String email) {
        assertTrue(isEmailCorrect(email));
    }

    @ParameterizedTest
    @DisplayName("email is incorrect")
    @ValueSource(strings = {"email", "email@dot", "em@ail@grab.net", "@mail.com"})
    void testEmailFalse(String email) {
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