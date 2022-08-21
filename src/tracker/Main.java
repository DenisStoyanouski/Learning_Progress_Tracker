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
                isFirstName(input);
                addedNumberOfStudents++;
            }
        } while (!"back".equals(input));
    }

    protected static String isFirstName(String credentials) {
        String[] credentialsOfStudent = credentials.split("\\s+");
        String firstName = credentialsOfStudent[0];
        String email = credentialsOfStudent[credentialsOfStudent.length - 1];
        String lastName = "";
        for (int i = 1; i < credentialsOfStudent.length - 2; i++) {
            lastName = credentialsOfStudent[i] + " ";
        }
        return firstName;
    }

    protected static String isLastName(String credentials) {
        String[] credentialsOfStudent = credentials.split("\\s+");
        String firstName = credentialsOfStudent[0];
        String email = credentialsOfStudent[credentialsOfStudent.length - 1];
        String lastName = "";
        for (int i = 1; i <= credentialsOfStudent.length - 2; i++) {
            lastName = credentialsOfStudent[i] + " ";
        }
        return lastName.strip();
    }

    protected static String isEmail(String credentials) {
        String[] credentialsOfStudent = credentials.split("\\s+");
        String firstName = credentialsOfStudent[0];
        String email = credentialsOfStudent[credentialsOfStudent.length - 1];
        String lastName = "";
        for (int i = 1; i < credentialsOfStudent.length - 2; i++) {
            lastName = credentialsOfStudent[i] + " ";
        }
        return email;
    }
    /*private static boolean isStudent(String input) {

    }*/

}
