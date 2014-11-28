package co.proxa.buttbutt.handler;

import co.proxa.buttbutt.Buttbutt;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class ButtSpeaker {

    private Buttbutt butt;

    public ButtSpeaker(Buttbutt butt) {
        this.butt = butt;
    }

    public void buttChat(final String buttFormat, final long delay) {
        butt.getServer().getScheduler().scheduleSyncDelayedTask(butt, new Runnable() {
            @Override
            public void run() {
                butt.getServer().broadcastMessage(ChatColor.GOLD + "buttbutt " + ChatColor.GRAY + buttFormat);
            }
        },delay);
    }

    public void buttMe(final String buttFormat, final long delay) {
        butt.getServer().getScheduler().scheduleSyncDelayedTask(butt, new Runnable() {
            @Override
            public void run() {
                butt.getServer().broadcastMessage(ChatColor.WHITE + "* " +
                        ChatColor.GOLD + "buttbutt " + ChatColor.WHITE + buttFormat);
            }
        },delay);
    }

    public void buttMsg(final CommandSender sender, final String buttFormat) {
        butt.getServer().getScheduler().scheduleSyncDelayedTask(butt, new Runnable() {
            @Override
            public void run() {
                sender.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "buttbutt " + ChatColor.GRAY
                        + " -> me] " + ChatColor.WHITE + buttFormat);
            }
        },10L);
    }

    public void buttEmptyMsg(final CommandSender sender, final String buttFormat) {
        butt.getServer().getScheduler().scheduleSyncDelayedTask(butt, new Runnable() {
            @Override
            public void run() {
                sender.sendMessage(buttFormat);
            }
        },10L);
    }

    public void buttBroadcast(final String buttFormat) {
        butt.getServer().getScheduler().scheduleSyncDelayedTask(butt, new Runnable() {
            @Override
            public void run() {
                butt.getServer().broadcastMessage(buttFormat);
            }
        },50L);
    }
}
