package tracker;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Learning Progress Tracker");
        String a;
        do{
            a = input();
            if (a.isBlank()) {
                System.out.println("No input");
            } else if(!"exit".equals(a)) {
                System.out.println("Unknown command!");
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

}
