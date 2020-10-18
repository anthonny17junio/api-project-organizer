package pg.projectOrganizer.service;

import io.reactiverse.reactivex.pgclient.*;
import pg.projectOrganizer.dataAccess.PostgreSQLClient;
import pg.projectOrganizer.model.Project;

import javax.inject.Singleton;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class ProjectServiceImpl implements ProjectService {
    private PgPool client = PostgreSQLClient.getInstance();

    @Override
    public List<Project> getProjects(long userId) {
        List<Project> projects = new ArrayList<>();
        PgIterator pgIterator = client.rxPreparedQuery("SELECT * FROM project WHERE id_user=" + userId).blockingGet().iterator();
        while (pgIterator.hasNext()) {
            Row row = pgIterator.next();
            URI uri = null;
            String strUri = row.getString("image_url");
            if (strUri != null) {
                uri = URI.create(strUri);
            }
            projects.add(new Project(row.getLong("id"), row.getString("name"), row.getString("description"), uri, row.getLong("id_user")));
        }
        return projects;
    }

    @Override
    public boolean insertProject(Project project) {
        String sqlQuery = "INSERT INTO project (name, description, image_url, id_user) VALUES ($1, $2, $3, $4)";
        String strUri = null;
        URI uri = project.getImageUrl();
        if (uri != null) {
            strUri = uri.toString();
        }

        client.preparedQuery(sqlQuery, Tuple.of(project.getName(), project.getDescription(), strUri, project.getUserId()), ar -> {
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
    public boolean updateProject(Project project) {
        String strUri = null;
        URI uri = project.getImageUrl();
        if (uri != null) {
            strUri = uri.toString();
        }

        String sqlQuery = "UPDATE project SET name=$1, description=$2, image_url=$3 WHERE id=$4";

        client.preparedQuery(sqlQuery, Tuple.of(project.getName(), project.getDescription(), strUri, project.getId()), ar -> {
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
    public boolean deleteProject(long projectId) {
        client.preparedQuery("DELETE FROM project WHERE id=$1", Tuple.of(projectId), ar -> {
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
