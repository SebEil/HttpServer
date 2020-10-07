package workers;

import org.postgresql.ds.PGSimpleDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WorkerDao {
    public static void main(String[] args) throws SQLException {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/workerlist");
        dataSource.setUser("workerlistuser");
        dataSource.setPassword("baretesterdette");

        Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("select * from workers");
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            String workerName = rs.getString("worker_name");
            System.out.println(workerName);
        }
    }
}
