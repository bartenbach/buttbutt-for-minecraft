package co.proxa.buttbutt.listener;

import co.proxa.buttbutt.handler.ButtCommandHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerCommandEvent;


public class ConsoleListener implements Listener{

    private ButtCommandHandler bch;

    public ConsoleListener(ButtCommandHandler bch) {
        this.bch = bch;
    }

    @EventHandler (ignoreCancelled = false, priority = EventPriority.NORMAL)
    void onConsoleCommand(ServerCommandEvent event) {
        if (event.getCommand().startsWith("!")) {
            bch.handleButtCommand(event.getSender(), event.getCommand().split(" "));
        }
    }
}
