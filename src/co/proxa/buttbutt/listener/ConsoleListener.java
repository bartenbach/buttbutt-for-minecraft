package co.proxa.buttbutt.listener;

import co.proxa.buttbutt.Buttbutt;
import co.proxa.buttbutt.util.ButtMath;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerCommandEvent;


public class ConsoleListener implements Listener{

    private Buttbutt butt;

    public ConsoleListener(Buttbutt butt) {
        this.butt = butt;
    }

    @EventHandler (ignoreCancelled = false, priority = EventPriority.NORMAL)
    void onConsoleCommand(ServerCommandEvent event) {
        if (event.getCommand().startsWith("say !")) {
            butt.getButtCommandHandler().handleButtCommand(event.getSender(), event.getCommand().split(" "));
        }
        if (event.getCommand().startsWith("say !")) {
            butt.getButtCommandHandler().handleButtCommand(event.getSender(), event.getCommand().split(" "));
        } else {
            butt.getChatLoggingManager().logMessage(event.getSender().getName(), event.getCommand());
            boolean reply = ButtMath.getButtButtChance();
            String message = event.getCommand();
            if (reply || message.contains("butt")) {
                if (message.equalsIgnoreCase("butt") || message.equalsIgnoreCase("buttbutt")) {
                    butt.getButtNameResponseHandler().buttRespond(event.getSender());
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
