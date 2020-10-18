package pg.projectOrganizer.api;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.http.HttpStatus;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import pg.projectOrganizer.model.Task;
import pg.projectOrganizer.service.TaskServiceImpl;

import javax.inject.Inject;
import java.util.List;

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/task")
public class TaskController {

    private final TaskServiceImpl crudService;

    @Inject
    public TaskController(TaskServiceImpl crudService) {
        this.crudService = crudService;
    }

    @Get("/{projectId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Task> list(long projectId) {
        return crudService.getTasks(projectId);
    }

    @Post()
    @Consumes(MediaType.APPLICATION_JSON)
    public HttpStatus insertTask(@Body Task task) {
        return crudService.insertTask(task) ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
    }

    @Put()
    @Consumes(MediaType.APPLICATION_JSON)
    public HttpStatus update(@Body Task task) {
        return crudService.updateTask(task) ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
    }

    @Delete("/{taskId}")
    public HttpStatus delete(long taskId) {
        return crudService.deleteTask(taskId) ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
    }
}