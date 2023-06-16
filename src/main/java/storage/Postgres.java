package storage;

import javax.sql.DataSource;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

import org.apache.commons.dbutils.DbUtils;

public class Postgres implements DataSource {
    private final String JDBC_DRIVER = "org.postgresql.Driver";
    private final String CONN_URL;
    private final String USERNAME;
    private final String PASSWORD;

    @Override
    public Connection getConnection() throws SQLException {
        if (!DbUtils.loadDriver(JDBC_DRIVER)) {
            throw new SQLException("could not find driver");
        }

        return DriverManager.getConnection(CONN_URL, USERNAME, PASSWORD);
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        if (!DbUtils.loadDriver(JDBC_DRIVER)) {
            throw new SQLException("could not find driver");
        }

        return DriverManager.getConnection(CONN_URL, username, password);
    }

    @Override
    public PrintWriter getLogWriter() {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) {

    }

    @Override
    public void setLoginTimeout(int seconds) {

    }

    @Override
    public int getLoginTimeout() {
        return 0;
    }

    @Override
    public Logger getParentLogger() {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) {
        return false;
    }

    public Postgres() {
        try (InputStream input = new FileInputStream("config.properties")) {
            Properties prop = new Properties();
            prop.load(input);

            CONN_URL = String.format(
                    "jdbc:postgresql://%1$s/%2$s",
                    prop.getProperty("db.url"),
                    prop.getProperty("db.name")
            );
            USERNAME = prop.getProperty("db.username");
            PASSWORD = prop.getProperty("db.password");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
