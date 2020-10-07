package database;

import org.postgresql.ds.PGSimpleDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WorkerDao {

    private ArrayList<String> workers = new ArrayList<>();

    public static void main(String[] args) throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/workerlist");
        dataSource.setUser("workerlistuser");
        dataSource.setPassword("baretesterdette");

        System.out.println("Please enter name you wish to add to worker list");
        Scanner scanner = new Scanner(System.in);
        String workerName = scanner.nextLine();

        try(Connection connection = dataSource.getConnection()){
            try(PreparedStatement statement = connection.prepareStatement("INSERT INTO workers (worker_name) VALUES (?)")){
                statement.setString(1, workerName);
                statement.executeUpdate();
            }
        }

        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("select * from workers")) {
                try (ResultSet rs = statement.executeQuery()) {
                    while (rs.next()) {
                        String workerNameOutput = rs.getString("worker_name");
                        System.out.println(workerNameOutput);
                    }
                }
            }
        }
    }

    public void insert(String worker) {
        workers.add(worker);
    }

    public List<String> list() {
        return workers;
    }
}
