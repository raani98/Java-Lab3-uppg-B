package model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to handle a list of all projects.
 * <p>
 * It can add, remove, find and get projects by title or ID.
 * It also makes sure that every project gets a unique title and ID number.
 * </p>
 *
 * @author Naher Islam & Vanessa Rådström
 * @version 1.0
 */
public class ProjectsManager {
    private int nextProjectId;
    private final List<Project> projects;

    /**
     * Creates a new ProjectsManager object.
     * <p>
     * When created, it starts with an empty list of projects
     * and sets the first available project ID to 1.
     * </p>
     */
    public ProjectsManager()
    {
        this.projects = new ArrayList<>();
        this.nextProjectId = 1;
    }


    /**
     * Checks if a given project title is unique.
     * <p>
     * Goes through all existing projects and compares their titles
     * with the one given by the user, ignoring uppercase and lowercase
     * differences.
     * </p>
     *
     * @param title the title to check
     * @return true if the title does not already exist, false otherwise
     */

    public boolean isTitleUnique(String title)
    {
        for(Project p : projects)
        {
            if(p.getTitle().equalsIgnoreCase(title))
            {
                return false;
            }
        }
        return true;

    }

    /**
     * Returns the list of all projects currently managed by this ProjectsManager.
     *
     * @return a list containing all existing Project objects
     */
    public List<Project> getProjects() {
        return projects;
    }

    /**
     * Replaces the current list of projects with a new list.
     * <p>
     * This method is usually used when loading saved projects from a file.
     * It clears the current list and adds all projects from the given list.
     * It also updates the next project ID to continue numbering correctly.
     *
     * @param incomingProjects the list of projects to replace the current list with
     * @return true if the list was replaced successfully, false if the provided list was null
     */
    public boolean setProjects(List<Project> incomingProjects) {
        if (incomingProjects == null) {
            return false;
        }

        projects.clear();
        projects.addAll(incomingProjects);

        nextProjectId = getHighestId() + 1;

        return true;
    }

    /**
     * Finds the highest project ID among all projects in the list.
     * <p>
     * This helps ensure that new projects get unique IDs when added.
     *
     * @return the highest project ID found in the list
     */
    public int getHighestId() {
        int highest = 0;

        for (Project p : projects) {
            if (p.getId() > highest) {
                highest = p.getId();
            }
        }

        return highest;
    }

    /**
     * Removes a project from the list of projects.
     *
     * @param project the project to remove
     * @return true if the project was successfully removed, false otherwise
     */
    public boolean removeProject(Project project) {
        return projects.remove(project);
    }

    /**
     * Finds and returns a project by its unique ID.
     *
     * @param id the ID of the project to find
     * @return the project with the matching ID, or null if not found
     */
    public Project getProjectById(int id) {
        for (Project p : projects) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    /**
     * Adds a new project with a title and descr.
     * <p>
     * If the title already exists, a TitleNotUniqueException is thrown.
     *
     * @param title the title of the new project
     * @param descr a short descr of the project
     * @return the new project that was created
     * @throws TitleNotUniqueException if the title already exists
     */
    public Project addProject(String title, String descr)
    {
        if(!isTitleUnique(title))
        {
            throw new TitleNotUniqueException("A project with this title already exists: " + title);
        }
        Project newProject = new Project(title, descr, nextProjectId);
        projects.add(newProject);
        nextProjectId++;
        return newProject;

    }

    /**
     * Finds all projects whose titles contain the given search text.
     *
     * @param title the search text to look for in project titles
     * @return a list of matching projects, or an empty list if none are found
     */
    public List<Project> findProjects(String title) {
        List<Project> result = new ArrayList<>();

        for (Project p : projects) {
            if (p.getTitle().toLowerCase().contains(title.toLowerCase())) {
                result.add(p);
            }
        }

        return result;
    }


}
