package webapp.storage;

import webapp.exeption.NotExistStorageExeption;
import webapp.exeption.StorageExeption;
import webapp.model.Resume;
import webapp.sql.ConnectionFactory;
import webapp.sql.SqlExecutor;
import webapp.sql.SqlHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlStorage implements Storage {
    private final ConnectionFactory connectionFactory;
    private SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        this.connectionFactory = () -> DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        sqlHelper = new SqlHelper(connectionFactory);
    }

    @Override
    public void clear() {
        sqlHelper.execute("DELETE FROM  resume");
    }

    @Override
    public void update(Resume r) {
        sqlHelper.execute("UPDATE resume SET full_name=? WHERE uuid=?", new SqlExecutor<Object>() {
            @Override
            public Object execute(PreparedStatement ps) throws SQLException {
                ps.setString(1, r.getFullName());
                ps.setString(2, r.getUuid());
                if (ps.executeUpdate()==0){
                    throw new NotExistStorageExeption(r.getUuid());
                }
                return null;
            }
        });
    }

    @Override
    public void save(Resume r) {
        sqlHelper.execute("INSERT INTO resume (uuid,full_name) VALUES(?,?)", ps -> {
            ps.setString(1, r.getUuid());
            ps.setString(2, r.getFullName());
            ps.execute();
            return null;
        });
    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.execute("SELECT * FROM resume r WHERE r.uuid=?", ps -> {
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new NotExistStorageExeption(uuid);
            }
            String st=rs.getString("full_name");
            String st2=rs.getString(1);
            String st3=rs.getString(2);
            Resume r = new Resume(uuid, rs.getString("full_name"));
            return r;
        });
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.execute("DELETE FROM resume r WHERE r.uuid=?", new SqlExecutor<Object>() {
            @Override
            public Object execute(PreparedStatement ps) throws SQLException {
                ps.setString(1, uuid);
                if (ps.executeUpdate()==0){
                    throw new NotExistStorageExeption(uuid);
                }
                return null;
            }
        });

    }

    @Override
    public List<Resume> getAllSorted() {
        return sqlHelper.execute("SELECT * FROM resume ORDER BY full_name,uuid", new SqlExecutor<List<Resume>>() {
            @Override
            public List<Resume> execute(PreparedStatement ps) throws SQLException {
               List<Resume> list = new ArrayList<Resume>();
               ResultSet rs=ps.executeQuery();
               while (rs.next()){
                    list.add(new Resume(rs.getString("uuid"),rs.getString("full_name")));
               }
             return list;
            }
        });
    }

    @Override
    public int size() {
        return sqlHelper.execute("SELECT count(uuid) FROM resume ", new SqlExecutor<Integer>() {
            @Override
            public Integer execute(PreparedStatement ps) throws SQLException {
                ResultSet rs = ps.executeQuery();
                return rs.next() ? rs.getInt(1) : 0;
            }
        });
    }
}
