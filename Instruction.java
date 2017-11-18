package OnTime;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Instruction extends JFrame implements ActionListener {
    
    JButton exit;
    JTextArea license;
    String instructionContent;
    //licencja odczytywana z pliku
    
    public Instruction(){
        super("Instruction");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        license = new JTextArea(instructionContent);
        license.setLineWrap(true);
        JScrollPane scroll = new JScrollPane(license);
        license.setEditable(false);
        license.setFont(new Font("Arial",Font.PLAIN,18));
        
        exit = new JButton("Exit");
        exit.addActionListener(this);
        
        add(scroll,BorderLayout.CENTER);
        add(exit, BorderLayout.SOUTH);
        
        
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        float screenHeight = screenSize.height;
        float screenWidth = screenSize.width;
        setLocation((int)(screenWidth / 2.9), (int)(screenHeight / 3.8));
        
        setSize(800,700);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent event){
        Object source = event.getSource();
        
        if(source == exit){
            this.setVisible(false);
        }
    }
}   
    //CAŁKOWICIE PRZYPADKOWA LICENCJA