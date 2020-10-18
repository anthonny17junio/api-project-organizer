package pg.projectOrganizer.service;

import io.micronaut.security.authentication.AuthenticationFailed;
import io.micronaut.security.authentication.AuthenticationProvider;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import io.micronaut.security.authentication.UserDetails;
import io.reactiverse.reactivex.pgclient.PgIterator;
import io.reactiverse.reactivex.pgclient.PgPool;
import io.reactivex.Flowable;
import org.reactivestreams.Publisher;
import pg.projectOrganizer.dataAccess.PostgreSQLClient;

import javax.inject.Singleton;
import java.util.ArrayList;

@Singleton
public class AuthenticationProviderUserPassword implements AuthenticationProvider {

    @Override
    public Publisher<AuthenticationResponse> authenticate(AuthenticationRequest authenticationRequest) {
        PgPool client = PostgreSQLClient.getInstance();
        String username = authenticationRequest.getIdentity().toString();
        String pass = authenticationRequest.getSecret().toString();
        PgIterator pgIterator = client.rxPreparedQuery("SELECT * FROM public.user WHERE username='" + username + "' AND password='" + pass + "'").blockingGet().iterator();
        //noinspection LoopStatementThatDoesntLoop
        while (pgIterator.hasNext()) {
            UserDetails details = new UserDetails(username, new ArrayList<>());
            return Flowable.just(details);
        }
        return Flowable.just(new AuthenticationFailed());
    }
}