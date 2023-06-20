package tracker;

import java.math.BigDecimal;

import static java.math.RoundingMode.HALF_UP;

class Student {
    String firstName;
    String lastName;
    String email;

    private int pointJava;
    private int pointDSA;
    private int pointDB;
    private int pointSpring;

    private boolean notifyJava = false;
    private boolean notifyDSA = false;
    private boolean notifyDatabases = false;
    private boolean notifySpring = false;

    public Student(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public int getPoints(String course) {
        int points = 0;
        switch (course) {
            case "java":
                points = pointJava;
                break;
            case "dsa":
                points = pointDSA;
                break;
            case "databases":
                points = pointDB;
                break;
            case "spring":
                points = pointSpring;
                break;
            default:
                break;
        }
        return points;
    }

    public String getCompleted(String course) {
        BigDecimal completed = BigDecimal.valueOf((double) getPoints(course) / getMaxPointsForCourse(course) * 100).setScale(1, HALF_UP);
        return completed + "%";
    }

    public int getMaxPointsForCourse(String course) {
        int maxPoints = 0;
        switch (course) {
            case "java":
                maxPoints = 600;
                break;
            case "dsa":
                maxPoints = 400;
                break;
            case "databases":
                maxPoints = 480;
                break;
            case "spring":
                maxPoints = 550;
                break;
            default:
                break;
        }
        return maxPoints;
    }

    public void addPointJava(int point) {
        pointJava += point;
        if (pointJava > getMaxPointsForCourse("java")) {
            pointJava = getMaxPointsForCourse("java");
        }
    }

    public void addPointDSA(int point) {
        pointDSA += point;
        if (pointDSA > getMaxPointsForCourse("dsa")) {
            pointDSA = getMaxPointsForCourse("dsa");
        }
    }

    public void addPointDB(int point) {
        pointDB += point;
        if (pointDB > getMaxPointsForCourse("databases")) {
            pointDB = getMaxPointsForCourse("databases");
        }
    }

    public void addPointSpring(int point) {
        pointSpring += point;
        if (pointSpring > getMaxPointsForCourse("spring")) {
            pointSpring = getMaxPointsForCourse("spring");
        }
    }

    public void setNotifiedCourse(String course) {
        switch (course) {
            case "java":
                this.notifyJava = true;
                break;
            case "dsa":
                this.notifyDSA = true;
                break;
            case "databases":
                this.notifyDatabases = true;
                break;
            case "spring":
                this.notifySpring = true;
            default:
                break;

        }
    }

    public boolean isNotifiedCourse(String course) {
        boolean isNotified = false;
        switch (course) {
            case "java":
                isNotified = notifyJava;
                break;
            case "dsa":
                isNotified = notifyDSA;
                break;
            case "databases":
                isNotified = notifyDatabases;
                break;
            case "spring":
                isNotified = notifySpring;
            default:
                break;
        }
        return isNotified;
    }

    //Check if the provided email has been already used when adding information about students.

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof tracker.Student) {
            tracker.Student student = (tracker.Student) obj;
            return this.email.equals(student.email);
        }
        return false;
    }
}
