package co.proxa.buttbutt.listener;

import co.proxa.buttbutt.Buttbutt;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    private Buttbutt butt;

    public PlayerJoinListener(Buttbutt butt) {
        this.butt = butt;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        butt.getPlayerListHandler().sendPlayerListPacket(event.getPlayer());
        butt.getPlayerListHandler().sendTabListPacket(event.getPlayer());
        if (event.getPlayer().hasPlayedBefore()) {
            int random = (int) (Math.random()*100);
            if (event.getPlayer().getName().equalsIgnoreCase("seed419")) {
                switch (random) {
                    case 0:
                        butt.getButtSpeaker().buttChat("wb master", 100L);
                }
            } else {
                switch (random) {
                    case 0:
                        butt.getButtSpeaker().buttChat("i remember u " + event.getPlayer().getName(), 100L);
                        break;
                    case 1:
                        butt.getButtSpeaker().buttChat("good to see you again " + event.getPlayer().getName(), 100L);
                        break;
                    case 2:
                        butt.getButtSpeaker().buttChat("hapy 2 see u " + event.getPlayer().getName(), 100L);
                        break;
                    case 3:
                        butt.getButtSpeaker().buttChat("butt glad " + event.getPlayer().getName() + " is here now", 100L);
                        break;
                    case 4:
                        butt.getButtSpeaker().buttChat(event.getPlayer().getName() + " i missed u", 100L);
                        break;
                    case 5:
                        butt.getButtSpeaker().buttChat("hi " + event.getPlayer().getName() + " how r u", 100L);
                        break;
                    default:
                        break;
                }
            }
        } else {
            butt.getButtSpeaker().buttChat("hello " + event.getPlayer().getName(), 100L);
        }
    }
}
