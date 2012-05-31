/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hopto.seed419;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author seed419
 */
public class Buttbutt  extends JavaPlugin {


    private final ChatListener cl = new ChatListener(this);


    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(cl, this);
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return super.onCommand(sender, command, label, args);
    }





}
