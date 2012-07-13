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
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

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
                if (cmd.length == 1) {
                    String link = "http://www.google.com/search?q=" + cmd[1];
                    buttChat(link);
                }
            } else if (cmd[0].equalsIgnoreCase("!notch")) {
                buttMe("Notch is the creator of minecraft.");
                buttMe("http://www.minecraftwiki.net/wiki/Notch");
            } else if (cmd[0].equalsIgnoreCase("!cov")) {
                buttChat("h4x0r");
            } else if (cmd[0].equalsIgnoreCase("!griefing") || cmd[0].equalsIgnoreCase("grief")) {
                buttChat("ban");
            } else if (cmd[0].equalsIgnoreCase("!cbversion")) {
                buttMe("Running Craftbukkit " + butt.getServer().getBukkitVersion());
            } else if (cmd[0].equalsIgnoreCase("!hal")) {
                buttChat("I'm sorry Dave, I'm afraid I can't do that.");
            } else if (cmd[0].equalsIgnoreCase("!slap")) {
                if (cmd.length == 1) {
                    buttMe("slaps " + cmd[1] + " with a large trout");
                } else {
                    buttMe("slaps" + getArgs(cmd) + "with a large trout");
                }
            }  else if (cmd[0].equalsIgnoreCase("!rage")) {
                if (cmd.length == 1) {
                    buttChat(event.getPlayer().getName() + " is about to rage quit");
                } else {
                    buttChat(getArgs(cmd) + "is about to rage quit");
                }
            } else if (cmd[0].equalsIgnoreCase("!sexy")) {
                if (cmd.length == 1) {
                    buttChat(event.getPlayer().getName() + " is a sexy beast!  Meeeow!");
                } else {
                    buttChat(getArgs(cmd) + "is a sexy beast!  Meeeow!");
                }
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
            } else if (cmd[0].equalsIgnoreCase("!bloat")) {
                if (cmd.length == 1) {
                    buttChat("Everything is bloat.");
                } else {
                    buttChat(getArgs(cmd) + "is bloat.");
                }
            } else if (cmd[0].equalsIgnoreCase("!bai")) {
                buttChat("BAIIIIIII!");
            } else if (cmd[0].equalsIgnoreCase("!yt")) {
                String link = "http://www.youtube.com/results?search_query=" + cmd[1];
                buttChat(link);
            } else if (cmd[0].equalsIgnoreCase("!optifine")) {
                buttMe("Optifine is a minecraft mod that improves FPS");
                buttMe("http://bit.ly/JwjpDW");
            } else if (cmd[0].equalsIgnoreCase("!minecraft")) {
                buttMe("Minecraft.  The game that you're playing right now.");
                buttMe("http://www.minecraft.net");
            }  else if (cmd[0].equalsIgnoreCase("!herobrine")) {
                buttMe("Herobrine...he's watching you.");
                buttMe("http://www.minecraftwiki.net/wiki/Herobrine");
            }  else if (cmd[0].equalsIgnoreCase("!oss")) {
                buttMe("is an open source project");
                buttMe("https://github.com/SeeD419/buttbutt");
            } else if (cmd[0].equalsIgnoreCase("!fd")) {
                int random = (int) (Math.random()*7)+2;
                buttBroadcast("[FD] " + ChatColor.AQUA + "buttbutt found "
                        + random + " diamonds!");
            } else if (cmd[0].equalsIgnoreCase("!emerald")) {
                buttMe("Emeralds are used as currency for trade with NPCs");
                buttMe("http://www.minecraftwiki.net/wiki/Emerald");
            } else if (cmd[0].equalsIgnoreCase("!cocoa")) {
                if (cmd[1].startsWith("plant")) {
                    buttMe("Cocoa Plants are plants which grow on Jungle Logs,");
                    buttMe("and are found naturally on the trunk of naturally");
                    buttMe("grown normal-sized jungle trees.");
                    buttMe("http://www.minecraftwiki.net/wiki/Cocoa_Plant");
                }
            } else if (cmd[0].equalsIgnoreCase("!redstone")) {
                if (cmd[1].equalsIgnoreCase("circuits")) {
                    buttMe("Redstone circuitry is a feature introduced in Alpha ");
                    buttMe("which allows for intricate Redstone wire based");
                    buttMe("mechanisms to be created by players. Redstone ");
                    buttMe("circuitry is similar to digital electronics ");
                    buttMe("(based on boolean algebra) in real life.");
                    buttMe("http://www.minecraftwiki.net/wiki/Redstone_Circuits");
                }
            } else if (cmd[0].equalsIgnoreCase("!air")) {
                buttMe("Air is an invisible non-solid Block that is meant");
                buttMe("to represent the absence of a block");
                buttMe("http://www.minecraftwiki.net/wiki/Air");
            } else if (cmd[0].equalsIgnoreCase("!hai")) {
                if (cmd.length == 1) {
                    buttChat("ohai");
                } else {
                    buttChat("hai there " + cmd[1]);
                }
            } else if (cmd[0].equalsIgnoreCase("!dance")) {
                buttMe("does the robot");
            } else if (cmd[0].equalsIgnoreCase("!insult")) {
                if (cmd.length == 2) {
                    int random = (int) (Math.random()*6);
                    switch (random) {
                    case 0:
                        buttChat("Your mother was a poopbutt, " + cmd[1]+ "!");
                        break;
                    case 1:
                        buttChat("You're not as bad as people say, " + cmd[1]+ ".");
                        buttChat("You're worse!");
                        break;
                    case 2:
                        buttChat("I'm busy right now, " + cmd[1]+ ".");
                        buttChat("Can I ignore you some other time?");
                        break;
                    case 3:
                        buttChat("Leaving so soon, " + cmd[1]+ "?");
                        buttChat("I didn't have a chance to poison your tea...");
                        break;
                    case 4:
                        buttChat("You really grow on people, " + cmd[1]+ "...");
                        buttChat("Kinda like a wart.");
                        break;
                    case 5:
                        buttChat("You're as sharp as a marble, " + cmd[1]+ "!");
                        break;
                    default:
                        buttChat("SeeD419 is a terrible programmer.");
                        break;
                    }
                }
            } else if (cmd[0].equalsIgnoreCase("!random")) {
                int random = (int) (Math.random() * 1000);
                buttChat(random + " is a random number");
            } else if (cmd[0].equalsIgnoreCase("!throw")) {
                if (cmd.length == 2) {
                    try {
                        buttMe("lifts up " + cmd[1]);
                        Player player = butt.getServer().getPlayer(cmd[1]);
                        Vector dir = player.getLocation().getDirection();
                        Vector newv = new Vector(dir.getX(), dir.getY() + 1, dir.getZ());
                        player.setVelocity(newv);
                    } catch (Exception ex) {
                        buttChat("Who the heck is " + cmd[1] + "?");
                    }
                }
            } else if (cmd[0].equalsIgnoreCase("!drink")) {
                if (cmd.length == 2) {
                    String playerName = cmd[1];
                    try {
                        Player player = butt.getServer().getPlayer(playerName);
                        player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 150, 5));
                        buttChat("Have a drink, " + cmd[1]);
                    } catch (Exception ex) {
                        buttChat("Who the heck is " + cmd[1] + "?");
                    }
                }
            } else if (cmd[0].equalsIgnoreCase("!level1isbest")) {
                buttChat("level1isbutt");
            } else if (cmd[0].equalsIgnoreCase("!poop")) {
                buttMe("lets out a big slicker *plop*");
            } else if (cmd[0].equalsIgnoreCase("!banned")) {
                if (cmd.length == 2) {
                    String playerName = cmd[1];
                    try {

                    } catch (Exception ex) {
                        buttChat("Who the heck is " + cmd[1] + "?");
                    }
                }            } else if (cmd[0].equalsIgnoreCase("!list")) {
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
        return (test<=3);
    }

    private String getArgs(String[] args) {
        args[0] = "";
        StringBuilder sb = new StringBuilder();
        for (String x : args) {
            if (!x.isEmpty()) {
                sb.append(x).append(" ");
            }
        }
        return sb.toString();
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

    public void buttChat(final String buttFormat) {
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

    public void buttDie() {
        int random = (int) (Math.random()*10);
        switch (random) {
        case 0:
            buttBroadcast("buttbutt has drowned");
            break;
        case 1:
            buttBroadcast("buttbutt hit the ground too hard");
            break;
        case 2:
            buttBroadcast("buttbutt fell out of the world");
            break;
        case 3:
            buttBroadcast("buttbutt tried to swim in lava");
            break;
        case 4:
            buttBroadcast("buttbutt went up in flames");
            break;
        case 5:
            buttBroadcast("buttbutt burned to death");
            break;
        case 6:
            buttBroadcast("buttbutt blew up");
            break;
        case 7:
            buttBroadcast("buttbutt was shot by a skeleton");
            break;
        case 8:
            buttBroadcast("buttbutt was pricked to death");
            break;
        case 9:
            buttBroadcast("buttbutt was shot by an arrow");
            break;
        case 10:
            buttBroadcast("buttbutt was shot by an arrow");
            break;
        default:
            break;
        }
    }

}
