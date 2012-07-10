/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hopto.seed419;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 *
 * @author seed419
 */
public class PlayerQuitListener implements Listener {


    private ChatListener butt;

    PlayerQuitListener(ChatListener cl) {
        this.butt = cl;
    }


    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        int random = (int) (Math.random()*10);
        switch (random) {
        case 0:
            butt.buttChat("I miss " + event.getPlayer().getName() + " already...");
        }
    }

}
