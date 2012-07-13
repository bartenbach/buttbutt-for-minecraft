/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hopto.seed419;

import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author seed419
 */
public class Buttbutt  extends JavaPlugin {


    private final ChatListener cl = new ChatListener(this);
    private final PlayerJoinListener pjl = new PlayerJoinListener(cl);
    private final PlayerQuitListener pql = new PlayerQuitListener(cl);
    private ButtDeath bd = new ButtDeath(cl);
    private final String version = "0.1";

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(cl, this);
        getServer().getPluginManager().registerEvents(pjl, this);
        getServer().getPluginManager().registerEvents(pql, this);
        bd.start();
    }

    @Override
    public void onDisable() {
        bd = null;
    }

    public String getVersion() {
        return version;
    }

}
