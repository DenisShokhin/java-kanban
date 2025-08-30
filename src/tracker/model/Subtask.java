package tracker.model;

public class Subtask extends Task {
    private final int epicId;

    public Subtask(String name, String description, TaskStatus status, int epicId) {
        super(name, description, status);
        this.epicId = epicId;
    }

    public int getEpicId() {
        return epicId;
    }

    @Override
    public Task copy() {
        Subtask copy = new Subtask(this.getName(), this.getDescription(), this.getStatus(), this.getEpicId());
        copy.setId(this.getId());
        return copy;
    }

    @Override
    public String toString() {
        return "Subtask{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status.getDisplayName() +
                ", epicId=" + epicId +
                '}';
    }
}