package co.proxa.buttbutt.handler;

import co.proxa.buttbutt.Buttbutt;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitScheduler;

public class ButtSpeaker {

    private Buttbutt butt;
    private BukkitScheduler buttScheduler;

    public ButtSpeaker(Buttbutt butt) {
        this.butt = butt;
        this.buttScheduler = butt.getServer().getScheduler();
    }

    public void buttChat(final String buttFormat, final long delay) {
        buttScheduler.scheduleSyncDelayedTask(butt, new Runnable() {
            @Override
            public void run() {
                butt.getServer().broadcastMessage(ChatColor.GOLD + "buttbutt " + ChatColor.GRAY + buttFormat);
            }
        },delay);
    }

    public void buttMe(final String buttFormat, final long delay) {
        buttScheduler.scheduleSyncDelayedTask(butt, new Runnable() {
            @Override
            public void run() {
                butt.getServer().broadcastMessage(ChatColor.WHITE + "* " +
                        ChatColor.GOLD + "buttbutt " + ChatColor.WHITE + buttFormat);
            }
        },delay);
    }

    public void buttMsg(final CommandSender sender, final String buttFormat) {
        buttScheduler.scheduleSyncDelayedTask(butt, new Runnable() {
            @Override
            public void run() {
                sender.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "buttbutt " + ChatColor.GRAY
                        + " -> me] " + ChatColor.WHITE + buttFormat);
            }
        },10L);
    }

    public void buttEmptyMsg(final CommandSender sender, final String buttFormat) {
        buttScheduler.scheduleSyncDelayedTask(butt, new Runnable() {
            @Override
            public void run() {
                sender.sendMessage(buttFormat);
            }
        },10L);
    }

    public void buttBroadcast(final String buttFormat) {
        buttScheduler.scheduleSyncDelayedTask(butt, new Runnable() {
            @Override
            public void run() {
                butt.getServer().broadcastMessage(buttFormat);
            }
        },50L);
    }
}
