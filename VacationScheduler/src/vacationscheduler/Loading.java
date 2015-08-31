/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vacationscheduler;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author erikbenscoter
 */
public class Loading extends Canvas{
            
            int x = 0;
            int y=this.getHeight()/2;
            public void moveCircle(){
                x+=1;
                if(x>this.getWidth()){
                   
                }
                //System.out.println(x);
                try{
                TimeUnit.NANOSECONDS.sleep((long) 25000000);
                }catch(Exception e){
                    System.out.println("VacationScheduler.java e");
                }
                try{
                new Thread(new Runnable(){

                    @Override
                    public void run() {
                      if(x<getWidth())
                        repaint();
                    }
                    
                }).start();
               }catch(Exception e){
                   System.out.println("VacationScheduler" + e);
               }
            }
            
            @Override
            public void paint(Graphics g) {
                super.paint(g); //To change body of generated methods, choose Tools | Templates.
                g.setColor(Color.WHITE);
                g.fill3DRect(0, y, 25+x, 25,true);
                moveCircle();
            }
            
}
