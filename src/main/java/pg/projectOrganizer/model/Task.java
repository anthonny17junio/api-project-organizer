package pg.projectOrganizer.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Task {
    private long id;
    private String name;
    private String description;
    private long projectId;

    @JsonCreator
    public Task(@JsonProperty("id") long id, @JsonProperty("name") String name, @JsonProperty("description") String description,@JsonProperty("projectId") long projectId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.projectId = projectId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }
}
