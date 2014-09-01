package co.proxa.buttbutt.listener;

import co.proxa.buttbutt.Buttbutt;
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
        if (event.getCommand().startsWith("!")) {
            butt.getButtCommandHandler().handleButtCommand(event.getSender(), event.getCommand().split(" "));
        }
    }
}
