package co.proxa.buttbutt.listener;

import co.proxa.buttbutt.ButtSpeaker;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    private ButtSpeaker butt;

    public PlayerQuitListener(ButtSpeaker cl) {
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
