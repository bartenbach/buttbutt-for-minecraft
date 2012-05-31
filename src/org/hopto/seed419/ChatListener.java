/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hopto.seed419;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

/**
 *
 * @author seed419
 */
public class ChatListener implements Listener {


    private Buttbutt butt;


    public ChatListener(Buttbutt buttbutt) {
        this.butt = buttbutt;
    }


    @EventHandler
    public void onPlayerChat(PlayerChatEvent event) {
        boolean reply = getButtButtChance();

        if(reply || event.getMessage().contains("buttbutt")) {
            String message = event.getMessage();
            final String buttFormat = buttformat(message);
            buttBroadcast(buttFormat);
        }

    }

    private boolean getButtButtChance() {
        int test = (int) (Math.random()*100);
        return (test<=5);
    }

    private String buttformat(String message) {
        String[] split = message.split(" ");
        replaceButt(split);
        if (split.length >= 6) {
            boolean success = replaceButt(split);
            if (!success) {
                buttRetry(split);
            }
        }
        if (split.length >= 14) {
            boolean success = replaceButt(split);
            if (!success) {
                buttRetry(split);
            }
        }
        if (split.length >= 20) {
            boolean success = replaceButt(split);
            if (!success) {
                buttRetry(split);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (String x : split) {
            if (!x.equalsIgnoreCase("buttbutt")) {
                sb.append(x).append(" ");
            }
        }
        return sb.toString();

    }

    private boolean replaceButt(String[] split) {
        int replace = (int) (Math.random()*split.length);
        if(!isAWordWeDontReplace(split[replace])) {
            if (split[replace].endsWith("?")) {
                split[replace] = "butt?";
            } else if (split[replace].endsWith("ed")) {
                split[replace] = "butted";
            } else if (split[replace].endsWith("ing")) {
                split[replace] = "butting";
            } else if (split[replace].endsWith("s")) {
                split[replace] = "butts";
            } else {
                split[replace] = "butt";
            }
            return true;
        }
        return false;
    }

    private void buttBroadcast(final String buttFormat) {
        butt.getServer().getScheduler().scheduleSyncDelayedTask(butt, new Runnable() {
            @Override
            public void run() {
                butt.getServer().broadcastMessage(ChatColor.DARK_GREEN + "[M]||||||||||" + ChatColor.WHITE + "{" +
                ChatColor.GOLD + "buttbutt" + ChatColor.WHITE + "} " + ChatColor.GRAY + buttFormat);
            }
        },20L);
    }

    private boolean isAWordWeDontReplace(String replace) {
        return replace.equalsIgnoreCase("is") || replace.equalsIgnoreCase("a") || replace.equalsIgnoreCase("the")
                || replace.equalsIgnoreCase("i") || replace.equalsIgnoreCase("my") || replace.equalsIgnoreCase("to")
                || replace.equalsIgnoreCase("in") || replace.equalsIgnoreCase("of") || replace.equalsIgnoreCase("buttbutt")
                || replace.equalsIgnoreCase("butt") || replace.equalsIgnoreCase("saw");
    }

    private void buttRetry(String[] split) {
        boolean success = replaceButt(split);
        if (!success) {
            replaceButt(split);
        }
    }

}
