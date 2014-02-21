package co.proxa.buttbutt.thread;

import co.proxa.buttbutt.ButtSpeaker;
import org.bukkit.plugin.java.JavaPlugin;

public class ButtDeath extends Thread {

    private JavaPlugin butt;
    private ButtSpeaker buttSpeaker;

    public ButtDeath(JavaPlugin butt, ButtSpeaker buttSpeaker) {
        this.butt = butt;
        this.buttSpeaker = buttSpeaker;
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
                            buttSpeaker.buttChat(":(");
                        }
                    }
                }
            } catch (InterruptedException ex) {
                // we don't really care
            }
        }
    }

    public void buttDie() {
        int random = (int) (Math.random()*10);
        switch (random) {
            case 0:
                buttSpeaker.buttBroadcast("buttbutt has drowned");
                break;
            case 1:
                buttSpeaker.buttBroadcast("buttbutt hit the ground too hard");
                break;
            case 2:
                buttSpeaker.buttBroadcast("buttbutt fell out of the world");
                break;
            case 3:
                buttSpeaker.buttBroadcast("buttbutt tried to swim in lava");
                break;
            case 4:
                buttSpeaker.buttBroadcast("buttbutt went up in flames");
                break;
            case 5:
                buttSpeaker.buttBroadcast("buttbutt burned to death");
                break;
            case 6:
                buttSpeaker.buttBroadcast("buttbutt blew up");
                break;
            case 7:
                buttSpeaker.buttBroadcast("buttbutt was shot by a skeleton");
                break;
            case 8:
                buttSpeaker.buttBroadcast("buttbutt was pricked to death");
                break;
            case 9:
                buttSpeaker.buttBroadcast("buttbutt was shot by an arrow");
                break;
            case 10:
                buttSpeaker.buttBroadcast("buttbutt was slain by zombie");
                break;
            default:
                butt.getLogger().warning("This should never happen: " + random);
                break;
        }
    }
}
