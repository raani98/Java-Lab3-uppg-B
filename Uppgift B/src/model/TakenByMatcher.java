package model;

public class TakenByMatcher implements ITaskMatcher {
    private final String takenBy;

    public TakenByMatcher(String takenBy) {
        this.takenBy = takenBy;
    }

    @Override
    public boolean match(Task task) {
        return task.takenBy != null && task.takenBy.equalsIgnoreCase(takenBy);
    }
}
