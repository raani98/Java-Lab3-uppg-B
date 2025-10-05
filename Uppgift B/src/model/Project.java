package model;

import model.matcher.ITaskMatcher;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a project that contains tasks.
 * <p>
 * Each project has a title, description, creation date, and a list of tasks.
 * It also keeps track of its own ID and can be compared to other projects by title.
 *
 * @author Naher Islam & Vanessa Rådström
 * @version 1.0
 */
public class Project implements Comparable<Project>, Serializable {
    private final String title;
    private final int id;
    private String description;
    private final LocalDate created;
    private int nextTaskId;
    private final List<Task> tasks;

    /**
     * Creates a new project with a title, descr, and unique ID.
     * <p>
     * When a project is created, it gets the current date as its creation date
     * and starts with an empty list of tasks.
     *
     * @param title the name of the project
     * @param descr a short descr of what the project is about
     * @param id the unique ID for this project
     */
    public Project(String title, String descr, int id) {
        this.title = title;
        this.description = descr;
        this.id = id;
        this.created = LocalDate.now();
        this.nextTaskId = 1;
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a new task to this project.
     *
     * @param descr the descr of the task
     * @param prio the priority level of the task
     * @return the new task that was created
     */
    public Task addTask(String descr, TaskPrio prio) {
        Task newTask = new Task(nextTaskId, descr, prio);
        tasks.add(newTask);
        nextTaskId++;
        return newTask;
    }

    /**
     * Returns the title of this project.
     *
     * @return the project title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the unique ID of this project.
     *
     * @return the project ID
     */
    public int getId() {
        return id;
    }

    /**
     * Removes a specific task from the project.
     *
     * @param task the task to remove
     * @return true if the task was successfully removed, false otherwise
     */
    public boolean removeTask(Task task) {
        return tasks.remove(task);
    }

    /**
     * Finds a task in the project by its ID.
     *
     * @param id the ID of the task to find
     * @return the task with the given ID, or null if not found
     */
    public Task getTaskById(int id) {
        for (Task t : tasks) {
            if (t.getId() == id) {
                return t;
            }
        }
        return null;
    }

    /**
     * Finds all tasks that match a given condition.
     * <p>
     * Uses the ITaskMatcher interface to check which tasks should be included.
     *
     * @param matcher the rule used to match tasks
     * @return a list of tasks that match the condition
     */
    public List<Task> findTasks(ITaskMatcher matcher) {
        List<Task> result = new ArrayList<>();

        for (Task t : tasks) {
            if (matcher.match(t)) {
                result.add(t);
            }
        }
        return result;
    }

    /**
     * Determines the current state of the project.
     * <p>
     * A project can be EMPTY (no tasks), ONGOING (some tasks not done), or COMPLETED (all tasks done).
     *
     * @return the current state of the project
     */
    public ProjectState getState() {
        if (tasks.isEmpty()) {
            return ProjectState.EMPTY;
        }

        boolean allDone = true;
        for (Task t : tasks) {
            if (t.getState() != TaskState.DONE) {
                allDone = false;
                break;
            }
        }
        if (allDone) {
            return ProjectState.COMPLETED;
        }
        return ProjectState.ONGOING;
    }

    /**
     * Returns the most recent update date among all tasks in the project.
     *
     * @return the latest update date, or the creation date if there are no tasks
     */
    public LocalDate getLastUpdated() {
        if (tasks.isEmpty()) {
            return created;
        }

        LocalDate latest = created;

        for (Task t : tasks) {
            if (t.getLastUpdated().isAfter(latest)) {
                latest = t.getLastUpdated();
            }
        }
        return latest;
    }

    /**
     * Compares this project to another project by title.
     *
     * @param other the project to compare with
     * @return a negative number if this title comes before the other,
     *         zero if they are the same, or a positive number if after
     */
    @Override
    public int compareTo(Project other) {
        return this.title.compareTo(other.title);
    }

    /**
     * Returns a text version of the project details.
     *
     * @return a formatted string with ID, title, description, and creation date
     */
    @Override
    public String toString() {
        return "ID " + id + "\n" +
                "Title: " + title + "\n" +
                "Description: " + description + "\n" +
                "Created: " + created;
    }
}


