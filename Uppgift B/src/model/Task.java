package model;

import java.time.LocalDate;


public class Task implements Comparable<Task>  {
    final int id;
    String description;
    TaskPrio prio;
    TaskState state;
    String takenBy;
    LocalDate lastUpdated;

    public Task(int id, String description, TaskPrio prio)
    {
        this.id = id;
        this.description = description;
        this.prio = prio;
        this.state = TaskState.TO_DO;
        this.lastUpdated = LocalDate.now();

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
        return "Task{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", prio=" + prio +
                ", state=" + state +
                ", takenBy='" + takenBy + '\'' +
                ", lastUpdated=" + lastUpdated +
                '}';
    }
}
