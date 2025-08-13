package tracker.model;

public enum TaskStatus {
    NEW("To do"),
    IN_PROGRESS("In progress"),
    DONE("Done");

    private final String displayName;

    TaskStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
