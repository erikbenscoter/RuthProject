/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vacationscheduler;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.concurrent.TimeUnit;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author erikbenscoter
 */
public class StartUp {
    JPanel jp;
    JButton fin;JFrame splashScreen = new JFrame();
    public StartUp(){
        JButton fin = new JButton();
            fin.setText("Finished");
        JPanel jp = new JPanel();
         StartUp cpy = this;
         // TODO code application logic here
        
        splashScreen.setSize(300, 500);
        splashScreen.setBackground(Color.BLUE);
        
        JLabel welcomeMsg = new JLabel();
        welcomeMsg.setText("Welcome to Vacation Scheduler");
        Font font = new Font("Elephant", Font.BOLD, 14);
        welcomeMsg.setFont(font);
        welcomeMsg.setForeground(Color.WHITE);
        
        
        Canvas spacer = new Canvas();
        spacer.setSize(splashScreen.getWidth(),100);

        JButton Done = new JButton();
        
        
        
       
        Loading loading = new Loading();
        loading.setSize(splashScreen.getWidth(), 100);
        
        
        
        jp = new JPanel();
        jp.setBackground(Color.BLUE);
        jp.setSize(300,500);
        jp.add(spacer);
        jp.add(welcomeMsg);
        jp.add(spacer);
        jp.add(loading);
        
       
        
        
        
        splashScreen.add(jp);
        splashScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        splashScreen.setVisible(true);
        //Home home = new Home();
        
       
        
        
       class WaitToAddButton implements Runnable{
           StartUp a;
           public WaitToAddButton(StartUp s){
               a = s;
            }
            @Override
            public void run() {
//                System.out.println("here");
//                while(loading.x < loading.getWidth()) 
//                    try{
//                        Thread.sleep(20);
//                    }catch(Exception e){
//                        System.out.println("couldn't sleep");
//                    }
//               
//                addButton(loading);
            }   
       }
        new Thread(new WaitToAddButton(this)).start();
    }
    public void addButton(Loading loading){
        new Home().setVisible(true);
        splashScreen.dispose();
    }
}

