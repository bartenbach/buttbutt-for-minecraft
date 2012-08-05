/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hopto.seed419;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 *
 * @author seed419
 */
public class PlayerJoinListener implements Listener {


    private ChatListener butt;


    public PlayerJoinListener(ChatListener butt) {
        this.butt = butt;
    }


    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (event.getPlayer().hasPlayedBefore()) {
            if (event.getPlayer().getName().equalsIgnoreCase("seed419")) {
                int random = (int) (Math.random()*100);
                switch (random) {
                    case 0:
                        butt.buttChat("Welcome back, master.");
                }
            } else {
                int random = (int) (Math.random()*100);
                switch (random) {
                    case 0:
                        butt.buttChat("Welcome back, " + event.getPlayer().getName() + "!");
                        break;
                    case 1:
                        butt.buttChat("Good to see you again, " + event.getPlayer().getName() + "!");
                        break;
                    case 2:
                        butt.buttChat("Looking good today, " + event.getPlayer().getName() + ".");
                        break;
                    case 3:
                        butt.buttChat("What's up, " + event.getPlayer().getName() + " ?");
                        break;
                    case 4:
                        butt.buttChat(event.getPlayer().getName() + ", is it hot in here or is it just you?");
                        break;
                    case 5:
                        butt.buttChat("Hey, " + event.getPlayer().getName() + ", how are you doing today?");
                        break;
                    default:
                        break;
                }
            }
        } else {
            butt.buttChat("Welcome to Peacecraft, " + event.getPlayer().getName() + ".");
            butt.buttChat("You will need to be approved before you can build.");
            butt.buttChat("Type !rules to see the server rules.");
        }
    }

}
