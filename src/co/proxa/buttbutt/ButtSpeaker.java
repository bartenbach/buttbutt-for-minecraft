package co.proxa.buttbutt;

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

    public void buttChat(final String buttFormat) {
        buttScheduler.scheduleSyncDelayedTask(butt, new Runnable() {
            @Override
            public void run() {
                butt.getServer().broadcastMessage(ChatColor.GOLD + "buttbutt " + ChatColor.GRAY + buttFormat);
            }
        },50L);
    }

    public void buttMe(final String buttFormat) {
        buttScheduler.scheduleSyncDelayedTask(butt, new Runnable() {
            @Override
            public void run() {
                butt.getServer().broadcastMessage(ChatColor.WHITE + "* " +
                        ChatColor.GOLD + "buttbutt " + ChatColor.WHITE + buttFormat);
            }
        },10L);
    }

    public void buttMsg(final CommandSender sender, final String buttFormat) {
        buttScheduler.scheduleSyncDelayedTask(butt, new Runnable() {
            @Override
            public void run() {
                sender.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "buttbutt" + ChatColor.GRAY
                        + " -> me] " + ChatColor.WHITE + buttFormat);
            }
        },10L);
    }

    public void buttBroadcast(final String buttFormat) {
        buttScheduler.scheduleSyncDelayedTask(butt, new Runnable() {
            @Override
            public void run() {
                butt.getServer().broadcastMessage(buttFormat);
            }
        },20L);
    }
}