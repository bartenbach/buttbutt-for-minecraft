package co.proxa.buttbutt.handler;

import co.proxa.buttbutt.Buttbutt;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class ButtNameResponseHandler {

    private Buttbutt butt;

    public ButtNameResponseHandler(Buttbutt butt) {
        this.butt = butt;
    }

    public void buttRespond(CommandSender sender) {
        int random = (int) (Math.random()*10);
        switch (random) {
            case 0:
                butt.getButtSpeaker().buttChat("yes butt here", 300L);
                break;
            case 1:
                butt.getButtSpeaker().buttChat("butt not too great at mc", 300L);
                break;
            case 2:
                butt.getButtSpeaker().buttChat("dont member how to craft pick", 400L);
                butt.getButtSpeaker().buttChat("gota pull up the wiki", 800L);
                break;
            case 3:
                butt.getButtSpeaker().buttChat("looking for a new palce to build", 600L);
                break;
            case 4:
                butt.getButtSpeaker().buttChat("hello " + sender.getName().toLowerCase(), 400L);
                butt.getButtSpeaker().buttChat("how r u?", 700L);
                butt.getButtSpeaker().buttChat("jus mineing some coal", 1100L);
                break;
            case 5:
                butt.getButtSpeaker().buttChat("how r u " + sender.getName().toLowerCase(), 600L);
                break;
            case 6:
                butt.getButtSpeaker().buttMe("sry need a new computer", 900L);
                butt.getButtSpeaker().buttMe("laggin", 1300L);
                break;
            case 7:
                butt.getButtSpeaker().buttChat("hey mate", 600L);
                break;
            case 8:
                butt.getButtSpeaker().buttChat("creep outside my house :(", 600L);
                break;
            case 9:
                butt.getButtSpeaker().buttChat("hey", 400L);
                butt.getButtSpeaker().buttChat("hav a good mine here", 800L);
                int rdm = (int) (Math.random()*7)+2;
                butt.getButtSpeaker().buttBroadcast(ChatColor.GRAY + "buttbutt found " + rdm + " coal!");
                butt.getButtSpeaker().buttChat("butt also pretty good miner", 1200L);
                break;
            case 10:
                butt.getButtSpeaker().buttChat("buttbutt was slain by zombie", 400L);
                butt.getButtSpeaker().buttChat("nooo my stuf :(", 800L);
                break;
            default:
                break;
        }
    }
}
