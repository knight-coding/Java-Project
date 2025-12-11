package Model;

public class tasks {
    private int id;
    private String task;
    private String status;

    public tasks(int id, String task, String status) {
        this.id = id;
        this.task = task;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getTask() {
        return task;
    }

    public String getStatus() {
        return status;
    }
}
