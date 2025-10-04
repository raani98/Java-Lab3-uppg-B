package model;

public class NotDoneMatcher implements ITaskMatcher {

    @Override
    public boolean match(Task task) {
        return task.state != TaskState.DONE;
    }
}