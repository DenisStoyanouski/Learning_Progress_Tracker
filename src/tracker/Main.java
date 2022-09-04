package tracker;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

import static java.math.RoundingMode.HALF_UP;

public class Main {

    static String lastName;
    static String firstName;
    static String email;

    private static SortedMap<Integer, Student> studentList = new TreeMap<>();
    private static Deque<Integer> idList = new ArrayDeque<>();

    private static final List<String> typeOfCourses = List.of("Java", "DSA", "Databases", "Spring");

    private static List<Course> Courses = new ArrayList<>();

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
                createListOfCourses();
                addStudents();
            } else if ("statistics".equals(a)) {
                printStatistics();
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

    private static void createListOfCourses() {
        for (String course : typeOfCourses) {
            Courses.add(new Course(course));
        }
    }

    private static void printStatistics() {
        System.out.println("Type the name of a course to see details or 'back' to quit:");
        System.out.printf("Most popular: %s%n", isMostPopular());
        System.out.printf("Least popular: %s%n", isLeastPopular());
        System.out.printf("Highest activity: %s%n", hasHighestActivity());
        System.out.printf("Lowest activity: %s%n", hasLowestActivity());
        System.out.printf("Easiest course: %s%n", isEasiestCourse());
        System.out.printf("Hardest course: %s%n", isHardestCourse());
        String command;
        do {
            command = input();
            switch (command) {
                case "java":
                    printCourseStat("java");
                    break;
                case "dsa":
                    printCourseStat("DSA");
                    break;
                case "databases":
                    printCourseStat("Databases");
                    break;
                case "spring":
                    printCourseStat("Spring");
                    break;
                case "back":
                    break;
                default:
                    System.out.println("Unknown course.");
            }
        } while (!"back".equals(command));
    }

    private static void printCourseStat(String course) {
        List<Stats> statsList = new ArrayList<>();
        System.out.println(course);
        System.out.println("id      points   completed");
        buildStatistic(course);


    }

    private static String isMostPopular() {
        if (Courses == null || Courses.size() == 0) {
            return "n/a";
        }
        Courses.sort(Comparator.comparing(Course::getNumberOfEnrolledStudents));
        int enroll = Courses.get(Courses.size() - 1).getNumberOfEnrolledStudents();
        StringBuilder courses = new StringBuilder();
        Courses.forEach(x -> {if (x.getNumberOfEnrolledStudents() == enroll) {
            courses.append(x.name).append(",").append(" ");
        }});
        courses.delete(courses.lastIndexOf(","), courses.length());
        return courses.toString();
    }

    private static String isLeastPopular() {
        if (Courses == null || Courses.size() == 0) {
            return "n/a";
        }
        Courses.sort(Comparator.comparing(Course::getNumberOfEnrolledStudents));
        int enroll = Courses.get(0).getNumberOfEnrolledStudents();
        StringBuilder courses = new StringBuilder();
        Courses.forEach(x -> {if (x.getNumberOfEnrolledStudents() == enroll) {
            courses.append(x.name).append(",").append(" ");
        }});
        courses.delete(courses.lastIndexOf(","), courses.length());
        return courses.toString();
    }

    private static String hasHighestActivity() {
        if (Courses == null || Courses.size() == 0) {
            return "n/a";
        }
        Courses.sort(Comparator.comparing(Course::getNumberOfCompletedTasks));
        int enroll = Courses.get(Courses.size() - 1).getNumberOfCompletedTasks();
        StringBuilder courses = new StringBuilder();
        Courses.forEach(x -> {if (x.getNumberOfCompletedTasks() == enroll) {
            courses.append(x.name).append(",").append(" ");
        }});
        courses.delete(courses.lastIndexOf(","), courses.length());
        return courses.toString();
    }

    private static String hasLowestActivity() {
        if (Courses == null || Courses.size() == 0) {
            return "n/a";
        }
        Courses.sort(Comparator.comparing(Course::getNumberOfCompletedTasks));
        int enroll = Courses.get(0).getNumberOfCompletedTasks();
        StringBuilder courses = new StringBuilder();
        Courses.forEach(x -> {if (x.getNumberOfCompletedTasks() == enroll) {
            courses.append(x.name).append(",").append(" ");
        }});
        courses.delete(courses.lastIndexOf(","), courses.length());
        return courses.toString();
    }

    private static String isEasiestCourse() {
        if (Courses == null || Courses.size() == 0) {
            return "n/a";
        }
        Courses.sort(Comparator.comparing(Course::getAverageGradePerAssignment));
        double minAverageGrade = Courses.get(Courses.size() - 1).getAverageGradePerAssignment();
        StringBuilder courses = new StringBuilder();
        Courses.forEach(x -> {if (x.getAverageGradePerAssignment() == minAverageGrade) {
            courses.append(x.name).append(",").append(" ");
        }});
        courses.delete(courses.lastIndexOf(","), courses.length());
        return courses.toString();
    }

    private static String isHardestCourse() {
        if (Courses == null || Courses.size() == 0) {
            return "n/a";
        }
        Courses.sort(Comparator.comparing(Course::getAverageGradePerAssignment));
        double maxAverageGrade = Courses.get(0).getAverageGradePerAssignment();
        StringBuilder courses = new StringBuilder();
        Courses.forEach(x -> {if (x.getAverageGradePerAssignment() == maxAverageGrade) {
            courses.append(x.name).append(",").append(" ");
        }});
        courses.delete(courses.lastIndexOf(","), courses.length());
        return courses.toString();
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
       return credentials.split("\\s+");
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
        if (idList.isEmpty()) {
            System.out.println("No students found.");
        } else {
            System.out.println("Students:");
            while (!idList.isEmpty()) {
                System.out.println(idList.pollFirst());
            }
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
                    updatePoints(points);
                }
            }
        } while (!"back".equals(points[0]));

    }

    protected static boolean checkPoints(String[] points) {
        return isPointsFormatCorrect(points) && isIdExist(points[0]);
    }
    protected static boolean isPointsFormatCorrect(String[] points) {
        boolean isCorrect = false;
        if (points == null || points.length != 5) {
            System.out.println("Incorrect points format.");
        } else {
            for (int i = 1; i < points.length; i++) {
                try {
                    int point = Integer.parseInt(points[i]);
                    if (point < 0 || point > 10) {
                        System.out.println("Incorrect points format.");
                        break;
                    } else {
                        isCorrect = true;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Incorrect points format.");
                    isCorrect = false;
                    break;
                }
            }
        }
        return isCorrect;
    }


    protected static boolean isIdExist(String id) {
        boolean isIdExist = false;
        try {
            int checkId = Integer.parseInt(id);
            if (studentList.containsKey(checkId)) {
                isIdExist = true;
            } else {
                System.out.printf("No student is found for id=%d.%n", checkId);
            }
        } catch (NumberFormatException e) {
            System.out.printf("No student is found for id=%s.%n", id);
        }

        return isIdExist;
    }

    protected static void updatePoints(String[] points) {
        int id = Integer.parseInt(points[0]);
        if (Integer.parseInt(points[1]) != 0) {
            studentList.get(id).addPointJava(Integer.parseInt(points[1]));
            Courses.get(0).addNumberOfEnrolledStudents(id);
            Courses.get(0).addNumberOfCompletedTasks();
            Courses.get(0).addNumberOfPoints(Integer.parseInt(points[1]));
        }
        if (Integer.parseInt(points[2]) != 0) {
            studentList.get(id).addPointDSA(Integer.parseInt(points[2]));
            Courses.get(1).addNumberOfEnrolledStudents(id);
            Courses.get(1).addNumberOfCompletedTasks();
            Courses.get(1).addNumberOfPoints(Integer.parseInt(points[2]));
        }

        if (Integer.parseInt(points[3]) != 0) {
            studentList.get(id).addPointDB(Integer.parseInt(points[3]));
            Courses.get(2).addNumberOfEnrolledStudents(id);
            Courses.get(2).addNumberOfCompletedTasks();
            Courses.get(2).addNumberOfPoints(Integer.parseInt(points[3]));
        }

        if (Integer.parseInt(points[4]) != 0) {
            studentList.get(id).addPointSpring(Integer.parseInt(points[4]));
            Courses.get(3).addNumberOfEnrolledStudents(id);
            Courses.get(3).addNumberOfCompletedTasks();
            Courses.get(3).addNumberOfPoints(Integer.parseInt(points[4]));
        }

        System.out.println("Points updated.");
    }

    protected static void findStudent() {
        System.out.println("Enter an id or 'back' to return:");
        String line;
        do {
            line = input();
            if ("back".equals(line)) {
                break;
            } else {
                try {
                    int id = Integer.parseInt(line);
                    if (!studentList.containsKey(id)) {
                        System.out.printf("No student is found for id=%d.%n", id);
                    } else {
                        printPoints(id);
                    }
                } catch (NumberFormatException e) {
                    System.out.printf("No student is found for id=%s.%n", line);
                }
            }
        } while (!"back".equals(line));

    }

    protected static void printPoints(int id) {
        System.out.printf("%d points: Java=%d; DSA=%d; Databases=%d; Spring=%d%n",
                id,
                studentList.get(id).getPoints("java"),
                studentList.get(id).getPoints("dsa"),
                studentList.get(id).getPoints("databases"),
                studentList.get(id).getPoints("spring"));
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

    private static void buildStatistic(String course) {
        for (var entry : studentList.entrySet()) {
            if (entry.getValue().getPoints(course) > 0) {
                statsList.add(new Stats(entry.getKey(), entry.getValue().getPoints(course), entry.getValue().getCompleted(course)));
            }
        }
    }
}

class Student {
    String firstName;
    String lastName;
    String email;

    private int pointJava;
    private int pointDSA;
    private int pointDB;

    private int pointSpring;

    public Student(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public int getPoints(String course) {
        int points = 0;
        switch (course) {
            case "java" : points = pointJava;
            break;
            case "dsa" : points = pointDSA;
            break;
            case "databases" : points = pointDB;
            break;
            case "spring" : points = pointSpring;
            break;
            default: break;
        }
        return points;
    }

    public String getCompleted(String course) {
        BigDecimal completed = new BigDecimal((double) getPoints(course) / getMaxPointsForCourse(course)).setScale(1, HALF_UP);
        return completed + "%";
    }

    private int getMaxPointsForCourse(String course) {
        int maxPoints = 0;
        switch (course) {
            case "java" : maxPoints = 600;
                break;
            case "dsa" : maxPoints = 400;
                break;
            case "databases" : maxPoints = 480;
                break;
            case "spring" : maxPoints = 550;
                break;
            default: break;
        }
        return maxPoints;
    }

    public void addPointJava(int point) {
        pointJava += point;
        if(pointJava > getMaxPointsForCourse("java")) {
            pointJava = getMaxPointsForCourse("java");
        }
    }

    public void addPointDSA(int point) {
        pointDSA += point;
        if(pointDSA > getMaxPointsForCourse("dsa")) {
            pointDSA = getMaxPointsForCourse("dsa");
        }
    }

    public void addPointDB(int point) {
        pointDB += point;
        if(pointDB > getMaxPointsForCourse("database")) {
            pointDB = getMaxPointsForCourse("database");
        }
    }

    public void addPointSpring(int point) {
        pointSpring += point;
        if(pointSpring > getMaxPointsForCourse("spring")) {
            pointSpring = getMaxPointsForCourse("spring");
        }
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

class Course {
    final String name;
    private Set<Integer> numberOfEnrolledStudents = new HashSet<>();
    private int numberOfCompletedTasks;

    private int numberOfPoints;

    private double averageGradePerAssignment;

    public Course(String name) {
        this.name = name;
    }

    public Integer getNumberOfEnrolledStudents() {
        return numberOfEnrolledStudents.size();
    }

    public int getNumberOfCompletedTasks() {
        return numberOfCompletedTasks;
    }

    public double getAverageGradePerAssignment() {
        return numberOfCompletedTasks != 0 ? averageGradePerAssignment = (double) numberOfPoints / numberOfCompletedTasks : 0;
    }

    public void addNumberOfEnrolledStudents(int id) {
        numberOfEnrolledStudents.add(id);
    }

    public void addNumberOfCompletedTasks() {
        numberOfCompletedTasks++;
    }

    public void addNumberOfPoints(int points) {
        numberOfPoints += points;
    }
}

class Stats {

    int id;
    int points;
    String completed;

    public Stats(int id, int points, String completed) {
        this.id = id;
        this.points = points;
        this.completed = completed;
    }


}

