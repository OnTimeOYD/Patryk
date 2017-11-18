package OnTime;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CenterContent extends JPanel implements ActionListener{
    public static int DAY=1, MONTH=1, YEAR=2017, HOUR=12, MINUTE=2;
    public static int LINE_NUMBER=0;
    public String title, content = "Brak zawartości";
    static Integer[] DAYS = new Integer[31];
    static Integer[] MONTHS = new Integer[12];
    static Integer[] YEARS = new Integer[100];
    static Integer[] HOURS = new Integer[24];
    static Integer[] MINUTES = new Integer[60];
    JComboBox dayDate,monthDate,yearDate,hourDate,minuteDate;
    JTextField textTitle;
    JTextArea textContent;
    JButton save,delete;
    JPanel handler;
    
    public CenterContent(){
        handler = new JPanel();
        handler.setLayout(new BoxLayout(handler,BoxLayout.Y_AXIS));
        content = "Brak zawartości";
        if(MainApp.RUNNED_TIME != 0) setDate(LINE_NUMBER);
        JPanel titlePanel = new JPanel(new GridLayout(1,1));
        textTitle = new JTextField(title);
        titlePanel.add(textTitle);
        
        ////////////////////////////////////////////////////////////////////////
        JPanel datePanel = new JPanel();
        JLabel dateLabel = new JLabel("Date: ");
        for(int i=1;i<=YEARS.length;i++){
            if(i<=31){
                DAYS[i-1] = i; 
            }
            if(i<=12){
                MONTHS[i-1] = i;
            }
            YEARS[i-1] = 2016+i;
            if(i<=24){
                HOURS[i-1] = i-1;
            }
            if(i<=60){
                MINUTES[i-1] = i-1;
            }
        }
       
        dayDate = new JComboBox(DAYS);
        dayDate.setSelectedItem(DAY);
        monthDate = new JComboBox(MONTHS);
        monthDate.setSelectedIndex(MONTH-1);
        yearDate = new JComboBox(YEARS);  
        yearDate.setSelectedIndex(YEAR-2017);
        hourDate = new JComboBox(HOURS);
        hourDate.setSelectedIndex(HOUR);
        minuteDate = new JComboBox(MINUTES);
        minuteDate.setSelectedItem(MINUTE);
        datePanel.add(dateLabel);
        datePanel.add(dayDate);
        datePanel.add(monthDate);
        datePanel.add(yearDate);
        
        JPanel timePanel = new JPanel();
        JLabel time = new JLabel("Time: ");
        timePanel.add(time);
        timePanel.add(hourDate);
        timePanel.add(minuteDate);
        ////////////////////////////////////////////////////////////////////////
        
        JPanel contentLabelPanel = new JPanel();
        JLabel contentLabel = new JLabel("Content: ");
        contentLabelPanel.add(contentLabel);
       
        ////////////////////////////////////////////////////////////////////////
        JPanel contentPanel = new JPanel(new GridLayout());

        textContent = new JTextArea(content,15,20);
        JScrollPane scroll = new JScrollPane(textContent);
        textContent.setFont(new Font("Arial",Font.PLAIN,15));
        textContent.setLineWrap(true);
        contentPanel.add(scroll);
        ////////////////////////////////////////////////////////////////////////
        
        JPanel buttonPanel = new JPanel();
        save = new JButton("SAVE",new ImageIcon("save.gif"));
        delete = new JButton("DELETE",new ImageIcon("del.gif"));
        delete.addActionListener(this);
        save.addActionListener(this);
        buttonPanel.add(delete);
        buttonPanel.add(save);
        
        ////////////////////////////////////////////////////////////////////////
        
        handler.add(titlePanel);
        //titlePanel.setBorder(BorderFactory.createLineBorder(Color.gray));
        //(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Title"));
        handler.add(datePanel);
        handler.add(timePanel);
        handler.add(contentLabelPanel);
        handler.add(contentPanel);
        handler.add(buttonPanel);
        handler.setBorder(BorderFactory.createLineBorder(Color.gray));
        add(handler);
    }
    
        public void actionPerformed(ActionEvent event){
        Object source = event.getSource();
        
            if(source == save){

            File.DELETE_OLD(LINE_NUMBER);
            
            File.TABLE_WITH_FILE_DATA[File.TABLE_LENGTH()+1] = 
                      "@@"+yearDate.getSelectedItem()+
                      "@@"+monthDate.getSelectedItem()+
                      "@@"+dayDate.getSelectedItem()+
                      "@@"+hourDate.getSelectedItem()+
                      "@@"+minuteDate.getSelectedItem()+
                      "@@"+textTitle.getText()+
                      "@@"+textContent.getText()+
                      "@@";
            File.SAVE_TAB();
            MainApp.REPAINT(1);
                this.setVisible(false);
            }
            if(source == delete){
                File.DELETE_OLD(LINE_NUMBER);
                File.DELETE();
                File.SAVE_TAB();
                MainApp.REPAINT(1);
            }
        }
        
        public static void SET_LINE(int lineNumber){
            LINE_NUMBER = lineNumber;
        }
        public void setDate(int line){
            DAY = File.GET_DAY(line);
            MONTH = File.GET_MONTH(line);
            YEAR = File.GET_YEAR(line);
            HOUR = File.GET_HOUR(line);
            MINUTE = File.GET_MINUTE(line);
            title = File.GET_TITLE(line);
            content = File.GET_CONTENT(line);
        }
        
        
        
}