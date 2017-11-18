package OnTime;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Authors extends JFrame {

    JLabel name1,name2,surname1,surname2,email,page;
    JPanel leftPanel, rightPanel;
    
    
    public Authors(){
        setLayout(new GridLayout(2,2));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        name1 = new JLabel("Mikołaj Korbanek",JLabel.CENTER);
        name2 = new JLabel("Patryk Pogorzelczyk",JLabel.CENTER);
        email = new JLabel("OnTimeOYD@gmail.com",JLabel.CENTER);
        //page = new JLabel ("OnTimeOYD.tk",JLabel.CENTER);
        name1.setFont(new Font("Arial",Font.PLAIN,20));
        name2.setFont(new Font("Arial",Font.PLAIN,20));
        email.setFont(new Font("Arial",Font.PLAIN,15));
        //page.setFont(new Font("Arial",Font.PLAIN,15));
        
                
        leftPanel = new JPanel(new GridLayout(1,2));
        rightPanel = new JPanel(new GridLayout(1,2));
        
        leftPanel.add(name1);
        rightPanel.add(name2);
               
        ////////////////////////////////////////////////////////////////////////
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        float screenHeight = screenSize.height;
        float screenWidth = screenSize.width;
        setLocation((int)(screenWidth / 2.3), (int)(screenHeight / 2.4));
        ////////////////////////////////////////////////////////////////////////
        
        add(leftPanel);
        add(rightPanel);
        add(email);
        //add(page);
        
        setSize(400,200);
        setVisible(true);
    }
 
}