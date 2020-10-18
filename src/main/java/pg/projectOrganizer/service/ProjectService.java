package pg.projectOrganizer.service;

import pg.projectOrganizer.model.Project;

import java.util.List;

public interface ProjectService {

    List<Project> getProjects(long userId);
    boolean insertProject(Project project);
    boolean updateProject(Project project);
    boolean deleteProject(long projectId);
}
