package model;

import java.io.Serializable;
import java.time.LocalDate;


public class Task implements Comparable<Task>, Serializable  {
    private final int id;
    private String description;
    private TaskPrio prio;
    private TaskState state;
    private String takenBy;
    private LocalDate lastUpdated;

    public Task(int id, String descr, TaskPrio prio)
    {
        this.id = id;
        this.description = descr;
        this.prio = prio;
        this.state = TaskState.TO_DO;
        this.lastUpdated = LocalDate.now();

    }

    public int getId()
    {
        return id;
    }

    public String getDescription()
    {
        return description;
    }

    public TaskPrio getPrio()
    {
        return prio;
    }

    public TaskState getState()
    {
        return state;
    }

    public LocalDate getLastUpdated() {
        return lastUpdated;
    }

    public String getTakenBy()
    {
        return takenBy;
    }

    public void setTakenBy(String takenBy)
    {
        if(this.takenBy != null)
        {
            throw new IllegalStateException("Not possible");
        }

        this.takenBy = takenBy;

        lastUpdated = LocalDate.now();

    }

    public void setState(TaskState state)
    {
        this.state = state;
        lastUpdated = LocalDate.now();
    }

    public void setPrio(TaskPrio prio)
    {
        this.prio = prio;
        lastUpdated = LocalDate.now();
    }

    @Override
    public int compareTo(Task other)
    {
        int result = this.prio.compareTo(other.prio);
        if(result !=0)
        {
            return result;
        }
        return this.description.compareTo(other.description);
    }

    @Override
    public String toString() {
        return String.format(
                "  [Task ID: %d | Description: %s | Prio: %s | State: %s | Taken by: %s | Last updated: %s]",
                id, description, prio, state, takenBy == null ? "-" : takenBy, lastUpdated
        );
    }
}
