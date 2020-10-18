package pg.projectOrganizer.dataAccess;

import io.reactiverse.pgclient.PgPoolOptions;
import io.reactiverse.reactivex.pgclient.PgClient;
import io.reactiverse.reactivex.pgclient.PgPool;

public class PostgreSQLClient {
    private static PgPool client;

    public static PgPool getInstance() {
        if (client == null) {
            connect();
        }
        return client;
    }

    private static void connect() {
        PgPoolOptions options = new PgPoolOptions()
                .setPort(5432)
                .setHost("35.246.126.180")
                .setDatabase("ntthqsxs")
                .setUser("ntthqsxs")
                .setPassword("mjmxVUfRY_7coTaj_XkykkjjM1RmNNeT")
                .setMaxSize(4);
        client = PgClient.pool(options);
    }

}
