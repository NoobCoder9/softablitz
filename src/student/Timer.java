/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student;

import javafx.scene.control.TextField;

/**
 *
 * @author Kushagra
 */

public class Timer {
    
    
    Thread thread = new Thread();
    private int seconds;
    private TextField tf;
    /**
     *
     * @param time
     * @throws InterruptedException
     */
    public void runTimer(int time, TextField tf)throws InterruptedException{
        
        do{
            thread.sleep(1000);
            tf.setText(Integer.toString(time));
            System.out.println(time);
            
        }while(time-->0);
    }

    /**
     *
     * @param time
     * @throws InterruptedException
     */
    public Timer(int time, TextField tf)throws InterruptedException{
        this.seconds = time;
        this.tf = tf;
        runTimer(this.seconds, this.tf);
    }
}

