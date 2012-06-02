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
    private final String version = "0.1";

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(cl, this);
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    public String getVersion() {
        return version;
    }

}
