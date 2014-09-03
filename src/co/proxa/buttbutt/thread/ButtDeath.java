package co.proxa.buttbutt.thread;

import co.proxa.buttbutt.Buttbutt;
import org.bukkit.ChatColor;

public class ButtDeath extends Thread {

    private Buttbutt butt;

    public ButtDeath(Buttbutt butt) {
        this.butt = butt;
    }

    @Override
    public void run() {
        while (this.butt != null) {
            try {
                if (this.butt != null) {
                    Thread.sleep(3000000);
                    if (butt.getServer().getOnlinePlayers().length > 0) {
                        this.buttDie();
                        double rand = (Math.random() * 100);
                        if (rand < 5) {
                            Thread.sleep(1000);
                            butt.getButtSpeaker().buttChat(":(", 100L);
                        }
                    }
                }
            } catch (InterruptedException ex) {
                // we don't really care
            }
        }
    }

    //TODO revert this to simply a death thread and create a separate thread for chatting and finding ores
    public void buttDie() {
        int random = (int) (Math.random()*10);
        switch (random) {
            case 0:
                butt.getButtSpeaker().buttBroadcast("buttbutt has drowned");
                break;
            case 1:
                butt.getButtSpeaker().buttBroadcast("buttbutt hit the ground too hard");
                break;
            case 2:
                butt.getButtSpeaker().buttBroadcast("buttbutt fell out of the world");
                break;
            case 3:
                butt.getButtSpeaker().buttBroadcast("buttbutt tried to swim in lava");
                break;
            case 4:
                int rdm = (int) (Math.random()*7)+2;
                butt.getButtSpeaker().buttBroadcast(ChatColor.GRAY + "buttbutt found " + rdm + " coal!");
                break;
            case 5:
                butt.getButtSpeaker().buttBroadcast("buttbutt burned to death");
                break;
            case 6:
                butt.getButtSpeaker().buttBroadcast("buttbutt blew up");
                break;
            case 7:
                butt.getButtSpeaker().buttBroadcast("buttbutt was shot by a skeleton");
                break;
            case 8:
                int cob = (int) (Math.random()*7)+2;
                butt.getButtSpeaker().buttBroadcast(ChatColor.AQUA + "buttbutt found " + cob + " diamonds!");
                break;
            case 9:
                butt.getButtSpeaker().buttBroadcast("buttbutt was shot by an arrow");
                break;
            case 10:
                butt.getButtSpeaker().buttBroadcast("buttbutt was slain by zombie");
                break;
            default:
                butt.getLogger().warning("This should never happen: " + random);
                break;
        }
    }
}
