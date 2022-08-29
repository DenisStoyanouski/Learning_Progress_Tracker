package tracker;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;
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


    @ParameterizedTest
    @MethodSource("credentialProviderPositive")
    void testCredentialsPositive(String[] credentials) {
        assertTrue(isCorrectCredentials(credentials));

    }
    static Stream<Arguments> credentialProviderPositive() {
        return Stream.of(
                arguments((Object) new String[]{"John", "Doe", "jdoe@mail.net"}),
                arguments((Object) new String[]{"Jane", "Doe","jane.doe@yahoo.com"}),
                arguments((Object) new String[]{"Jean-Claude", "O'Connor", "jcda123@google.net"})
        );
    }

    @ParameterizedTest
    @MethodSource("credentialProviderNegative")
    void testCredentialsNegative(String[] credentials) {
        assertFalse(isCorrectCredentials(credentials));

    }
    static Stream<Arguments> credentialProviderNegative() {
        return Stream.of(
                arguments((Object) new String[]{"help"}),
                arguments((Object) new String[]{"Jane", "Doe"}),
                arguments((Object) new String[]{})
        );
    }

    @ParameterizedTest
    @MethodSource("pointsProviderPositive")
    void testPointsPositive(String[] points) {
        assertTrue(isPointsFormatCorrect(points));

    }
    static Stream<Arguments> pointsProviderPositive() {
        return Stream.of(
                arguments((Object) new String[]{"10000", "5", "5", "5", "5"}),
                arguments((Object) new String[]{"10000", "0", "0", "0", "0"}),
                arguments((Object) new String[]{"10000", "10", "10", "10", "10"})
        );
    }

    @ParameterizedTest
    @MethodSource("pointsProviderNegative")
    void testPointsNegative(String[] points) {
        assertFalse(isPointsFormatCorrect(points));

    }
    static Stream<Arguments> pointsProviderNegative() {
        return Stream.of(
                arguments((Object) new String[]{"10000", "7", "7", "7", "7", "7"}),
                arguments((Object) new String[]{"10000", "-1", "2", "2", "2"}),
                arguments((Object) new String[]{"10000", "?", "1", "1", "1"})
        );
    }




}