package database;

import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WorkerDao {

    private DataSource dataSource;

    public WorkerDao(DataSource dataSource) {

        this.dataSource = dataSource;
    }

    public static void main(String[] args) throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/workerlist");
        dataSource.setUser("workerlistuser");
        dataSource.setPassword("baretesterdette");

        WorkerDao workerDao = new WorkerDao(dataSource);

        System.out.println("Please enter name you wish to add to worker list");
        Scanner scanner = new Scanner(System.in);
        String workerName = scanner.nextLine();


        Worker worker = new Worker();
        worker.setName(workerName);
        workerDao.insert(worker);
        for (Worker w : workerDao.list()) {
            System.out.println(worker);
        }
    }

    public void insert(Worker worker) throws SQLException {
        try(Connection connection = dataSource.getConnection()){
            try(PreparedStatement statement = connection.prepareStatement("INSERT INTO workers (worker_name)  VALUES (?)")){
                statement.setString(1, worker.getName());
                statement.executeUpdate();
            }
        }
    }

    public List<Worker> list() throws SQLException {
        List<Worker> workers = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("select * from workers")) {
                try (ResultSet rs = statement.executeQuery()) {
                    while (rs.next()) {
                        Worker worker = new Worker();
                        worker.setName(rs.getString("worker_name"));
                        workers.add(worker);
                    }
                }
            }
        }
        return workers;
    }
}
