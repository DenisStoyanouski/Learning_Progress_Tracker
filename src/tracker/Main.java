package tracker;

import java.util.*;

public class Main {

    static String lastName;
    static String firstName;
    static String email;

    private static Map<Integer, Student> studentList = new HashMap<>();
    private static Deque<Integer> idList = new ArrayDeque<>();

    public static void main(String[] args) {
        startMenu();
    }

    private static void startMenu() {
        System.out.println("Learning Progress Tracker");
        String a;
        do {
            a = input().strip();
            if (a.isBlank()) {
                System.out.println("No input");
            } else if ("add students".equals(a)) {
                addStudents();
            } else if ("back".equals(a)){
                System.out.println("Enter 'exit' to exit the program.");
            } else if ("list".equals(a)) {
                printIdList();
            } else if ("add points".equals(a)) {
                addPoints();
            } else if ("find".equals(a)) {
                findStudent();
            } else if (!"exit".equals(a)) {
                System.out.println("Unknown command!");
            }
        } while(!"exit".equals(a));
        System.out.println("Bye!");
    }

    private static String input() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    private static void addStudents() {
        String command;
        System.out.println("Enter student credentials or 'back' to return:");
        do {
            command = input();
            if ("back".equals(command)) {
                System.out.printf("Total %d students have been added.%n", studentList.size());
            } else {
                if (isCorrectCredentials(getCredentials(command))) {
                    addStudentToList(firstName, lastName,email);
                }
            }
        } while (!"back".equals(command));
    }

    protected static String[] getCredentials(String credentials) {
        String[] credentialsOfStudent = credentials.split("\\s+");
       return credentialsOfStudent;
    }

    protected static boolean isCorrectCredentials(String[] credentialsOfStudent) {
        boolean isCorrectCredentials = false;

        if (credentialsOfStudent.length < 3) {
            System.out.println("Incorrect credentials");
        } else {
            firstName = credentialsOfStudent[0];
            email = credentialsOfStudent[credentialsOfStudent.length - 1];
            StringBuilder lastNameB = new StringBuilder();
            for (int i = 1; i <= credentialsOfStudent.length - 2; i++) {
                lastNameB.append(credentialsOfStudent[i]);
                lastNameB.append(" ");
            }
            lastName = lastNameB.deleteCharAt(lastNameB.length() - 1).toString();
            isCorrectCredentials = isFirstNameCorrect(firstName) && isLastNameCorrect(lastName) && isEmailCorrect(email);
        }

        return isCorrectCredentials;
    }

    protected static void addStudentToList(String firstName, String lastName, String email) {
        Student student = new Student(firstName, lastName, email);
        int id = 10000;
        if (!studentList.containsValue(student)) {
            while(studentList.containsKey(id)) {
                id++;
            }
            studentList.put(id, student);
            idList.add(id);
            System.out.println("The student has been added.");
        } else {
            System.out.println("This email is already taken.");
        }
    }

    protected static void printIdList() {
        System.out.println("Students:");
        while (!idList.isEmpty()) {
            System.out.println(idList.pollFirst());
        }
    }

    protected static void addPoints() {
        System.out.println("Enter an id and points or 'back' to return:");
        String[] points;
        do {
            points = input().split("\\s+");
            if ("back".equals(points[0])) {
                break;
            } else {
                if(checkPoints(points)) {

                }
            }

        } while (!"back".equals(points[0]));

    }

    protected static boolean checkPoints(String[] points) {
        return isPointsFormatCorrect(points) && isIdExist(points[0]);
    }
    protected static boolean isPointsFormatCorrect(String[] points) {
        boolean isCorrect = false;
        for (int i = 1; i < points.length; i++) {
            try {
                int point = Integer.parseInt(points[i]);
                if (point < 0) {
                    System.out.println("Incorrect points format.");
                } else {
                    isCorrect = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Incorrect points format.");
            }
        }
        return isCorrect;
    }


    protected static boolean isIdExist(String id) {
        boolean isIdExist = false;
        int checkId = Integer.parseInt(id);
        if (studentList.containsKey(checkId)) {
            isIdExist = true;
        }
        return isIdExist;
    }

    protected static void findStudent() {
        System.out.println("Enter an id and points or 'back' to return:");
        String line;
        do {
            line = input();
            if ("add".equals(line)) {}
        } while (!"back".equals(line));

    }

    /*Accept only ASCII characters, from A to Z and from a to z as well as hyphens and apostrophes,
    at least two characters long, hyphens and apostrophes - not at the first or the last character,
    cannot be adjacent to each other.*/
    protected static boolean isFirstNameCorrect(String firstName) {
        boolean isFirstNameCorrect = false;
        if (firstName.matches("\\b[A-Za-z]+([A-Za-z]*[-']?[A-Za-z]+)+\\b")) {
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
        if (email.matches("[\\w[^@]]{1,63}@[\\w[^@]]{1,192}[.][\\w[^@]]{1,63}")) {
            isEmailCorrect = true;
        } else {
            System.out.println("Incorrect email.");
        }
        return isEmailCorrect;
    }
}

class Student {
    String firstName;
    String lastName;
    String email;

    public Student(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    //Check if the provided email has been already used when adding information about students.

    @Override
    public int hashCode() {
        return Double.valueOf(email).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Student) {
            Student student =  (Student)obj;
            return this.email.equals(student.email);
        }
        return false;
    }
}
