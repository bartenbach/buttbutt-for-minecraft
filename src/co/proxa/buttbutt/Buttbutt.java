package co.proxa.buttbutt;

import co.proxa.buttbutt.file.FileManager;
import co.proxa.buttbutt.handler.ButtCommandHandler;
import co.proxa.buttbutt.handler.ChatLoggingManager;
import co.proxa.buttbutt.listener.ChatListener;
import co.proxa.buttbutt.listener.ConsoleListener;
import co.proxa.buttbutt.listener.PlayerJoinListener;
import co.proxa.buttbutt.listener.PlayerQuitListener;
import co.proxa.buttbutt.sql.SqlManager;
import co.proxa.buttbutt.thread.ButtDeath;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;
import java.util.logging.Logger;

/*
    + Implemented SQL connection class for storing user quotes.
    + Implemented grab command to grab quotes and store in quote database.
    + Fixed death messages printing when nobody was online to see them.

    Commands that indicate that we have lost connection to the database:


 */

public class Buttbutt extends JavaPlugin {

    private ButtSpeaker bs = new ButtSpeaker(this);
    private Logger buttLogger = this.getLogger();
    private ChatLoggingManager clm = new ChatLoggingManager();
    private SqlManager sqlManager = new SqlManager(this.getConfig(), buttLogger);
    private ButtCommandHandler bch = new ButtCommandHandler(this, bs, clm, sqlManager);
    private ConsoleListener conl = new ConsoleListener(bch);
    private ChatListener cl = new ChatListener(bch, bs, clm);
    private PlayerJoinListener pjl = new PlayerJoinListener(bs);
    private PlayerQuitListener pql = new PlayerQuitListener(bs);
    private ButtDeath bd = new ButtDeath(this, bs);
    private FileManager fileManager = new FileManager(this);

    @Override
    public void onLoad() {
        fileManager.createFiles();
    }

    @Override
    public void onEnable() {
        if (this.isEnabled()) {
            try {
                sqlManager.connectToDatabase();
                buttLogger.info("Database connected");
                sqlManager.createTablesIfNeeded();
            } catch (SQLException ex) {
                buttLogger.severe("Failed to establish SQL connection:");
                ex.printStackTrace();
                buttLogger.severe("**********DISABLING**********");
                this.getPluginLoader().disablePlugin(this);
            }
            if (this.isEnabled()) {
                getServer().getPluginManager().registerEvents(cl, this);
                getServer().getPluginManager().registerEvents(pjl, this);
                getServer().getPluginManager().registerEvents(pql, this);
                getServer().getPluginManager().registerEvents(conl, this);
                bd.start();
            }
        }
    }

    @Override
    public void onDisable() {
        bd = null;
    }
}
