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
        try {
            Thread.sleep(1800000);
            butt.buttDie();
        } catch (InterruptedException ex) {}
    }




}
