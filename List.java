package OnTime;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;

public class List extends JPanel implements MouseListener,ItemListener{
    String textContent = " \t Jakiś tekst ";
    JPanel[] line = new JPanel[12];
    int TableLength = 0;
    public List(){
        setLayout(new GridLayout(15,1));
        File.READ("calendar.txt"); 
        TableLength =File.TABLE_LENGTH();
        for(int i = 0; i < TableLength && i < 12; i++){ 
          
            add(createDate(File.GET_DAY(i),
                           File.GET_MONTH(i),
                           File.GET_YEAR(i),
                           File.GET_HOUR(i),
                           File.GET_MINUTE(i),
                           File.GET_TITLE(i),
                           i
                            ));
            //System.err.println(File.TABLE_LENGTH());
            //System.out.println(File.TABLE_WITH_FILE_DATA[i]);
        }
        
        
    }
    

    
    public JPanel createDate(int day, int month, int year, int hour, int minute,
                            String text, int value) {
        line[value] = new JPanel();
            String textLabel = "";
            
            if(day < 10){
                textLabel = "0"+day;
            } else {
                textLabel = ""+day;
            }
            if(month<10){
                textLabel += "-0"+month;
            } else{
                textLabel += "-"+month;
            }
            textLabel += "-"+year;
            if(hour <10){
                textLabel += "  0"+hour;
            } else {
                textLabel += "  "+hour;
            }
            if(minute<10){
                textLabel += ":0"+minute;
            } else {
                textLabel += ":"+minute;
            }
            
            if(text.length()<=19){
                for(int i=text.length();i<19;i++){
                    text+=" ";
                }
            }
            if(text.length()>19){
                text = text.substring(0, 15)+" ...";
            }
                
            textLabel += "  "+text;
            
            
            JLabel content = new JLabel(textLabel);
            //TO DO: DOPRACOWAĆ CZCIONKE;
            content.setFont(new Font("Courier New",Font.PLAIN,12));
            line[value].setName(""+value);
            line[value].addMouseListener(this);
            line[value].add(content);  
            line[value].setCursor(new Cursor(Cursor.HAND_CURSOR));
            
        return line[value];
    }
    
    public void mouseClicked(MouseEvent event){
        
        Object source = event.getSource();
        //System.err.println(source);
        int length = File.TABLE_LENGTH();
        for(int i=0;i<length && i < 12;i++){
            if(source == line[i]){
                MainApp.CC.setVisible(true);
                CenterContent.SET_LINE(i);
                MainApp.REPAINT(0);
            }
        }

        }
    public void mouseEntered(MouseEvent event){
    Object source = event.getSource();
    int length = File.TABLE_LENGTH();
        for(int i=0;i<12 && i<length;i++){
                if(source == line[i]){
           line[i].setBackground(new Color(165,172,182));
            } 
        }  
             
    }
    public void mouseExited(MouseEvent event){
        Object source = event.getSource();
       int length = File.TABLE_LENGTH();
       for(int i=0;i<12 && i<length;i++){
           if(source == line[i]){
           line[i].setBackground(null);
            } 
        }
    }
    public void mousePressed(MouseEvent event){
        
    }
    public void mouseReleased(MouseEvent event){
        
    }
    public void itemStateChanged(ItemEvent event){
        Object source = event.getSource();
        //System.err.println(source);
    }
}