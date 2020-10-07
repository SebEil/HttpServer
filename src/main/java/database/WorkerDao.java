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

        workerDao.insert(workerName);
        for (String worker : workerDao.list()) {
            System.out.println(worker);
        }
    }

    public void insert(String worker) throws SQLException {
        try(Connection connection = dataSource.getConnection()){
            try(PreparedStatement statement = connection.prepareStatement("INSERT INTO workers (worker_name)  VALUES (?)")){
                statement.setString(1, worker);
                statement.executeUpdate();
            }
        }
    }

    public List<String> list() throws SQLException {
        List<String> workers = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("select * from workers")) {
                try (ResultSet rs = statement.executeQuery()) {
                    while (rs.next()) {
                        workers.add(rs.getString("worker_name"));
                    }
                }
            }
        }
        return workers;
    }
}
