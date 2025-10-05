package model;

import java.util.ArrayList;
import java.util.List;

public class ProjectsManager {
    private int nextProjectId;
    private final List<Project> projects;

    public ProjectsManager()
    {
        this.projects = new ArrayList<>();
        this.nextProjectId = 1;
    }

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

    public List<Project> getProjects() {
        return projects;
    }

    public boolean setProjects(List<Project> incomingProjects) {
        if (incomingProjects == null) {
            return false;
        }

        projects.clear();
        projects.addAll(incomingProjects);

        nextProjectId = getHighestId() + 1;

        return true;
    }


    public int getHighestId() {
        int highest = 0;

        for (Project p : projects) {
            if (p.getId() > highest) {
                highest = p.getId();
            }
        }

        return highest;
    }

    public boolean removeProject(Project project) {
        return projects.remove(project);
    }

    public Project getProjectById(int id) {
        for (Project p : projects) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }


    public Project addProject(String title, String description)
    {
        if(!isTitleUnique(title))
        {
            throw new TitleNotUniqueException("A project with this title already exists: " + title);
        }
        Project newProject = new Project(title, description, nextProjectId);
        projects.add(newProject);
        nextProjectId++;
        return newProject;

    }

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
