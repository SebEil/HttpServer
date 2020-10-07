package database;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class WorkerDaoTest {

    @Test
    void shouldListInsertedWorkers() {
        WorkerDao workerDao = new WorkerDao();
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
