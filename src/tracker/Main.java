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
        String input = "";
        try (Scanner sc = new Scanner(System.in)) {
            input = sc.nextLine().strip();
        } catch (Exception e) {
            e.getMessage();
        }
        return input;
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
                isCorrectCredentials(input);
                addedNumberOfStudents++;
            }
        } while (!"back".equals(input));
    }

    protected static boolean isCorrectCredentials(String credentials) {

        String[] credentialsOfStudent = credentials.split("\\s+");
        String firstName = credentialsOfStudent[0];
        String email = credentialsOfStudent[credentialsOfStudent.length - 1];
        String lastName = "";
        for (int i = 1; i < credentialsOfStudent.length - 2; i++) {
            lastName = lastName + credentialsOfStudent[i] + " ";
        }

        boolean correct = true;
        if (isFirstNameCorrect(firstName)) {
            System.out.println("Incorrect first name.");
        }
        return correct;
    }

    //  Accept only ASCII characters, from A to Z and from a to z as well as hyphens - and apostrophes
    //  at least 2 symbols'.
    protected static boolean isFirstNameCorrect(String firstName) {
        boolean isFirstNameCorrect = false;
        if (firstName.matches("[A-Z]['A-Za-z\\-]+")) {
            isFirstNameCorrect = true;
        } else {
            System.out.println("Incorrect first name.");
        }
        return isFirstNameCorrect;
    }
    //  Accept only ASCII characters, from A to Z and from a to z as well as hyphens - and apostrophes '
    //  at least 2 symbols. Can contain one or more words without district.
    protected static boolean isLastNameCorrect(String lastName) {
        boolean isLastNameCorrect = false;
        if (lastName.matches("[A-Z]['A-Za-z\\-\\s]+")) {
            isLastNameCorrect = true;
        } else {
            System.out.println("Incorrect last name.");
        }
        return isLastNameCorrect;
    }

    protected static boolean isEmailCorrect(String email) {
        boolean isEmailCorrect = false;
        if (email.matches("\\p{ASCII}{2,63}@\\p{ASCII}{2,256}[.][\\w-]{3,63}")) {
            isEmailCorrect = true;
        } else {
            System.out.println("Incorrect email.");
        }
        return isEmailCorrect;
    }
    /*private static boolean isStudent(String input) {

    }*/

}
