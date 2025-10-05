package model;

import model.matcher.ITaskMatcher;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Project implements Comparable<Project>, Serializable {
    private final String title;
    private final int id;
    private String description;
    private final LocalDate created;
    private int nextTaskId;
    private final List<Task> tasks;

    public Project(String title, String description, int id) {
        this.title = title;
        this.description = description;
        this.id = id;
        this.created = LocalDate.now();
        this.nextTaskId = 1;
        this.tasks = new ArrayList<>();
    }

    public Task addTask(String description, TaskPrio prio) {
        Task newTask = new Task(nextTaskId, description, prio);
        tasks.add(newTask);
        nextTaskId++;
        return newTask;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }


    public boolean removeTask(Task task) {
        return tasks.remove(task);
    }

    public Task getTaskById(int id) {
        for (Task t : tasks) {
            if (t.getId() == id) {
                return t;
            }
        }
        return null;
    }

    public List<Task> findTasks(ITaskMatcher matcher) {
        List<Task> result = new ArrayList<>();

        for (Task t : tasks) {
            if (matcher.match(t)) {
                result.add(t);
            }
        }
        return result;
    }

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

    @Override
    public int compareTo(Project other) {
        return this.title.compareTo(other.title);
    }

    @Override
    public String toString() {
        return "ID " + id + "\n" +
                "Title: " + title + "\n" +
                "Description: " + description + "\n" +
                "Created: " + created;
    }
}


