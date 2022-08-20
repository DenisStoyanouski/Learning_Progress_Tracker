package tracker;

import java.util.Scanner;

public class Main {
    final static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Learning Progress Tracker");
        String a;
        do{
            a = sc.nextLine().strip();
            if (a.isBlank()) {
                System.out.println("No input");
            } else if(!"exit".equals(a)) {
                System.out.println("Unknown command!");
            }
        } while(!"exit".equals(a));
        System.out.println("Bye!");
    }

}
