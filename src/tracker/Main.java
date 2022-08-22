package tracker;

import java.util.Scanner;

public class Main {

    static int addedNumberOfStudents;

    public static void main(String[] args) {
        System.out.println("Learning Progress Tracker");
        String a;
        do {
            a = input();
            if (a.isBlank()) {
                System.out.println("No input");
            } else if (!"exit".equals(a)) {
                System.out.println("Unknown command!");
            } else if ("add students".equals(input())) {
                addStudents();
            }
        } while(!"exit".equals(a));
        System.out.println("Bye!");
    }

    private static String input() {
        try (Scanner sc = new Scanner(System.in)) {
            return sc.nextLine().strip();
        }
    }

    private static void addStudents() {
        addedNumberOfStudents = 0;
        String input;
        do {
            System.out.println("Enter student credentials or 'back' to return:");
            input = input();
            if ("back".equals(input)) {
                System.out.printf("Total %d students have been added.", addedNumberOfStudents);
            } else {
                if (isCorrectCredentials(input)) {
                    addedNumberOfStudents++;
                }
            }
        } while (!"back".equals(input));
    }

    protected static boolean isCorrectCredentials(String credentials) {

        String[] credentialsOfStudent = credentials.split("\\s+");
        if (credentialsOfStudent.length < 3) {
            System.out.println("Incorrect credentials");
        }
        String firstName = credentialsOfStudent[0];
        String email = credentialsOfStudent[credentialsOfStudent.length - 1];
        StringBuilder lastName = new StringBuilder();
        for (int i = 1; i < credentialsOfStudent.length - 2; i++) {
            lastName.append(credentialsOfStudent[i]);
            lastName.append(" ");
        }
        lastName.deleteCharAt(lastName.lastIndexOf(lastName.toString()));

        return isFirstNameCorrect(firstName) && isLastNameCorrect(lastName.toString()) && isEmailCorrect(email);
    }

    /*Accept only ASCII characters, from A to Z and from a to z as well as hyphens and apostrophes,
    at least two characters long, hyphens and apostrophes - not at the first or the last character,
    cannot be adjacent to each other.*/
    protected static boolean isFirstNameCorrect(String firstName) {
        boolean isFirstNameCorrect = false;
        if (firstName.matches("\\b[A-Z]+([A-Za-z]*[-']?[A-Za-z]+)+\\b")) {
            isFirstNameCorrect = true;
        } else {
            System.out.println("Incorrect first name.");
        }
        return isFirstNameCorrect;
    }
    /*Accept only ASCII characters, from A to Z and from a to z as well as hyphens and apostrophes,
    at least two characters long, hyphens and apostrophes - not at the first or the last character,
    cannot be adjacent to each other.
    Can contain one or more words without district.
     */
    protected static boolean isLastNameCorrect(String lastName) {
        boolean isLastNameCorrect = false;
        if (lastName.matches("(\\b[A-Za-z]+([A-Za-z]*[-']?[A-Za-z]+)+\\b\\s?)+")) {
            isLastNameCorrect = true;
        } else {
            System.out.println("Incorrect last name.");
        }
        return isLastNameCorrect;
    }

    /*The local part can be up to 64 characters in length and consist of any combination of alphabetic characters,
    digits, or any of the following special characters: ! # $ % & ‘ * + – / = ? ^ _ ` . { | } ~
    The domain part cannot be more than 255 characters in length and must conform to the specification for hostnames
    which is a list of dot-separated DNS labels. Each DNS label must not exceed 63 characters and should consist
    of any combination of alphabetic characters, digits and hyphens.*/
    protected static boolean isEmailCorrect(String email) {
        boolean isEmailCorrect = false;
        if (email.matches("\\p{ASCII}{2,63}@\\p{ASCII}{2,192}[.][\\w-]{3,63}")) {
            isEmailCorrect = true;
        } else {
            System.out.println("Incorrect email.");
        }
        return isEmailCorrect;
    }

}
