package co.proxa.buttbutt.sql;

import co.proxa.buttbutt.file.Path;
import org.bukkit.configuration.file.FileConfiguration;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

public class SqlManager {

    private Connection connection;
    private Logger buttLogger;
    private String username;
    private String password;
    private String ip;
    private String port;
    private String tablePrefix;
    private String database;

    public SqlManager(FileConfiguration config, Logger buttLogger) {
        this.username    = config.getString(Path.SQL_USERNAME.filePath);
        this.password    = config.getString(Path.SQL_PASSWORD.filePath);
        this.ip          = config.getString(Path.SQL_IP.filePath);
        this.port        = config.getString(Path.SQL_PORT.filePath);
        this.tablePrefix = config.getString(Path.SQL_TABLE_PREFIX.filePath);
        this.database    = config.getString(Path.SQL_DATABASE.filePath);
        this.buttLogger  = buttLogger;
    }

    public void connectToDatabase() throws SQLException{
        String url = "jdbc:mysql://" + this.ip + ":" + this.port + "/" + this.database;
        this.connection = DriverManager.getConnection(url, this.username, this.password);
    }

    public void createTablesIfNeeded() throws SQLException{
        sqlUpdate("CREATE TABLE IF NOT EXISTS `" + this.tablePrefix + "_quotes` " +
                "(`id` SMALLINT PRIMARY KEY NOT NULL AUTO_INCREMENT, `user` VARCHAR(16) NOT NULL," +
                "`quote` VARCHAR(100) NOT NULL, `grabbed_by` VARCHAR(16) NOT NULL," +
                "`timestamp` DATETIME NOT NULL) ENGINE=MyISAM");
    }

    public void addQuote(String playerName, String quote, String grabber) throws SQLException {
        if (this.isConnected()) {
            java.util.Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String timestamp = sdf.format(date);
            String update = "INSERT INTO `" + this.tablePrefix + "_quotes` (user,quote,grabbed_by,timestamp) VALUES(?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(update);
            ps.setString(1, playerName);
            ps.setString(2, quote);
            ps.setString(3, grabber);
            ps.setString(4, timestamp);
            ps.executeUpdate();
        }
    }

    public boolean quoteAlreadyExists(String playerName, String quote) throws SQLException{
        if (this.isConnected()) {
            String query = "SELECT * FROM `" + this.tablePrefix + "_quotes` WHERE user=? AND quote=?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, playerName);
            ps.setString(2, quote);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("Already exists!");
                return true;
            } else {
                System.out.println("Does not exist.");
            }
        }
        return false;
    }

    // How is this randomly returning null?
    public String getRandomQuote() throws SQLException {
        if (this.isConnected()) {
            String query = "SELECT * FROM `" + this.tablePrefix + "_quotes` ORDER BY RAND() LIMIT 1";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String user = rs.getString("user");
                String quote = rs.getString("quote");
                int id = rs.getInt("id");
                return restructureQuote(id, user, quote);
            }
        } else {
            reconnect();
            System.out.println("Reconnected, right...?");
            return getRandomQuote();
        }
        return null;
    }

    public String getQuoteById(int id) throws SQLException {
        if (this.isConnected()) {
            String query = "SELECT * FROM `" + this.tablePrefix + "_quotes` WHERE id=?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String user = rs.getString("user");
                String quote = rs.getString("quote");
                return restructureQuote(id, user, quote);
            }
        } else {
            reconnect();
            System.out.println("Reconnected, right...?");
            return getRandomQuote();
        }
        return null;
    }

    public String findQuote(String search) throws SQLException {
        if (this.isConnected()) {
            String query = "SELECT * FROM `" + this.tablePrefix + "_quotes` WHERE quote LIKE ?";
            StringBuilder sb = new StringBuilder(query);
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, "%" + search + "%");
            ResultSet rs = ps.executeQuery();
            //TODO this could certainly return more than one item
            if (rs.next()) {
                int id = rs.getInt("id");
                String user = rs.getString("user");
                String quote = rs.getString("quote");
                return restructureQuote(id, user, quote);
            }
        } else {
            reconnect();
            System.out.println("Reconnected, right...?");
        }
        return null;
    }

    public String getRandomQuoteFromPlayer(String username) throws SQLException {
        if (this.isConnected()) {
            String query = "SELECT * FROM `" + this.tablePrefix + "_quotes` WHERE user=? ORDER BY RAND() LIMIT 1";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String quote = rs.getString("quote");
                return restructureQuote(id, username, quote);
            }
        }
        return null;
    }

    public String[] getQuoteInfo(int id) throws SQLException {
        if (this.isConnected()) {
            String query = "SELECT * FROM `" + this.tablePrefix + "_quotes` WHERE id=?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String user = rs.getString("user");
                String quote = rs.getString("quote");
                String grabber = rs.getString("grabbed_by");
                String time = rs.getString("timestamp");
                String formattedQuote = restructureQuote(id, user, quote);
                String quoteInfo = "Grabbed by: " + grabber + " on " + time;
                String[] quotes = new String[2];
                quotes[0] = formattedQuote;
                quotes[1] = quoteInfo;
                return quotes;
            }
        }
        return null;
    }

    public String getLastQuoteFromPlayer(String username) throws SQLException {
        if (this.isConnected()) {
            String query = "SELECT * FROM `" + this.tablePrefix + "_quotes` WHERE user=? ORDER BY id DESC LIMIT 1";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String quote = rs.getString("quote");
                return restructureQuote(id, username, quote);
            }
        }
        return null;
    }

    private String restructureQuote(int id, String username, String quote) {
        StringBuilder sb = new StringBuilder();
        sb.append("(").append(id).append(") ").append(username).append(": ").append(quote);
        return sb.toString();
    }

    public boolean sqlUpdate(String sql) throws SQLException {
        if (this.isConnected()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.executeUpdate();
            return true;
        } else {
            buttLogger.severe("Database connection timed out after 10 seconds");
            reconnect();
            sqlUpdate(sql);
        }
        return false;
    }

    public void reconnect() {
        try {
            System.out.println("Reconnect method");
            while (!this.isConnected()) {
                this.connectToDatabase();
            }
        } catch (SQLException ex) {
            buttLogger.warning("Failed to connect to database...reconnecting...");
            // Can I do this in the main thread?
            // I don't think this ever executes...
        }
    }

    public boolean isConnected() throws SQLException {
        return connection.isValid(10);
    }

}
