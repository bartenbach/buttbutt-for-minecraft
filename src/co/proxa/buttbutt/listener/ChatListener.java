package co.proxa.buttbutt.listener;

import co.proxa.buttbutt.ButtSpeaker;
import co.proxa.buttbutt.handler.ButtCommandHandler;
import co.proxa.buttbutt.handler.ChatLoggingManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    private ButtSpeaker buttSpeaker;
    private ButtCommandHandler bch;
    private ChatLoggingManager clm;

    public ChatListener(ButtCommandHandler bch, ButtSpeaker buttSpeaker, ChatLoggingManager clm) {
        this.buttSpeaker = buttSpeaker;
        this.bch = bch;
        this.clm = clm;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        if (event.getMessage().startsWith("!")) {
            bch.handleButtCommand(event.getPlayer(), event.getMessage().split(" "));
        } else {
            clm.logMessage(event.getPlayer().getName(), event.getMessage());
            boolean reply = getButtButtChance();
            if (reply || event.getMessage().contains("buttbutt") || event.getMessage().contains("butt")) {
                String message = event.getMessage();
                final String buttFormat = buttformat(message).trim();
                if (!buttFormat.equals(message)) {
                    System.out.println(buttFormat);
                    System.out.println(message);
                    buttSpeaker.buttChat(buttFormat);
                }
            }
        }
    }

    private boolean getButtButtChance() {
        int test = (int) (Math.random()*100);
        return (test<=1);
    }

    private String buttformat(String message) {
        String[] split = message.split(" ");
        replaceButt(split);
        if (split.length > 6) {
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
        // TODO butt emotes
    }

    private boolean replaceButt(String[] split) {
        int replace = (int) (Math.random()*split.length);
        if(!isAWordWeDontReplace(split[replace])) {
            if (split[replace].equalsIgnoreCase("buttbutt")) {
                split[replace] = "";
            } else if (split[replace].endsWith("?")) {
                split[replace] = "butt?";
            } else if (split[replace].endsWith("ed")) {
                split[replace] = "butted";
            } else if (split[replace].endsWith("ing")) {
                split[replace] = "butting";
            } else if (split[replace].endsWith("ify")) {
                split[replace] = "buttify";
            } else if (split[replace].endsWith("ly")) {
                split[replace] = "buttly";
            } else if (split[replace].endsWith("'s")) {
                split[replace] = "butt's";
            } else if (split[replace].endsWith("s")) {
                split[replace] = "butts";
            } else if (split[replace].endsWith("!")) {
                split[replace] = "butt!";
            } else if (split[replace].endsWith(".")) {
                split[replace] = "butt.";
            } else if (split[replace].endsWith("()")) {
                split[replace] = "butt()";
            } else if (split[replace].endsWith(")")) {
                split[replace] = "butt)";
            } else if (split[replace].endsWith(");")) {
                split[replace] = "butt);";
            } else if (split[replace].endsWith(":")) {
                split[replace] = "butt:";
            } else if (split[replace].endsWith(";")) {
                split[replace] = "butt;";
            } else if (split[replace].endsWith("\"")) {
                split[replace] = "butt\"";
            } else if (split[replace].endsWith("'")) {
                split[replace] = "butt'";
            } else if (split[replace].endsWith("*")) {
                split[replace] = "butt*";
            } else if (split[replace].startsWith("#")) {
                split[replace] = "#buttbutt";
            } else if (split[replace].startsWith("/")) {
                split[replace] = "/butt";
            }  else {
                split[replace] = "butt";
            }
            return true;
        }
        return false;
    }


    private boolean isAWordWeDontReplace(String replace) {
        return replace.equalsIgnoreCase("is") || replace.equalsIgnoreCase("a") || replace.equalsIgnoreCase("the")
                || replace.equalsIgnoreCase("i") || replace.equalsIgnoreCase("my") || replace.equalsIgnoreCase("to")
                || replace.equalsIgnoreCase("in") || replace.equalsIgnoreCase("of") || replace.equalsIgnoreCase("butt")
                || replace.equalsIgnoreCase("and") || replace.equalsIgnoreCase("or") || replace.equalsIgnoreCase("your")
                || replace.equalsIgnoreCase("her") || replace.equalsIgnoreCase("his") || replace.equalsIgnoreCase("was")
                || replace.equalsIgnoreCase("lol") || replace.equalsIgnoreCase("wtf") || replace.equalsIgnoreCase("tbh")
                || replace.equalsIgnoreCase("haha") || replace.equalsIgnoreCase("it's") || replace.equalsIgnoreCase("for")
                || replace.equalsIgnoreCase("has");
    }

    private void buttRetry(String[] split) {
        boolean success = replaceButt(split);
        if (!success) {
            replaceButt(split);
        }
    }
}
