package tracker;

import java.util.*;


public class Main {

    static String lastName;
    static String firstName;
    static String email;

    private static SortedMap<Integer, Student> studentList = new TreeMap<>();
    private static final Deque<Integer> idList = new ArrayDeque<>();

    private static final List<String> typeOfCourses = List.of("Java", "DSA", "Databases", "Spring");

    private static List<Course> Courses = new ArrayList<>();

    private static final Set<Student> notifiedStudents = new HashSet<>();

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
            } else if ("back".equals(a)) {
                System.out.println("Enter 'exit' to exit the program.");
            } else if ("list".equals(a)) {
                printIdList();
            } else if ("add points".equals(a)) {
                addPoints();
            } else if ("find".equals(a)) {
                findStudent();
            } else if ("notify".equals(a)) {
                sendNotification();
            } else if (!"exit".equals(a)) {
                System.out.println("Unknown command!");
            }
        } while (!"exit".equals(a));
        System.out.println("Bye!");
    }

    private static void createListOfCourses() {
        for (String course : typeOfCourses) {
            Courses.add(new Course(course));
        }
    }

    private static void printStatistics() {
        System.out.println("Type the name of a course to see details or 'back' to quit:");
        System.out.printf("Most popular: %s%n", isMostPopular().toString().replaceAll("[\\[\\]]", ""));
        System.out.printf("Least popular: %s%n", isLeastPopular().toString().replaceAll("[\\[\\]]", ""));
        System.out.printf("Highest activity: %s%n", hasHighestActivity().toString().replaceAll("[\\[\\]]", ""));
        System.out.printf("Lowest activity: %s%n", hasLowestActivity().toString().replaceAll("[\\[\\]]", ""));
        System.out.printf("Easiest course: %s%n", isEasiestCourse().toString().replaceAll("[\\[\\]]", ""));
        System.out.printf("Hardest course: %s%n", isHardestCourse().toString().replaceAll("[\\[\\]]", ""));
        String command;
        do {
            command = input().toLowerCase();
            switch (command) {
                case "java":
                    printCourseStat("java");
                    break;
                case "dsa":
                    printCourseStat("dsa");
                    break;
                case "databases":
                    printCourseStat("databases");
                    break;
                case "spring":
                    printCourseStat("spring");
                    break;
                case "back":
                    break;
                default:
                    System.out.println("Unknown course.");
            }
        } while (!"back".equals(command));
    }

    private static ArrayList<String> isMostPopular() {
        ArrayList<String> mostPopularCourses = new ArrayList<>();
        if (Courses == null || Courses.size() == 0) {
            mostPopularCourses.add("n/a");
            return mostPopularCourses;
        }
        Courses.sort(Comparator.comparing(Course::getNumberOfEnrolledStudents));
        int enroll = Courses.get(Courses.size() - 1).getNumberOfEnrolledStudents();
        Courses.forEach(x -> {
            if (x.getNumberOfEnrolledStudents() == enroll) {
                mostPopularCourses.add(x.name);
            }
        });
        return mostPopularCourses;
    }

    private static ArrayList<String> isLeastPopular() {
        ArrayList<String> leastPopularCourses = new ArrayList<>();
        if (Courses == null || Courses.size() == 0) {
            leastPopularCourses.add("n/a");
            return leastPopularCourses;
        }
        Courses.sort(Comparator.comparing(Course::getNumberOfEnrolledStudents));
        int enroll = Courses.get(0).getNumberOfEnrolledStudents();
        Courses.forEach(x -> {
            if (x.getNumberOfEnrolledStudents() == enroll) {
                leastPopularCourses.add(x.name);
            }
        });
        leastPopularCourses.removeAll(isMostPopular());
        if (leastPopularCourses.isEmpty()) {
            leastPopularCourses.add("n/a");
        }
        return leastPopularCourses;
    }

    private static ArrayList<String> hasHighestActivity() {
        ArrayList<String> highestActivityCourses = new ArrayList<>();
        if (Courses == null || Courses.size() == 0) {
            highestActivityCourses.add("n/a");
            return highestActivityCourses;
        }
        Courses.sort(Comparator.comparing(Course::getNumberOfCompletedTasks));
        int enroll = Courses.get(Courses.size() - 1).getNumberOfCompletedTasks();
        Courses.forEach(x -> {
            if (x.getNumberOfCompletedTasks() == enroll) {
                highestActivityCourses.add(x.name);
            }
        });
        return highestActivityCourses;
    }

    private static ArrayList<String> hasLowestActivity() {
        ArrayList<String> hardestCourses = new ArrayList<>();

        if (Courses == null || Courses.size() == 0) {
            hardestCourses.add("n/a");
            return hardestCourses;
        }

        Courses.sort(Comparator.comparing(Course::getNumberOfCompletedTasks));
        int enroll = Courses.get(0).getNumberOfCompletedTasks();
        Courses.forEach(x -> {
            if (x.getNumberOfCompletedTasks() == enroll) {
                hardestCourses.add(x.name);
            }
        });

        hardestCourses.removeAll(hasHighestActivity());
        if (hardestCourses.isEmpty()) {
            hardestCourses.add("n/a");
        }
        return hardestCourses;
    }

    private static ArrayList<String> isEasiestCourse() {
        ArrayList<String> easiestCourses = new ArrayList<>();
        if (Courses == null || Courses.size() == 0) {
            easiestCourses.add("n/a");
            return easiestCourses;
        }
        Courses.sort(Comparator.comparing(Course::getAverageGradePerAssignment));
        double minAverageGrade = Courses.get(Courses.size() - 1).getAverageGradePerAssignment();
        Courses.forEach(x -> {
            if (x.getAverageGradePerAssignment() == minAverageGrade) {
                easiestCourses.add(x.name);
            }
        });
        return easiestCourses;
    }

    private static ArrayList<String> isHardestCourse() {
        ArrayList<String> hardestCourses = new ArrayList<>();

        if (Courses == null || Courses.size() == 0) {
            hardestCourses.add("n/a");
            return hardestCourses;
        }

        Courses.sort(Comparator.comparing(Course::getAverageGradePerAssignment));
        double maxAverageGrade = Courses.get(0).getAverageGradePerAssignment();
        Courses.forEach(x -> {
            if (x.getAverageGradePerAssignment() == maxAverageGrade) {
                hardestCourses.add(x.name);
            }
        });

        hardestCourses.removeAll(isEasiestCourse());
        if (hardestCourses.isEmpty()) {
            hardestCourses.add("n/a");
        }
        return hardestCourses;
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
                    addStudentToList(firstName, lastName, email);
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
            while (studentList.containsKey(id)) {
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
                if (checkPoints(points)) {
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
                    if (point < 0) {
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

    private static void printCourseStat(String course) {
        System.out.println(String.valueOf(course.charAt(0)).toUpperCase() + course.substring(1));
        System.out.println("id  points  completed");
        createStatistic(course);
    }

    private static void createStatistic(String course) {
        ArrayList<Stats> statsList = new ArrayList<>();
        for (var entry : studentList.entrySet()) {
            if (entry.getValue().getPoints(course) > 0) {
                statsList.add(new Stats(entry.getKey(), entry.getValue().getPoints(course), entry.getValue().getCompleted(course)));
            }
        }
        sortList(statsList);
    }

    private static void sortList(ArrayList<Stats> statsList) {
        statsList.sort(Comparator.comparing(Stats::getPoints).reversed().thenComparing(Stats::getId));
        printStatsList(statsList);
    }

    private static void printStatsList(ArrayList<Stats> statsList) {
        for (Stats stats : statsList) {
            System.out.printf("%d     %d      %s%n", stats.getId(), stats.getPoints(), stats.getCompleted());
        }
        statsList.clear();
    }

    private static void sendNotification() {

        for (Student student : studentList.values()) {
            for (String course : typeOfCourses) {
                if (student.getPoints(course.toLowerCase()) == student.getMaxPointsForCourse(course.toLowerCase())
                        && !student.isNotifiedCourse(course.toLowerCase())) {
                    System.out.printf("To: %s%n", student.email);
                    System.out.println("Re: Your Learning Progress");
                    System.out.printf("Hello, %s %s! You have accomplished our %s course!%n", student.firstName, student.lastName, course);
                    student.setNotifiedCourse(course.toLowerCase());
                    notifiedStudents.add(student);
                }
            }
        }
        System.out.printf("Total %d students have been notified.%n", notifiedStudents.size());
        notifiedStudents.clear();
    }
}