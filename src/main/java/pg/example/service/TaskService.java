package pg.example.service;

import pg.example.model.Task;

import java.util.List;

public interface TaskService {

    List<Task> getTasks(long projectId);
    boolean insertTask(Task task);
    boolean updateTask(Task task);
    boolean deleteTask(long userId);
}
