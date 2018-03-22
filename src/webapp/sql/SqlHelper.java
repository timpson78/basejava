package webapp.sql;

import webapp.exeption.StorageExeption;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlHelper {
    public final ConnectionFactory connectionFactory;

    public SqlHelper(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public void execute(String sqlQuery)
    {
        execute(sqlQuery, ps -> {
            ps.execute();
            return null;
        });
    }

    public <T> T execute (String sqlQuery, SqlExecutor<T> executor)
    {
        try {
            Connection conn = connectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(sqlQuery);
           return executor.execute(ps);

        } catch (SQLException e) {
            throw  SqlExeptionUtil.ConvertExeption(e);
        }
    }
}
