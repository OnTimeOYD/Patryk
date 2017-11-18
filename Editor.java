package OnTime;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Editor extends JFrame implements ActionListener{
    private JPanel editorPanel;
    private DefaultListModel editListModel;
    private JList editList;
    private JLabel editLabel;
    private JButton chooseButton;
    
    
    public Editor(){
        super("Edit event");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        editorPanel = new JPanel();
        editorPanel.setLayout(new BorderLayout());
        editLabel = new JLabel("Choose event:");
        //editLabel.setAlignmentX(TOP_ALIGNMENT);    trzeba wysrodkowac
        chooseButton = new JButton("Choose");
        chooseButton.addActionListener(this);
        addList();
        JScrollPane scroller = new JScrollPane(editList);
        editList.setVisibleRowCount(7);
        
        
               
        add(editLabel, BorderLayout.NORTH);
        editorPanel.add(scroller);
        add(editorPanel, BorderLayout.CENTER);
        add(chooseButton, BorderLayout.SOUTH);
       
        
        setSize(500,300);
        setVisible(true);
        //setIconImage(MainApp.img);
    }
    
    public void addList(){
        editListModel = new DefaultListModel();
        for(int i = 0; i < File.TABLE_LENGTH(); i++){
            editListModel.addElement(newElement(File.GET_DAY(i),
                           File.GET_MONTH(i),
                           File.GET_YEAR(i),
                           File.GET_HOUR(i),
                           File.GET_MINUTE(i),
                           File.GET_TITLE(i)));
        }
        editList = new JList(editListModel);
        editList.setFont(new Font("Courier New", Font.PLAIN, 14));
    }
    public String newElement(int day, int month, int year, 
                                int hour, int minute, String title) {
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
            textLabel += ", " + title;
        return textLabel;
    }
    
    public void actionPerformed(ActionEvent event){
        Object source = event.getSource();
        
        if(source == chooseButton){
            //System.out.print(editList.getSelectedIndex());
            int index = editList.getSelectedIndex();
            MainApp.CC.setVisible(true);
            CenterContent.SET_LINE(index);
            this.setVisible(false);
            MainApp.REPAINT(0);
        }
    }
}
