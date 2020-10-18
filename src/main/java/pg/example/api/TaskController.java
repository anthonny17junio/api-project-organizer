package pg.example.api;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.http.HttpStatus;
import pg.example.model.Task;
import pg.example.service.TaskServiceImpl;

import javax.inject.Inject;
import java.util.List;

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
    public HttpStatus save(@Body Task task) {
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