package pg.projectOrganizer.service;

import io.reactiverse.reactivex.pgclient.PgIterator;
import io.reactiverse.reactivex.pgclient.PgPool;
import io.reactiverse.reactivex.pgclient.PgRowSet;
import io.reactiverse.reactivex.pgclient.Row;
import io.reactiverse.reactivex.pgclient.Tuple;
import pg.projectOrganizer.dataAccess.PostgreSQLClient;
import pg.projectOrganizer.model.Task;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class TaskServiceImpl implements TaskService {

    private PgPool client = PostgreSQLClient.getInstance();

    @Override
    public List<Task> getTasks(long projectId) {
        List<Task> tasks = new ArrayList<>();
        PgIterator pgIterator = client.rxPreparedQuery("SELECT * FROM task WHERE id_project=" + projectId).blockingGet().iterator();
        while (pgIterator.hasNext()) {
            Row row = pgIterator.next();
            tasks.add(new Task(row.getLong("id"), row.getString("name"), row.getString("description"), row.getLong("id_project")));
        }
        return tasks;
    }

    @Override
    public boolean insertTask(Task task) {
        String sqlQuery = "INSERT INTO task (name, description, id_project) VALUES ($1, $2, $3)";
        client.preparedQuery(sqlQuery, Tuple.of(task.getName(), task.getDescription(), task.getProjectId()), ar -> {
            if (ar.succeeded()) {
                PgRowSet rows = ar.result();
                System.out.println(rows.rowCount());
            } else {
                System.out.println("Failure: " + ar.cause().getMessage());
            }
        });
        return true;
    }

    @Override
    public boolean updateTask(Task task) {
        String sqlQuery = "UPDATE task SET name=$1, description=$2 WHERE id=$3";
        client.preparedQuery(sqlQuery, Tuple.of(task.getName(), task.getDescription(), task.getId()), ar -> {
            if (ar.succeeded()) {
                PgRowSet rows = ar.result();
                System.out.println(rows.rowCount());
            } else {
                System.out.println("Failure: " + ar.cause().getMessage());
            }
        });
        return true;
    }

    @Override
    public boolean deleteTask(long taskId) {
        client.preparedQuery("DELETE FROM task WHERE id=$1", Tuple.of(taskId), ar -> {
            if (ar.succeeded()) {
                PgRowSet rows = ar.result();
                System.out.println(rows.rowCount());
            } else {
                System.out.println("Failure: " + ar.cause().getMessage());
            }
        });
        return true;
    }
}
