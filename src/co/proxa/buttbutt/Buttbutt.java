package co.proxa.buttbutt;

import co.proxa.buttbutt.file.FileManager;
import co.proxa.buttbutt.handler.*;
import co.proxa.buttbutt.listener.ChatListener;
import co.proxa.buttbutt.listener.ConsoleListener;
import co.proxa.buttbutt.listener.PlayerJoinListener;
import co.proxa.buttbutt.listener.PlayerQuitListener;
import co.proxa.buttbutt.sql.KnowledgeTable;
import co.proxa.buttbutt.sql.QuoteGrabTable;
import co.proxa.buttbutt.sql.SqlManager;
import co.proxa.buttbutt.thread.ButtDeath;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

/*
    * Cleaned up a lot of lazy, sloppy code
    + Added knowledgeTable framework
    + Added buttSayings framework
 */

public class Buttbutt extends JavaPlugin {

    private ButtSpeaker bs = new ButtSpeaker(this);
    private Logger buttLogger = this.getLogger();
    private ChatLoggingManager clm = new ChatLoggingManager();
    private SqlManager sqlManager = new SqlManager(this);
    private ButtCommandHandler bch = new ButtCommandHandler(this);
    private ConsoleListener conl = new ConsoleListener(this);
    private ChatListener cl = new ChatListener(this);
    private PlayerJoinListener pjl = new PlayerJoinListener(this);
    private PlayerQuitListener pql = new PlayerQuitListener(this);
    private ButtDeath bd = new ButtDeath(this);
    private FileManager fileManager = new FileManager(this);
    private PlayerListHandler plh = new PlayerListHandler(this);
    private ButtNameResponseHandler bnrh = new ButtNameResponseHandler(this);
    private QuoteGrabTable quoteGrabTable = new QuoteGrabTable(this);
    private KnowledgeTable knowledgeTable = new KnowledgeTable(this);
    private KnowledgeHandler knowledgeHandler = new KnowledgeHandler(this);
    private ButtFormatter buttFormatter = new ButtFormatter();
    private InsultHandler insultHandler = new InsultHandler(this);
    private ProtocolManager pm;

    public void onLoad() {
        fileManager.createFiles();
    }

    @Override
    public void onEnable() {
        pm = ProtocolLibrary.getProtocolManager();
        plh.initPacketListener();
        if (this.isEnabled()) {
            sqlManager.connectToDatabase();
            buttLogger.info("Database connected");
            sqlManager.createTablesIfNeeded();
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

    public SqlManager getSqlManager() { return this.sqlManager; }

    public QuoteGrabTable getQuoteGrabTable() { return this.quoteGrabTable; }

    public KnowledgeTable getKnowledgeTable() { return this.knowledgeTable; }

    public KnowledgeHandler getKnowledgeHandler() { return this.knowledgeHandler; }

    public ButtFormatter getButtFormatter() { return this.buttFormatter; }

    public InsultHandler getInsultHandler() { return this.insultHandler; }

    @Override
    public void onDisable() {
        bd = null;
    }
}
