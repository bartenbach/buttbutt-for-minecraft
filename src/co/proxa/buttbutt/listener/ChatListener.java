package co.proxa.buttbutt.listener;

import co.proxa.buttbutt.Buttbutt;
import co.proxa.buttbutt.util.ButtMath;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    private Buttbutt butt;

    public ChatListener(Buttbutt butt) {
        this.butt = butt;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        if (event.getMessage().startsWith("!") || event.getMessage().startsWith("~")) {
            butt.getButtCommandHandler().handleButtCommand(event.getPlayer(), event.getMessage().split(" "));
        } else {
            butt.getChatLoggingManager().logMessage(event.getPlayer().getName(), event.getMessage());
            boolean reply = ButtMath.getButtButtChance();
            String message = event.getMessage();
            if (reply || message.contains("butt")) {
                if (message.equalsIgnoreCase("butt") || message.equalsIgnoreCase("buttbutt")) {
                    butt.getButtNameResponseHandler().buttRespond(event.getPlayer());
                } else {
                    final String buttFormat = butt.getButtFormatter().buttformat(message).trim();
                    if (!buttFormat.equals(message)) {
                        butt.getButtSpeaker().buttChat(buttFormat, 60L);
                    }
                }
            }
        }
    }
}
