package tracker;

import java.util.HashSet;
import java.util.Set;

public class Course {

    final String name;
    private final Set<Integer> numberOfEnrolledStudents = new HashSet<>();
    private int numberOfCompletedTasks;

    private int numberOfPoints;

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
        return numberOfCompletedTasks != 0 ? (double) numberOfPoints / numberOfCompletedTasks : 0;
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
