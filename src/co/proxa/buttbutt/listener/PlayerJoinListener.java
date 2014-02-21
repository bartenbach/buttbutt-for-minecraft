package co.proxa.buttbutt.listener;

import co.proxa.buttbutt.ButtSpeaker;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    private ButtSpeaker butt;

    public PlayerJoinListener(ButtSpeaker butt) {
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
                        butt.buttChat("welcome back " + event.getPlayer().getName());
                        break;
                    case 1:
                        butt.buttChat("good to see you again " + event.getPlayer().getName());
                        break;
                    case 2:
                        butt.buttChat("lookin good today, " + event.getPlayer().getName());
                        break;
                    case 3:
                        butt.buttChat("what's up, " + event.getPlayer().getName() + "?");
                        break;
                    case 4:
                        butt.buttChat(event.getPlayer().getName() + ", is it hot in here or is it just you?");
                        break;
                    case 5:
                        butt.buttChat("hey " + event.getPlayer().getName() + ", how are you?");
                        break;
                    default:
                        break;
                }
            }
        } else {
            butt.buttChat("Welcome, " + event.getPlayer().getName() + ".");
            // Rules doesn't print in order and default probably can build atm...
            //butt.buttChat("You will need to be approved before you can build.");
            //butt.buttChat("Type !rules to see the server rules.");
        }
    }
}
