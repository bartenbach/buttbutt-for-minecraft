package co.proxa.buttbutt.handler;

import co.proxa.buttbutt.util.StringUtils;
import co.proxa.buttbutt.Buttbutt;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.sql.SQLException;

public class ButtCommandHandler {

    private Buttbutt butt;
    //TODO not sure how to implement help for the bot still
    //TODO this needs further refactoring.  it's pure madness really.  the laziness is too obvious.z
    private String commands = "grab, rq, bot, g, q, qinfo, qsay, qfind, yt, notch, rules, cbversion, grief, clear";

    public ButtCommandHandler(Buttbutt butt) {
        this.butt = butt;
    }

    public void handleButtCommand(CommandSender sender, String[] cmd) {
        Player player = null;
        if (sender instanceof Player) {
            player = (Player) sender;
        }
        if (cmd[0].equals("!bot")) {
            butt.getButtSpeaker().buttMe("is a robot!", 0L);
            butt.getButtSpeaker().buttMe("does the robot", 0L);
        } else if (cmd[0].equals("!g")) {
            if (cmd.length > 1) {
                String link = "http://www.google.com/search?q=" + StringUtils.concatenateUrlArgs(cmd);
                butt.getButtSpeaker().buttChat(link, 30L);
            }
        } else if (cmd[0].equals("!grab")){
            if (cmd.length == 2) {
                if (cmd[1].equalsIgnoreCase(sender.getName()) || cmd[1].equalsIgnoreCase(ChatColor.stripColor(player.getDisplayName()))) {
                    butt.getButtSpeaker().buttChat("You like grabbing yourself, " + player.getDisplayName() + "?", 30L);
                } else {
                    if (butt.getChatLoggingManager().hasQuoteFrom(cmd[1])) {
                        String quote = butt.getChatLoggingManager().getLastQuoteFrom(cmd[1]);
                        try {
                            if (!butt.getQuoteGrabTable().quoteAlreadyExists(cmd[1], quote)) {
                                butt.getQuoteGrabTable().addQuote(cmd[1], quote, sender.getName());
                                butt.getButtSpeaker().buttChat(sender.getName() + ": Tada!", 30L);
                            } else {
                                butt.getLogger().info("Attempted to add duplicate quote - not adding duplicate.");
                            }
                        } catch (SQLException ex) {
                            System.out.println("SQL Exception encountered.");
                            ex.printStackTrace();
                        }
                    } else {
                        butt.getButtSpeaker().buttChat(sender.getName() + ": i don't believe I've met " + cmd[1], 30L);
                    }
                }
            } else {
                butt.getButtSpeaker().buttMe("!grab <player>", 30L);
            }
        } else if (cmd[0].equals("!rq")) {
            if (cmd.length == 1) {
                String quote = butt.getQuoteGrabTable().getRandomQuote();
                if (quote != null) {
                    butt.getButtSpeaker().buttMe(quote, 30L);
                } else {
                    butt.getButtSpeaker().buttChat("Error: couldn't retrieve any quotes!", 50L);
                }
            } else {
                try {
                    String quote = butt.getQuoteGrabTable().getRandomQuoteFromPlayer(cmd[1]);
                    if (quote != null) {
                        butt.getButtSpeaker().buttMe(quote, 30L);
                    } else {
                        butt.getButtSpeaker().buttChat("butt don't know " + cmd[1], 50L);
                    }
                } catch (SQLException ex) {
                    butt.getButtSpeaker().buttChat("error - butt find nothing... :(", 50L);
                    ex.printStackTrace();
                }
            }
        } else if (cmd[0].equals("!q")) {
            if (cmd.length == 1) {
                butt.getButtSpeaker().buttMe("!q <player>", 30L);
            } else {
                try {
                    String quote = butt.getQuoteGrabTable().getLastQuoteFromPlayer(cmd[1]);
                    if (quote != null) {
                        butt.getButtSpeaker().buttMe(quote, 30L);
                    } else {
                        butt.getButtSpeaker().buttChat("butt don't know " + cmd[1], 30L);
                    }
                } catch (SQLException ex) {
                    butt.getButtSpeaker().buttChat("error - butt find nothing... :(", 50L);
                    ex.printStackTrace();
                }
            }
        } else if (cmd[0].equals("!qinfo")) {
            if (cmd.length == 1) {
                butt.getButtSpeaker().buttMe("!q <id>", 30L);
            } else {
                try {
                    String[] quote = butt.getQuoteGrabTable().getQuoteInfo(Integer.parseInt(cmd[1]));
                    if (quote != null) {
                        butt.getButtSpeaker().buttMe(quote[0], 30L);
                        butt.getButtSpeaker().buttMe(quote[1], 30L);
                    } else {
                        butt.getButtSpeaker().buttChat("no quote record with id of " + ChatColor.DARK_RED + cmd[1] + ChatColor.RESET, 30L);
                    }
                } catch (SQLException ex) {
                    butt.getButtSpeaker().buttChat("error - butt find nothing... :(", 50L);
                    ex.printStackTrace();
                }
            }
        } else if (cmd[0].equals("!qsay")) {
            if (cmd.length == 1) {
                butt.getButtSpeaker().buttMe("!qsay <id>", 30L);
            } else {
                try {
                    String quote = butt.getQuoteGrabTable().getQuoteById(Integer.parseInt(cmd[1]));
                    if (quote != null) {
                        butt.getButtSpeaker().buttMe(quote, 30L);
                    } else {
                        butt.getButtSpeaker().buttChat("no quote record with id of " + ChatColor.DARK_RED + cmd[1] + ChatColor.RESET, 30L);
                    }
                } catch (SQLException ex) {
                    butt.getButtSpeaker().buttChat("error - butt find nothing... :(", 50L);
                    ex.printStackTrace();
                }
            }
        } else if (cmd[0].equals("!qfind") || cmd[0].equals("!qsearch")) {
            if (cmd.length == 1) {
                butt.getButtSpeaker().buttMe("!qfind <string>", 30L);
            } else {
                try {
                    String quote = butt.getQuoteGrabTable().findQuote(StringUtils.getArgs(cmd));
                    if (quote != null) {
                        butt.getButtSpeaker().buttMe(quote, 30L);
                    } else {
                        butt.getButtSpeaker().buttChat("sry butt find noting", 30L);
                    }
                } catch (SQLException ex) {
                    butt.getButtSpeaker().buttChat("error - butt find nothing... :(", 50L);
                    ex.printStackTrace();
                }
            }
        } else if (cmd[0].equals("!yt")) {
            String link = "http://www.youtube.com/results?search_query=" + StringUtils.concatenateUrlArgs(cmd);
            butt.getButtSpeaker().buttMe(link, 30L);
        } else if (cmd[0].equals("!notch")) {
            butt.getButtSpeaker().buttMe("Notch is the creator of minecraft.", 30L);
            butt.getButtSpeaker().buttMe("http://www.minecraftwiki.net/wiki/Notch", 30L);
        } else if (cmd[0].equals("!clear") || cmd[0].equals("!cls") || cmd[0].equals("!reset")) {
            for ( int x = 1 ; x < 21 ; x++ ) {
                butt.getButtSpeaker().buttEmptyMsg(sender, "");
            }
        } else if (cmd[0].equals("!griefing") || cmd[0].equals("grief")) {
            butt.getButtSpeaker().buttChat("grief would get buttbutt ban", 100L);
        } else if (cmd[0].equals("!cbversion")) {
            butt.getButtSpeaker().buttMe("Running Craftbukkit " + butt.getServer().getBukkitVersion(), 10L);
        } else if (cmd[0].equals("!hal")) {
            butt.getButtSpeaker().buttChat("I'm sorry Dave, I'm afraid I can't do that.", 30L);
        } else if (cmd[0].equals("!slap")) {
            if (cmd.length == 1) {
                butt.getButtSpeaker().buttMe("slaps " + sender.getName() + " with a large trout", 30L);
            } else {
                try {
                    Player player2 = butt.getServer().getPlayer(cmd[1]);
                    player2.damage(0D);
                    Vector dir = player2.getLocation().getDirection();
                    Vector newv = new Vector(dir.getX(), dir.getY() + 0.2, dir.getZ());
                    player2.setVelocity(newv);
                    butt.getButtSpeaker().buttMe("slaps " + StringUtils.getArgs(cmd) + "with a large trout", 30L);
                } catch (Exception ex) {
                    butt.getButtSpeaker().buttMe(StringUtils.getArgs(cmd) + "is out of my reach", 30L);
                }
            }
        } else if (cmd[0].equals("!rage")) {
            if (cmd.length == 1) {
                butt.getButtSpeaker().buttChat("we are in the midst of a rage quit", 30L);
            } else {
                butt.getButtSpeaker().buttChat("i sense a rage quit involving " + StringUtils.getArgs(cmd), 30L);
            }
        } else if (cmd[0].equals("!home")) {
            if (!(sender instanceof Player)) {
                butt.getButtSpeaker().buttMsg(sender, "you don't have a home");
            } else {
                if (cmd.length == 1) {
                    if (player.getBedSpawnLocation() != null) {
                        player.teleport(player.getBedSpawnLocation());
                        butt.getButtSpeaker().buttMsg(player, "buttbutt take u home safely");
                    } else {
                        butt.getButtSpeaker().buttMsg(player, "butt don't know where u home is at");
                    }
                } else {
                    butt.getButtSpeaker().buttChat("Usage: !home", 30L);
                }
            }
        } else if (cmd[0].equals("!sexy")) {
            if (cmd.length == 1) {
                butt.getButtSpeaker().buttChat(sender.getName() + "is a sexy beast!  Meeeow!", 30L);
            } else {
                butt.getButtSpeaker().buttChat(StringUtils.getArgs(cmd) + "is a sexy beast!  Meeeow!", 30L);
            }
        } else if (cmd[0].equals("!fail")) {
            butt.getButtSpeaker().buttChat(ChatColor.DARK_RED + "" + ChatColor.BOLD + "AHHH THE FAILURE! IT BURRRRNS!!", 30L);
        } else if (cmd[0].equals("!halp")) {
            butt.getButtSpeaker().buttChat("admin pls", 30L);
        } else if (cmd[0].equals("!respond")) {
            butt.getButtSpeaker().buttChat("pls respond", 30L);
        } else if (cmd[0].equals("!version")) {
            butt.getButtSpeaker().buttChat(butt.getDescription().getVersion(), 10L);
        } else if (cmd[0].equals("!dice") || cmd[0].equals("!roll")) {
            int players = butt.getServer().getOnlinePlayers().length;
            int dice = (int) (Math.random()*players);
            butt.getButtSpeaker().buttMe("rolls a huge " + players + " sided die and it flattens "
                    + butt.getServer().getOnlinePlayers()[dice].getName(), 30L);
            butt.getButtSpeaker().buttMe("before coming to a halt on " + ChatColor.DARK_RED + "YOU LOSE", 30L);
        } else if (cmd[0].equals("!bloat")) {
            if (cmd.length == 1) {
                butt.getButtSpeaker().buttChat("Everything is bloat.", 30L);
            } else {
                butt.getButtSpeaker().buttChat(StringUtils.getArgs(cmd) + "is bloat.", 30L);
            }
        } else if (cmd[0].equals("!bai")) {
            butt.getButtSpeaker().buttChat("bai" + StringUtils.getArgs(cmd), 30L);
        }  else if (cmd[0].equals("!oss")) {
            butt.getButtSpeaker().buttMe("is an open source project", 10L);
            butt.getButtSpeaker().buttMe(butt.getDescription().getWebsite(), 10L);
        } else if (cmd[0].equals("!fd")) {
            int random = (int) (Math.random()*7)+2;
            butt.getButtSpeaker().buttBroadcast(ChatColor.AQUA + "buttbutt found " + random + " diamonds!");
        } else if (cmd[0].equals("!hai")) {
            if (cmd.length == 1) {
                butt.getButtSpeaker().buttChat("ohai", 30L);
            } else {
                butt.getButtSpeaker().buttChat("hai there " + StringUtils.getArgs(cmd), 30L);
            }
        } else if (cmd[0].equals("!dance")) {
            butt.getButtSpeaker().buttMe("does the robot", 30L);
        } else if (cmd[0].equals("!insult")) {
            if (cmd.length >= 2) {
                butt.getInsultHandler().insultPlayer(cmd);
            }
        } else if (cmd[0].equals("!random")) {
            int random = (int) (Math.random() * 1000000);
            butt.getButtSpeaker().buttChat(random + " is a random number", 30L);
        } else if (cmd[0].equals("!learn")) {
            butt.getKnowledgeHandler().addKnowledge(sender.getName(), cmd);
            butt.getButtSpeaker().buttChat("ok " + sender.getName() + " got it", 30L);
        } else if (cmd[0].equals("!unlearn")) {
            boolean success = butt.getKnowledgeHandler().removeKnowledge(cmd);
            if (success) {
                butt.getButtSpeaker().buttChat("ok butt wont member that no more", 30L);
            } else {
                butt.getButtSpeaker().buttChat("butt dont kno nothin bout that", 30L);
            }
        } else if (cmd[0].startsWith("~")) {
            cmd[0] = cmd[0].substring(1, cmd[0].length());
            String info = butt.getKnowledgeHandler().getKnowledge(cmd);
            if (info != null) {
                butt.getButtSpeaker().buttChat(info, 30L);
            } else {
                butt.getButtSpeaker().buttChat("butt dun kno nothin bout that", 30L);
            }
        } else if (cmd[0].equals("!drink")) {
            if (cmd.length >= 2) {
                String playerName = StringUtils.getArgs(cmd);
                try {
                    Player player2 = butt.getServer().getPlayer(playerName);
                    player2.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 150, 5));
                    butt.getButtSpeaker().buttChat("Have a drink, " + playerName, 30L);
                } catch (Exception ex) {
                    butt.getButtSpeaker().buttChat("who is " + playerName + "?", 30L);
                }
            }
        } else if (cmd[0].equals("!poop")) {
            butt.getButtSpeaker().buttMe("lets out a big slicker *plop*", 30L);
        }
    }

}
