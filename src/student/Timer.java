/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student;

/**
 *
 * @author Divyanshu
 */
public class Timer  implements Runnable{

    private int seconds;
    
    
    @Override
    public void run() {
 
        while(this.seconds>0){
            
            
            
            
            
            
            
        }
        
        
        
        
    }
    
    public void startTimer(int seconds){
        
        this.seconds =seconds;
        Thread t = new Thread(this);
        t.start();
 }
    
    
    
    
    
    
}
