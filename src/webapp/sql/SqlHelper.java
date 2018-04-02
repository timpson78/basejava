package webapp.sql;

import webapp.exeption.StorageExeption;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlHelper {
    public final ConnectionFactory connectionFactory;

    public SqlHelper(String dbUrl, String dbUser, String dbPassword) {
        this.connectionFactory = () -> DriverManager.getConnection(dbUrl, dbUser, dbPassword);
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
            throw  ExceptionUtil.ConvertExeption(e);
        }
    }


    public <T> T transactionalExecute(SqlTransaction<T> executor) {
        try (Connection conn = connectionFactory.getConnection()) {
            try {
                conn.setAutoCommit(false);
                T res = executor.execute(conn);
                conn.commit();
                return res;
            } catch (SQLException e) {
                conn.rollback();
                throw ExceptionUtil.ConvertExeption(e);
            }
        } catch (SQLException e) {
            throw new StorageExeption(e);
        }
    }
}
