package co.proxa.buttbutt.listener;

import co.proxa.buttbutt.Buttbutt;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    private Buttbutt butt;

    public PlayerQuitListener(Buttbutt butt) {
        this.butt = butt;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        if (butt.getServer().getOnlinePlayers().size() > 0) {
            int random = (int) (Math.random() * 10);
            switch (random) {
                case 0:
                    butt.getButtSpeaker().buttChat("I miss " + event.getPlayer().getName() + " already...", 200L);
            }
        }
    }
}
