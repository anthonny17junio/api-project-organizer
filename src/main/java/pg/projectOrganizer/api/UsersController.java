package pg.projectOrganizer.api;

import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import pg.projectOrganizer.model.User;
import pg.projectOrganizer.service.ProjectServiceImpl;
import pg.projectOrganizer.service.UserServiceImpl;

import javax.inject.Inject;

@Secured(SecurityRule.IS_AUTHENTICATED)
@Controller("/users")
public class UsersController {

    private final UserServiceImpl crudService;

    @Inject
    public UsersController(UserServiceImpl crudService) {
        this.crudService = crudService;
    }


    @Get("/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public User index(long userId) {
        return crudService.getUser(userId);
    }
}