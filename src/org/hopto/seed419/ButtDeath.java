/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hopto.seed419;

/**
 *
 * @author seed419
 */
public class ButtDeath extends Thread {


    private ChatListener butt;


    public ButtDeath(ChatListener butt) {
        this.butt = butt;
    }


    @Override
    public void run() {
        while (butt != null) {
            try {
                if (butt != null) {
                    Thread.sleep(2000000);
                    butt.buttDie();
                    double rand = (Math.random() * 100);
                    if (rand < 5) {
                        Thread.sleep(500);
                        butt.buttChat(":(");
                    }
                }
            } catch (InterruptedException ex) {}
        }
    }




}
