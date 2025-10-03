package Model;

import java.time.LocalDateTime;

public class Task {
    private final int id;
    private String description;
    private int prio;
    private TaskState state;
    private String takenBy;
    private LocalDateTime lastUpdated;

    public Task(int id, String description, int prio)
    {
        this.id = id;
        this.description = description;
        this.prio = prio;
        this.state = TaskState.TODO;
        this.lastUpdated = LocalDateTime.now();

    }

}
