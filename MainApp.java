package OnTime;

/**
* Aplikacja tworzona od 17.10.2017
* 
* Osoby, które w pełni tworzą znajdujący się kod to:
* Mikołaj Korbanek
* Patryk Pogorzelczyk
* 
* Obecna wersja:
* Ver. 0.0.0.03.9.1
*/

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.SwingUtilities;
import java.awt.*;

public class MainApp extends JFrame {
    static MainApp mp;
    static CenterContent CC = new CenterContent();
    
    
    
    static int RUNNED_TIME = 0;
    
    
    static Toolkit TK = Toolkit.getDefaultToolkit();
    static Dimension SCREEN_SIZE = TK.getScreenSize();
    public float screenHeight = SCREEN_SIZE.height;
    public float screenWidth = SCREEN_SIZE.width;
    
            
    public MainApp() {
        super("OnTime - OYD");
        setLookAndFeel();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(15,3));
        
        JButton cButton = new JButton("Środek");
        cButton.setEnabled(false);
 
        add(CC = new CenterContent(), BorderLayout.CENTER);
        if(RUNNED_TIME == 0){
            CC.setVisible(false);
            
        }
        //CC.setMinimumSize(new Dimension(200,100));
        add(new List(), BorderLayout.EAST);
        
        add(new Calendar(), BorderLayout.WEST);
       
        ////////////////////////////////////////////////////////////////////////
        setLocation((int)(screenWidth / 2.9), (int)(screenHeight / 3.8));
        ////////////////////////////////////////////////////////////////////////
        
        
        MenuBar menu = new MenuBar();       
        setJMenuBar(menu.menubar);
        setResizable(false);
        pack();
        
        setVisible(true);
    }
    
    public static void main(String[] arguments){
        mp = new MainApp();
        mp.setMinimumSize(new Dimension(800, 500));
        new Editor();
    }
    

    public void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(
                "com.sun.java.swing.plaf.windows.WindowsLookAndFeel"
            );
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            System.err.println("Nie potrafię wczytać systemowego wyglądu: " 
                    + e.getMessage());
        }
    }

    
    public static int GET_SCREEN_HEIGHT(){
        return SCREEN_SIZE.height;
    }
    public static int GET_SCREEN_WIDTH(){
        return SCREEN_SIZE.width;
    }
    
    public static void REPAINT(int change){
        mp.dispose();
        if(change==1) RUNNED_TIME = 0;
        else RUNNED_TIME++;
        mp = new MainApp();
        mp.setMinimumSize(new Dimension(800, 500));
        
    }

}