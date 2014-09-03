package co.proxa.buttbutt.sql;

import co.proxa.buttbutt.Buttbutt;
import co.proxa.buttbutt.file.Path;

import java.sql.*;

public class SqlManager {

    private Buttbutt butt;
    private Connection connection;
    private String username;
    private String password;
    private String ip;
    private String port;
    private String tablePrefix;
    private String database;

    public SqlManager(Buttbutt butt) {
        this.username    = butt.getConfig().getString(Path.SQL_USERNAME.filePath);
        this.password    = butt.getConfig().getString(Path.SQL_PASSWORD.filePath);
        this.ip          = butt.getConfig().getString(Path.SQL_IP.filePath);
        this.port        = butt.getConfig().getString(Path.SQL_PORT.filePath);
        this.tablePrefix = butt.getConfig().getString(Path.SQL_TABLE_PREFIX.filePath);
        this.database    = butt.getConfig().getString(Path.SQL_DATABASE.filePath);
        this.butt        = butt;
    }

    public void connectToDatabase() {
        String url = "jdbc:mysql://" + this.ip + ":" + this.port + "/" + this.database;
        try {
            this.connection = DriverManager.getConnection(url, this.username, this.password);
        } catch (SQLException ex) {
            butt.getLogger().severe("Failed to establish SQL connection:");
            butt.getLogger().severe("*************DISABLING*************");
            butt.getPluginLoader().disablePlugin(butt);
        }
    }

    public void createTablesIfNeeded() {
        sqlUpdate("CREATE TABLE IF NOT EXISTS `" + this.tablePrefix + "_quotes` " +
                "(`id` SMALLINT PRIMARY KEY NOT NULL AUTO_INCREMENT, `user` VARCHAR(16) NOT NULL," +
                "`quote` VARCHAR(100) NOT NULL, `grabbed_by` VARCHAR(16) NOT NULL," +
                "`timestamp` DATETIME NOT NULL) ENGINE=MyISAM");
        sqlUpdate("CREATE TABLE IF NOT EXISTS `" + this.tablePrefix + "_knowledge` " +
                "(`id` SMALLINT PRIMARY KEY NOT NULL AUTO_INCREMENT, `item` VARCHAR(32) NOT NULL UNIQUE," +
                "`data` VARCHAR(200) NOT NULL, `added_by` VARCHAR(16) NOT NULL," +
                "`timestamp` DATETIME NOT NULL) ENGINE=MyISAM");
    }

    public boolean sqlUpdate(String sql) {
        if (this.isConnected()) {
            try {
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.executeUpdate();
                return true;
            } catch (SQLException ex) {
                butt.getLogger().warning("Unable to update SQL database.");
            }
        } else {
            reconnect();
            sqlUpdate(sql);
        }
        return false;
    }

    public void reconnect() {
        butt.getLogger().warning("Disconnected from SQL Database.  Reconnecting...");
        if (!this.isConnected()) {
            this.connectToDatabase();
        }
    }

    public String getTablePrefix() {
        return this.tablePrefix;
    }

    public Connection getConnection() {
        return this.connection;
    }

    public PreparedStatement getPreparedStatement(String query) {
        try {
            return this.connection.prepareStatement(query);
        } catch (SQLException e) {
            butt.getLogger().severe("Unable to prepare SQL statement.  Stacktrace following:");
            e.printStackTrace();
            return null;
        }
    }

    public void prepareStatement(PreparedStatement ps, Object ... objects) {
        //int i = 1;
        try {
            for (int i = 0 ; i < objects.length ; i++) {
                System.out.println(objects[i]);
                if (objects[i] instanceof String) {
                    ps.setString((i+1), (String) objects[i]);
                } else {
                    System.out.println("not a string somehow");
                }
                i++;
            }
        } catch (SQLException ex) {
            butt.getLogger().severe("Failed to set parameter in PreparedStatement.  StackTrace:");
            ex.printStackTrace();
        }
    }

    public ResultSet getResultSet(PreparedStatement ps) {
        try {
            return ps.executeQuery();
        } catch (SQLException e) {
            butt.getLogger().severe("Failed to execute query from PreparedStatement.  StackTrace:");
            e.printStackTrace();
            return null;
        }
    }

    public boolean isConnected() {
        try {
            return connection.isValid(10);
        } catch (SQLException ex) {
            reconnect();
            return isConnected();
        }
    }

}
