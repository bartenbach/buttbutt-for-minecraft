package co.proxa.buttbutt.handler;

import co.proxa.buttbutt.Buttbutt;
import co.proxa.buttbutt.util.StringUtils;

public class InsultHandler {

    private Buttbutt butt;

    public InsultHandler(Buttbutt butt) {
        this.butt = butt;
    }

    public void insultPlayer(String[] cmd) {
        int random = (int) (Math.random()*6);
        switch (random) {
            case 0:
                butt.getButtSpeaker().buttChat("Your mother was a poopbutt, " + StringUtils.getArgs(cmd) + "!", 30L);
                break;
            case 1:
                butt.getButtSpeaker().buttChat("You're not as bad as people say, " + StringUtils.getArgs(cmd), 30L);
                butt.getButtSpeaker().buttChat("You're worse!", 30L);
                break;
            case 2:
                butt.getButtSpeaker().buttChat("I'm busy right now, " + StringUtils.getArgs(cmd), 30L);
                butt.getButtSpeaker().buttChat("Can I ignore you some other time?", 30L);
                break;
            case 3:
                butt.getButtSpeaker().buttChat("Leaving so soon, " + StringUtils.getArgs(cmd) + "?", 30L);
                butt.getButtSpeaker().buttChat("I didn't have a chance to poison your tea...", 30L);
                break;
            case 4:
                butt.getButtSpeaker().buttChat("You really grow on people, " + StringUtils.getArgs(cmd) + "...", 30L);
                butt.getButtSpeaker().buttChat("Kinda like a wart.", 30L);
                break;
            case 5:
                butt.getButtSpeaker().buttChat("You're as sharp as a marble, " + StringUtils.getArgs(cmd), 30L);
                break;
            default:
                break;
        }
    }
}
