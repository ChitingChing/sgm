package utilidades;

import org.hibernate.jdbc.Work;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionInfo implements Work {
    public String dataBaseUrl;
    public String dataBaseProductName;
    public String driverName;
    public String username;
    private String databaseName;

    @Override
    public void execute(Connection connection) throws SQLException {
        dataBaseUrl = connection.getMetaData().getURL().substring(connection.getMetaData().getURL().indexOf("//")+2);
        dataBaseProductName = connection.getMetaData().getDatabaseProductName();
        driverName = connection.getMetaData().getDriverName();
        username = connection.getMetaData().getUserName();
        databaseName = connection.getCatalog();


    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getDataBaseProductName() {
        return dataBaseProductName;
    }

    public void setDataBaseProductName(String dataBaseProductName) {
        this.dataBaseProductName = dataBaseProductName;
    }

    public String getDataBaseUrl() {
        return dataBaseUrl;
    }

    public void setDataBaseUrl(String dataBaseUrl) {
        this.dataBaseUrl = dataBaseUrl;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }




}
