package tracker;

public class Stats {

    final int id;
    final int points;
    final String completed;

    public Stats(int id, int points, String completed) {
        this.id = id;
        this.points = points;
        this.completed = completed;
    }

    public int getPoints() {
        return points;
    }

    public int getId() {
        return id;
    }

    public String getCompleted() {
        return completed;
    }
}
