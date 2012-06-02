/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hopto.seed419;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
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
        if (event.getMessage().startsWith("!")) {
            String[] cmd = event.getMessage().split(" ");

            if (cmd[0].equalsIgnoreCase("!bot")) {
                buttMe("is a robot!");
                buttMe("does the robot");
            } else if (cmd[0].equalsIgnoreCase("!g")) {
                String link = "http://www.google.com/search?q=" + cmd[1];
                buttChat(link);
            } else if (cmd[0].equalsIgnoreCase("!notch")) {
                buttChat("Notch is the fat man who created minecraft.");
            } else if (cmd[0].equalsIgnoreCase("!cov")) {
                buttChat("h4x0r");
            } else if (cmd[0].equalsIgnoreCase("!griefing")) {
                buttChat("Griefing is only allowed in Z, otherwise: ban");
            } else if (cmd[0].equalsIgnoreCase("!hal")) {
                buttChat("I'm sorry Dave, I'm afraid I can't do that.mem");
            } else if (cmd[0].equalsIgnoreCase("!rage")) {
                if (cmd.length == 1) {
                    buttChat(event.getPlayer().getName() + " is about to rage quit");
                } else {
                    buttChat(cmd[1] + " is about to rage quit");
                }
            } else if (cmd[0].equalsIgnoreCase("!sexy")) {
                buttChat(event.getPlayer().getName() + " is a sexy beast!  Meeeow!");
            } else if (cmd[0].equalsIgnoreCase("!fail")) {
                buttChat("AHHH THE FAILURE! IT BURRRRNNSSS!!");
            } else if (cmd[0].equalsIgnoreCase("!ban")) {
                buttChat("inb4 ban");
            } else if (cmd[0].equalsIgnoreCase("!halp")) {
                buttChat("HALP PLZ KNEAD HALP!");
            } else if (cmd[0].equalsIgnoreCase("!version")) {
                buttChat("I am version " + butt.getVersion() + " and my master is seed419");
            } else if (cmd[0].equalsIgnoreCase("!dice")) {
                int players = butt.getServer().getOnlinePlayers().length;
                System.out.println(players);
                int dice = (int) (Math.random()*players);
                buttMe("rolls a huge " + players + " sided die and it flattens "
                        + butt.getServer().getOnlinePlayers()[dice].getName());
            } else if (cmd[0].equalsIgnoreCase("!shit")) {
                buttChat("I am version " + butt.getVersion() + " and my master is seed419");
            } else if (cmd[0].equalsIgnoreCase("!bai")) {
                buttChat(event.getPlayer().getName() + " is leaving!  BAIIIIIII!");
            } else if (cmd[0].equalsIgnoreCase("!yt")) {
                String link = "http://www.youtube.com/results?search_query=" + cmd[1];
                buttChat(link);
            } else if (cmd[0].equalsIgnoreCase("!optifine")) {
                buttMe("Optifine is a minecraft mod that improves FPS");
                buttMe("http://bit.ly/JwjpDW");
            } else if (cmd[0].equalsIgnoreCase("!oss")) {
                buttMe("is an open source project");
                buttMe("https://github.com/SeeD419/buttbutt");
            } else if (cmd[0].equalsIgnoreCase("!fd")) {
                int random = (int) (Math.random()*4)+2;
                buttBroadcast("[FD] " + ChatColor.AQUA + "buttbutt found "
                        + random + " diamonds!");
            } else if (cmd[0].equalsIgnoreCase("!poop")) {
                buttMe("lets out a big slicker *plop*");
            }  else if (cmd[0].equalsIgnoreCase("!list")) {
                StringBuilder sb = new StringBuilder();
                for (Player x : butt.getServer().getOnlinePlayers()) {
                    sb.append(x.getName()).append(" ");
                }
                buttMe(sb.toString());
            }

        } else {
            boolean reply = getButtButtChance();

            if(reply || event.getMessage().contains("buttbutt")) {
                String message = event.getMessage();
                final String buttFormat = buttformat(message);
                buttChat(buttFormat);
            }
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
            if (!x.equalsIgnoreCase("buttbutt") && !x.equalsIgnoreCase("buttbutt!")
                    && !x.equalsIgnoreCase("buttbutt?") && !x.equalsIgnoreCase("buttbutt,")
                    && !x.equalsIgnoreCase("buttbutt.")) {
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
            } else if (split[replace].endsWith("!")) {
                split[replace] = "butt!";
            } else if (split[replace].endsWith(".")) {
                split[replace] = "butt.";
            }  else {
                split[replace] = "butt";
            }
            return true;
        }
        return false;
    }

    private void buttChat(final String buttFormat) {
        butt.getServer().getScheduler().scheduleSyncDelayedTask(butt, new Runnable() {
            @Override
            public void run() {
                butt.getServer().broadcastMessage(ChatColor.DARK_GREEN + "[M]||||||||||" + ChatColor.WHITE + "{" +
                ChatColor.GOLD + "buttbutt" + ChatColor.WHITE + "} " + ChatColor.GRAY + buttFormat);
            }
        },20L);
    }

    private void buttMe(final String buttFormat) {
        butt.getServer().getScheduler().scheduleSyncDelayedTask(butt, new Runnable() {
            @Override
            public void run() {
                butt.getServer().broadcastMessage(ChatColor.WHITE + "* " +
                ChatColor.GOLD + "buttbutt " + ChatColor.WHITE + buttFormat);
            }
        },20L);
    }

    private void buttBroadcast(final String buttFormat) {
        butt.getServer().getScheduler().scheduleSyncDelayedTask(butt, new Runnable() {
            @Override
            public void run() {
                butt.getServer().broadcastMessage(buttFormat);
            }
        },20L);
    }

    private boolean isAWordWeDontReplace(String replace) {
        return replace.equalsIgnoreCase("is") || replace.equalsIgnoreCase("a") || replace.equalsIgnoreCase("the")
                || replace.equalsIgnoreCase("i") || replace.equalsIgnoreCase("my") || replace.equalsIgnoreCase("to")
                || replace.equalsIgnoreCase("in") || replace.equalsIgnoreCase("of") || replace.equalsIgnoreCase("butt")
                || replace.equalsIgnoreCase("saw");
    }

    private void buttRetry(String[] split) {
        boolean success = replaceButt(split);
        if (!success) {
            replaceButt(split);
        }
    }

}
