package pg.projectOrganizer.service;

import io.reactiverse.reactivex.pgclient.PgIterator;
import io.reactiverse.reactivex.pgclient.PgPool;
import io.reactiverse.reactivex.pgclient.Row;
import pg.projectOrganizer.dataAccess.PostgreSQLClient;
import pg.projectOrganizer.model.User;

import javax.inject.Singleton;

@Singleton
public class UserServiceImpl implements UserService {

    private PgPool client = PostgreSQLClient.getInstance();

    @Override
    public User getUser(long userId) {
        User user = null;
        PgIterator pgIterator = client.rxPreparedQuery("SELECT * FROM public.user WHERE id=" + userId).blockingGet().iterator();
        //noinspection LoopStatementThatDoesntLoop
        while (pgIterator.hasNext()) {
            Row row = pgIterator.next();
            user = new User(row.getLong("id"), row.getString("username"), null);
            break;
        }
        return user;
    }
}
