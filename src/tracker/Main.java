package tracker;

import java.util.Scanner;

public class Main {

    static int addedNumberOfStudents;

    public static void main(String[] args) {
        System.out.println("Learning Progress Tracker");
        String a;
        do{
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
            }
        } while (!"back".equals(input));
    }
    /*private static boolean isStudent(String input) {

    }*/

}
