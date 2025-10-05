package model;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Represents a single task inside a project.
 * <p>
 * A task has an ID, description, priority, state, who is responsible for it,
 * and the date when it was last updated.
 *
 * @author Naher Islam & Vanessa Rådström
 * @version 1.0
 */
public class Task implements Comparable<Task>, Serializable  {
    private final int id;
    private String description;
    private TaskPrio prio;
    private TaskState state;
    private String takenBy;
    private LocalDate lastUpdated;

    /**
     * Creates a new task with a specific ID, description, and priority.
     * <p>
     * The task starts in the TO_DO state and saves the current date as last updated.
     *
     * @param id the unique ID of the task
     * @param descr a short description of what the task is about
     * @param prio the priority level of the task
     */
    public Task(int id, String descr, TaskPrio prio)
    {
        this.id = id;
        this.description = descr;
        this.prio = prio;
        this.state = TaskState.TO_DO;
        this.lastUpdated = LocalDate.now();

    }

    /**
     * Returns the ID number of this task.
     *
     * @return the task ID
     */
    public int getId()
    {
        return id;
    }

    /**
     * Returns the text description of this task.
     *
     * @return the task description
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Returns the priority level of this task.
     *
     * @return the task priority
     */
    public TaskPrio getPrio()
    {
        return prio;
    }

    /**
     * Returns the current state of this task.
     * <p>
     * Possible states are TO_DO, IN_PROGRESS, or DONE.
     *
     * @return the current task state
     */
    public TaskState getState()
    {
        return state;
    }

    /**
     * Returns the date when the task was last updated.
     *
     * @return the last updated date
     */
    public LocalDate getLastUpdated() {
        return lastUpdated;
    }

    /**
     * Returns the name or email of the person responsible for this task.
     *
     * @return the name or email of the person assigned, or null if none
     */
    public String getTakenBy()
    {
        return takenBy;
    }

    /**
     * Assigns this task to a person.
     * <p>
     * Can only be done once — if already assigned, an exception is thrown.
     *
     * @param takenBy the name or email of the person taking the task
     * @throws IllegalStateException if the task is already taken
     */
    public void setTakenBy(String takenBy)
    {
        if(this.takenBy != null)
        {
            throw new IllegalStateException("Not possible");
        }

        this.takenBy = takenBy;

        lastUpdated = LocalDate.now();

    }

    /**
     * Changes the current state of this task.
     * <p>
     * Also updates the last updated date.
     *
     * @param state the new state of the task
     */
    public void setState(TaskState state)
    {
        this.state = state;
        lastUpdated = LocalDate.now();
    }

    /**
     * Updates the priority of this task.
     * <p>
     * Also updates the last updated date.
     *
     * @param prio the new priority level
     */
    public void setPrio(TaskPrio prio)
    {
        this.prio = prio;
        lastUpdated = LocalDate.now();
    }

    /**
     * Compares this task to another task by priority and then by description.
     * <p>
     * Tasks with higher priority come first.
     *
     * @param other the other task to compare with
     * @return a negative number if this task comes before, positive if after, or 0 if equal
     */
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

    /**
     * Returns a readable text with all task details.
     *
     * @return a formatted string with ID, description, priority, state, assignee, and last update date
     */
    @Override
    public String toString() {
        return String.format(
                "  [Task ID: %d | Description: %s | Prio: %s | State: %s | Taken by: %s | Last updated: %s]",
                id, description, prio, state, takenBy == null ? "-" : takenBy, lastUpdated
        );
    }
}
