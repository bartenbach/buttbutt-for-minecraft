package co.proxa.buttbutt.handler;

import co.proxa.buttbutt.ButtSpeaker;
import co.proxa.buttbutt.ButtUtils;
import co.proxa.buttbutt.Buttbutt;
import co.proxa.buttbutt.sql.SqlManager;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.sql.SQLException;
import java.util.logging.Logger;

public class ButtCommandHandler {

    private JavaPlugin butt;
    private Logger buttLogger;
    private ButtSpeaker buttSpeaker;
    private ChatLoggingManager clm;
    private SqlManager sqlManager;
    private String commands = "grab, rq, bot, g, q, qinfo, qsay, qfind, yt, notch, rules, cbversion, grief, clear, ";

    public ButtCommandHandler(Buttbutt butt, ButtSpeaker buttSpeaker, ChatLoggingManager clm, SqlManager sqlManager) {
        this.butt = butt;
        this.buttLogger = butt.getLogger();
        this.buttSpeaker = buttSpeaker;
        this.sqlManager = sqlManager;
        this.clm = clm;
    }

    public void handleButtCommand(CommandSender sender, String[] cmd) {
        Player player = null;
        if (sender instanceof Player) {
            player = (Player) sender;
        }
        if (cmd[0].equals("!bot")) {
            buttSpeaker.buttMe("is a robot!");
            buttSpeaker.buttMe("does the robot");
        } else if (cmd[0].equals("!g")) {
            if (cmd.length > 1) {
                String link = "http://www.google.com/search?q=" + ButtUtils.concatenateUrlArgs(cmd);
                buttSpeaker.buttChat(link);
            }
        } else if (cmd[0].equals("!grab")){
            if (cmd.length == 2) {
                if (cmd[1].equalsIgnoreCase(sender.getName()) || cmd[1].equalsIgnoreCase(ChatColor.stripColor(player.getDisplayName()))) {
                    buttSpeaker.buttChat("You like grabbing yourself, " + player.getDisplayName() + "?");
                } else {
                    if (clm.hasQuoteFrom(cmd[1])) {
                        String quote = clm.getLastQuoteFrom(cmd[1]);
                        try {
                            if (!sqlManager.quoteAlreadyExists(cmd[1], quote)) {
                                sqlManager.addQuote(cmd[1], quote, sender.getName());
                                buttSpeaker.buttChat(sender.getName() + ": Tada!");
                            } else {
                                buttLogger.info("Attempted to add duplicate quote - not adding duplicate.");
                            }
                        } catch (SQLException ex) {
                            System.out.println("SQL Exception encountered.");
                            ex.printStackTrace();
                        }
                    } else {
                        buttSpeaker.buttChat(sender.getName() + ": i don't believe I've met " + cmd[1]);
                    }
                }
            } else {
                buttSpeaker.buttMe("!grab <player>");
            }
        } else if (cmd[0].equals("!rq")) {
            if (cmd.length == 1) {
                try {
                    String quote = sqlManager.getRandomQuote();
                    if (quote != null) {
                        buttSpeaker.buttMe(quote);
                    } else {
                        buttSpeaker.buttChat("Error: couldn't retrieve any quotes!");
                    }
                } catch (SQLException ex) {
                    buttSpeaker.buttChat("error - butt find nothing... :(");
                    ex.printStackTrace();
                }
            } else {
                try {
                    String quote = sqlManager.getRandomQuoteFromPlayer(cmd[1]);
                    if (quote != null) {
                        buttSpeaker.buttMe(quote);
                    } else {
                        buttSpeaker.buttChat("butt don't know " + cmd[1]);
                    }
                } catch (SQLException ex) {
                    buttSpeaker.buttChat("error - butt find nothing... :(");
                    ex.printStackTrace();
                }
            }
        } else if (cmd[0].equals("!q")) {
            if (cmd.length == 1) {
                buttSpeaker.buttMe("!q <player>");
            } else {
                try {
                    String quote = sqlManager.getLastQuoteFromPlayer(cmd[1]);
                    if (quote != null) {
                        buttSpeaker.buttMe(quote);
                    } else {
                        buttSpeaker.buttChat("butt don't know " + cmd[1]);
                    }
                } catch (SQLException ex) {
                    buttSpeaker.buttChat("error - butt find nothing... :(");
                    ex.printStackTrace();
                }
            }
        } else if (cmd[0].equals("!qinfo")) {
            if (cmd.length == 1) {
                buttSpeaker.buttMe("!q <id>");
            } else {
                try {
                    String[] quote = sqlManager.getQuoteInfo(Integer.parseInt(cmd[1]));
                    if (quote != null) {
                        buttSpeaker.buttMe(quote[0]);
                        buttSpeaker.buttMe(quote[1]);
                    } else {
                        buttSpeaker.buttChat("no quote record with id of " + ChatColor.DARK_RED + cmd[1] + ChatColor.RESET);
                    }
                } catch (SQLException ex) {
                    buttSpeaker.buttChat("error - butt find nothing... :(");
                    ex.printStackTrace();
                }
            }
        } else if (cmd[0].equals("!qsay")) {
            if (cmd.length == 1) {
                buttSpeaker.buttMe("!qsay <id>");
            } else {
                try {
                    String quote = sqlManager.getQuoteById(Integer.parseInt(cmd[1]));
                    if (quote != null) {
                        buttSpeaker.buttMe(quote);
                    } else {
                        buttSpeaker.buttChat("no quote record with id of " + ChatColor.DARK_RED + cmd[1] + ChatColor.RESET);
                    }
                } catch (SQLException ex) {
                    buttSpeaker.buttChat("error - butt find nothing... :(");
                    ex.printStackTrace();
                }
            }
        } else if (cmd[0].equals("!qfind") || cmd[0].equals("!qsearch")) {
            if (cmd.length == 1) {
                buttSpeaker.buttMe("!qfind <string>");
            } else {
                try {
                    String quote = sqlManager.findQuote(ButtUtils.getArgs(cmd));
                    if (quote != null) {
                        buttSpeaker.buttMe(quote);
                    } else {
                        buttSpeaker.buttChat("butt find nothing... :(");
                    }
                } catch (SQLException ex) {
                    buttSpeaker.buttChat("error - butt find nothing... :(");
                    ex.printStackTrace();
                }
            }
        } else if (cmd[0].equals("!yt")) {
            String link = "http://www.youtube.com/results?search_query=" + ButtUtils.concatenateUrlArgs(cmd);
            buttSpeaker.buttMe(link);
        } else if (cmd[0].equals("!notch")) {
            buttSpeaker.buttMe("Notch is the creator of minecraft.");
            buttSpeaker.buttMe("http://www.minecraftwiki.net/wiki/Notch");
        } else if (cmd[0].equals("!clear") || cmd[0].equals("!cls") || cmd[0].equals("!reset")) {
            for ( int x = 1 ; x < 20 ; x++ ) {
                butt.getServer().broadcastMessage("");
            }
        } else if (cmd[0].equals("!griefing") || cmd[0].equals("grief")) {
            buttSpeaker.buttChat("grief would get buttbutt ban");
        } else if (cmd[0].equals("!cbversion")) {
            buttSpeaker.buttMe("Running Craftbukkit " + butt.getServer().getBukkitVersion());
        } else if (cmd[0].equals("!hal")) {
            buttSpeaker.buttChat("I'm sorry Dave, I'm afraid I can't do that.");
        } else if (cmd[0].equals("!slap")) {
            if (cmd.length == 1) {
                buttSpeaker.buttMe("slaps " + sender.getName() + " with a large trout");
            } else {
                try {
                    Player player2 = butt.getServer().getPlayer(cmd[1]);
                    player2.damage(0D);
                    Vector dir = player2.getLocation().getDirection();
                    Vector newv = new Vector(dir.getX(), dir.getY() + 0.2, dir.getZ());
                    player2.setVelocity(newv);
                    buttSpeaker.buttMe("slaps " + ButtUtils.getArgs(cmd) + "with a large trout");
                } catch (Exception ex) {
                    buttSpeaker.buttMe(ButtUtils.getArgs(cmd) + "is out of my reach");
                }
            }
        } else if (cmd[0].equals("!rage")) {
            if (cmd.length == 1) {
                buttSpeaker.buttChat(sender.getName() + " is about to rage quit");
            } else {
                buttSpeaker.buttChat(ButtUtils.getArgs(cmd) + "is about to rage quit");
            }
        } else if (cmd[0].equals("!home")) {
            if (!(sender instanceof Player)) {
                buttSpeaker.buttMsg(sender, "you don't have a home");
            } else {
                if (cmd.length == 1) {
                    if (player.getBedSpawnLocation() != null) {
                        player.teleport(player.getBedSpawnLocation());
                        buttSpeaker.buttMsg(player, "buttbutt take u home safely");
                    } else {
                        buttSpeaker.buttMsg(player, "butt don't know where u home is at");
                    }
                } else {
                    buttSpeaker.buttChat("Usage: !home");
                }
            }
        } else if (cmd[0].equals("!sexy")) {
            if (cmd.length == 1) {
                buttSpeaker.buttChat(sender.getName() + " is a sexy beast!  Meeeow!");
            } else {
                buttSpeaker.buttChat(ButtUtils.getArgs(cmd) + "is a sexy beast!  Meeeow!");
            }
        } else if (cmd[0].equals("!fail")) {
            buttSpeaker.buttChat(ChatColor.DARK_RED + "" + ChatColor.BOLD + "AHHH THE FAILURE! IT BURRRRNS!!");
        } else if (cmd[0].equals("!halp")) {
            buttSpeaker.buttChat("admin pls");
        } else if (cmd[0].equals("!respond")) {
            buttSpeaker.buttChat("pls respond");
        } else if (cmd[0].equals("!version")) {
            buttSpeaker.buttChat(butt.getDescription().getVersion());
        } else if (cmd[0].equals("!dice") || cmd[0].equals("!roll")) {
            int players = butt.getServer().getOnlinePlayers().length;
            int dice = (int) (Math.random()*players);
            buttSpeaker.buttMe("rolls a huge " + players + " sided die and it flattens "
                    + butt.getServer().getOnlinePlayers()[dice].getName());
            buttSpeaker.buttMe("before coming to a halt on " + ChatColor.DARK_RED + "YOU LOSE");
        } else if (cmd[0].equals("!bloat")) {
            if (cmd.length == 1) {
                buttSpeaker.buttChat("Everything is bloat.");
            } else {
                buttSpeaker.buttChat(ButtUtils.getArgs(cmd) + "is bloat.");
            }
        } else if (cmd[0].equals("!bai")) {
            buttSpeaker.buttChat("baaaai" + ButtUtils.getArgs(cmd));
        }  else if (cmd[0].equals("!oss")) {
            buttSpeaker.buttMe("is an open source project");
            buttSpeaker.buttMe(butt.getDescription().getWebsite());
        } else if (cmd[0].equals("!fd")) {
            int random = (int) (Math.random()*7)+2;
            buttSpeaker.buttBroadcast(ChatColor.AQUA + "buttbutt found " + random + " diamonds!");
        } else if (cmd[0].equals("!hai")) {
            if (cmd.length == 1) {
                buttSpeaker.buttChat("ohai");
            } else {
                buttSpeaker.buttChat("hai there " + ButtUtils.getArgs(cmd));
            }
        } else if (cmd[0].equals("!dance")) {
            buttSpeaker.buttMe("does the robot");
        } else if (cmd[0].equals("!insult")) {
            if (cmd.length >= 2) {
                int random = (int) (Math.random()*6);
                switch (random) {
                    case 0:
                        buttSpeaker.buttChat("Your mother was a poopbutt, " + ButtUtils.getArgs(cmd) + "!");
                        break;
                    case 1:
                        buttSpeaker.buttChat("You're not as bad as people say, " + ButtUtils.getArgs(cmd));
                        buttSpeaker.buttChat("You're worse!");
                        break;
                    case 2:
                        buttSpeaker.buttChat("I'm busy right now, " + ButtUtils.getArgs(cmd));
                        buttSpeaker.buttChat("Can I ignore you some other time?");
                        break;
                    case 3:
                        buttSpeaker.buttChat("Leaving so soon, " + ButtUtils.getArgs(cmd) + "?");
                        buttSpeaker.buttChat("I didn't have a chance to poison your tea...");
                        break;
                    case 4:
                        buttSpeaker.buttChat("You really grow on people, " + ButtUtils.getArgs(cmd) + "...");
                        buttSpeaker.buttChat("Kinda like a wart.");
                        break;
                    case 5:
                        buttSpeaker.buttChat("You're as sharp as a marble, " + ButtUtils.getArgs(cmd));
                        break;
                    default:
                        butt.getLogger().warning("This should never happen either: " + random);
                        break;
                }
            }
        } else if (cmd[0].equals("!random")) {
            int random = (int) (Math.random() * 1000000);
            buttSpeaker.buttChat(random + " is a random number");
        } else if (cmd[0].equals("!drink")) {
            if (cmd.length >= 2) {
                String playerName = ButtUtils.getArgs(cmd);
                try {
                    Player player2 = butt.getServer().getPlayer(playerName);
                    player2.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 150, 5));
                    buttSpeaker.buttChat("Have a drink, " + playerName);
                } catch (Exception ex) {
                    buttSpeaker.buttChat("Who the heck is " + playerName + "?");
                }
            }
        } else if (cmd[0].equals("!poop")) {
            buttSpeaker.buttMe("lets out a big slicker *plop*");
        }
    }

}
