package model.matcher;

import model.Task;

public class TakenByMatcher implements ITaskMatcher {
    private final String takenBy;

    public TakenByMatcher(String takenBy) {
        this.takenBy = takenBy;
    }

    @Override
    public boolean match(Task task) {
        return task.getTakenBy() != null && task.getTakenBy().equalsIgnoreCase(takenBy);
    }
}
