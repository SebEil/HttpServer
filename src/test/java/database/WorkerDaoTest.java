package database;

import org.flywaydb.core.Flyway;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Random;
import static org.assertj.core.api.Assertions.assertThat;

public class WorkerDaoTest {

    @Test
    void shouldListInsertedWorkers() throws SQLException {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setUrl("jdbc:h2:mem:testdatabase;DB_CLOSE_DELAY=-1");
        Flyway.configure().dataSource(dataSource).load().migrate();


        WorkerDao workerDao = new WorkerDao(dataSource);
        String worker = exampleWorker();
        workerDao.insert(worker);
        assertThat(workerDao.list()).contains(worker);

    }

    private String exampleWorker() {
        String[] options = {"Zid", "Seb", "Baal", "Tommy", "Jerry"};
        Random random = new Random();
        return options[random.nextInt(options.length)];
    }

}
