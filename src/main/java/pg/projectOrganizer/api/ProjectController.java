package pg.projectOrganizer.api;

import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import pg.projectOrganizer.model.Project;
import pg.projectOrganizer.service.ProjectServiceImpl;

import javax.inject.Inject;
import java.util.List;

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/project")
public class ProjectController {

    private final ProjectServiceImpl crudService;

    @Inject
    public ProjectController(ProjectServiceImpl crudService) {
        this.crudService = crudService;
    }

    @Get("/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Project> list(long userId) {
        return crudService.getProjects(userId);
    }

    @Post()
    @Consumes(MediaType.APPLICATION_JSON)
    public HttpStatus insertProject(@Body Project project) {
        return crudService.insertProject(project) ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
    }

    @Put()
    @Consumes(MediaType.APPLICATION_JSON)
    public HttpStatus update(@Body Project project) {
        return crudService.updateProject(project) ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
    }

    @Delete("/{projectId}")
    public HttpStatus delete(long projectId) {
        return crudService.deleteProject(projectId) ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
    }
}