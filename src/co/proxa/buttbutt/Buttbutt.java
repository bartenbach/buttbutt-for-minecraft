package co.proxa.buttbutt;

import co.proxa.buttbutt.file.FileManager;
import co.proxa.buttbutt.handler.ButtCommandHandler;
import co.proxa.buttbutt.handler.ButtNameResponseHandler;
import co.proxa.buttbutt.handler.ChatLoggingManager;
import co.proxa.buttbutt.handler.PlayerListHandler;
import co.proxa.buttbutt.listener.ChatListener;
import co.proxa.buttbutt.listener.ConsoleListener;
import co.proxa.buttbutt.listener.PlayerJoinListener;
import co.proxa.buttbutt.listener.PlayerQuitListener;
import co.proxa.buttbutt.sql.SqlManager;
import co.proxa.buttbutt.thread.ButtDeath;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;
import java.util.logging.Logger;

/*
    * Fixed buttbutt replying with an empty message (again?)
    + Added buttbutt to the tab list!


 */

public class Buttbutt extends JavaPlugin {

    private ButtSpeaker bs = new ButtSpeaker(this);
    private Logger buttLogger = this.getLogger();
    private ChatLoggingManager clm = new ChatLoggingManager();
    private SqlManager sqlManager = new SqlManager(this.getConfig(), buttLogger);
    private ButtCommandHandler bch = new ButtCommandHandler(this, bs, clm, sqlManager);
    private ConsoleListener conl = new ConsoleListener(this);
    private ChatListener cl = new ChatListener(this);
    private PlayerJoinListener pjl = new PlayerJoinListener(this);
    private PlayerQuitListener pql = new PlayerQuitListener(bs);
    private ButtDeath bd = new ButtDeath(this, bs);
    private FileManager fileManager = new FileManager(this);
    private PlayerListHandler plh = new PlayerListHandler(this);
    private ButtNameResponseHandler bnrh = new ButtNameResponseHandler(this);
    private ProtocolManager pm;

    public void onLoad() {
        fileManager.createFiles();
    }

    @Override
    public void onEnable() {
        pm = ProtocolLibrary.getProtocolManager();
        plh.initPacketListener();
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

    public PlayerListHandler getPlayerListHandler() {
        return this.plh;
    }

    public ButtSpeaker getButtSpeaker() {
        return this.bs;
    }

    public ButtCommandHandler getButtCommandHandler() {
        return this.bch;
    }

    public ChatLoggingManager getChatLoggingManager() {
        return this.clm;
    }

    public ButtNameResponseHandler getButtNameResponseHandler() {
        return this.bnrh;
    }

    public ProtocolManager getProtocolManager() {
        return this.pm;
    }

    @Override
    public void onDisable() {
        bd = null;
    }
}
