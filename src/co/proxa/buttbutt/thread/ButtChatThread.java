package co.proxa.buttbutt.thread;

import co.proxa.buttbutt.Buttbutt;

public class ButtChatThread extends Thread {
    private Buttbutt butt;

    public ButtChatThread(Buttbutt butt) {
        this.butt = butt;
    }

    @Override
    public void run() {
        while (this.butt != null) {
            try {
                if (this.butt != null) {
                    Thread.sleep(3000000);
                    if (butt.getServer().getOnlinePlayers().size() > 0) {
                        this.buttChat();
                    }
                }
            } catch (InterruptedException ex) {
                // we don't really care
            }
        }
    }

    public void buttChat() {
        String randomFact = butt.getKnowledgeTable().getRandomData();
    }
}
