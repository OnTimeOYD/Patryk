package OnTime;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;

public class Calendar extends JPanel implements ActionListener,MouseListener{
    JLabel monthName,yearNumb;
    JLabel[] dayName = new JLabel[7];
    JLabel[] dayNumb = new JLabel[42];
    Date[] dates = new Date[100];
    JPanel holder = new JPanel();
    JPanel yearNumbPanel,monthNamePanel,days;
    JButton leftYear,rightYear,leftMonth,rightMonth;    
    String[] EngNames = {"M","T","W","T","F","S","S"};
    String[] EngMonthName = {" January "," February","  March  ","  April  ","   May   ",
                            "   June  ","   July  ","  August ","September",
                            " October "," November"," December",""};
    int numbOfDays = 31,lastIndex = 0,block = 0,monthNumb = 0,FirstIndex;
    static int YEAR=2017;
    static Date TODAY = new Date();
  
    public Calendar(){      
        holder.setLayout(new BoxLayout(holder, BoxLayout.Y_AXIS));
        yearNumbPanel = new JPanel(); 
        yearNumb = new JLabel(""+YEAR); 
        
        leftYear = new JButton("<=");
        rightYear = new JButton("=>");
        yearNumbPanel.add(leftYear);
        yearNumbPanel.add(yearNumb);
        yearNumbPanel.add(rightYear);
        if(YEAR==2017){
            leftYear.setEnabled(false);
        }
        leftYear.addActionListener(this);
        rightYear.addActionListener(this);
         
        yearNumb.setFont(new Font("Courier New",Font.PLAIN,15));
        yearNumb.setAlignmentX(CENTER_ALIGNMENT);
        
        
        monthNamePanel = new JPanel();
        monthName = new JLabel(EngMonthName[monthNumb]);
        leftMonth = new JButton("<=");
        rightMonth = new JButton("=>");
        if(YEAR == 2017){
            leftYear.setEnabled(false);
        }
        monthName.setFont(new Font("Courier New",Font.BOLD,15));
        leftMonth.addActionListener(this);
        rightMonth.addActionListener(this);
        monthNamePanel.add(leftMonth);
        monthNamePanel.add(monthName);
        monthNamePanel.add(rightMonth);

        days = new JPanel();
        days.setLayout(new GridLayout(7,7,5,2));
        for(int i=0;i<7;i++){
            JLabel dayName = new JLabel(EngNames[i]);
            dayName.setHorizontalAlignment(SwingConstants.CENTER);
            dayName.setVerticalAlignment(SwingConstants.CENTER);
            dayName.setFont(new Font("Courier New",Font.BOLD,18));
            days.add(dayName);
        }
        
        
        for(int i=0;i<dayNumb.length;i++){
            dayNumb[i]=new JLabel();
            dayNumb[i].setHorizontalAlignment(SwingConstants.CENTER);
            dayNumb[i].setVerticalAlignment(SwingConstants.CENTER);
        }
        getDates();
        writeCalendar(monthNumb+1,YEAR,YEAR);
        
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        holder.add(yearNumbPanel);
        holder.add(monthNamePanel);
        //holder.add(dayTitle);
        holder.add(days); 
        add(holder);        
        setVisible(true);
    }
    
    public int getFirstIndexOfMonth(int Month){
        int indexToReturn = 0 ;
        
        return indexToReturn;
    }
////////////////////////////////////////////////////////////////////////////////    
    public void writeCalendar(int Month, int Year,int Prev){
        int dayNum = 1;
        numbOfDays = GET_NUMB_OF_DAYS(Month,Year); 
        FirstIndex = GET_FIRST_INDEX(Month,Year,lastIndex);
        lastIndex = GET_LAST_INDEX(FirstIndex,numbOfDays);
        
        
       // System.out.println(lastIndex);
        if(Prev<=Year){
            for(int i=0; i<dayNumb.length;i++){
               dayNumb[i].setText("  ");
               dayNumb[i].addMouseListener(this);
                days.add(dayNumb[i]);  
                dayNumb[i].setOpaque(true);
                dayNumb[i].setBackground(null); 
                dayNumb[i].setForeground(null);  
                dayNumb[i].setName("No.");
            } 

            for(int i=0;i<numbOfDays+FirstIndex;i++){
                if(i<FirstIndex){
                    continue;
                } else if(i<9+FirstIndex){
                    dayNumb[i].setText("0"+dayNum++);
                    dayNumb[i].addMouseListener(this);
                    
                    if(makeDatesColorfull(dayNum-1,Month,Year)){
                        dayNumb[i].setOpaque(true);
                        dayNumb[i].setBackground(new Color(184,0,0));
                        dayNumb[i].setForeground(new Color(0xFF,0xFF,0xFF));
                        dayNumb[i].setName("Colored");
                        
                    }else{
                        dayNumb[i].setOpaque(true);
                        dayNumb[i].setBackground(null); 
                        dayNumb[i].setForeground(null);
                        dayNumb[i].setName("No.");
                    }
                    
                    if(dayNum-1 == TODAY.getDate() && Month == TODAY.getMonth()-1 && Year==TODAY.getYear()+1900){
                        dayNumb[i].setOpaque(true);
                        dayNumb[i].setBackground(new Color(165,172,182)); 
                        dayNumb[i].setName("Today");
                        
                        
                    }
                    
                } else {
                    
                    dayNumb[i].setText(""+dayNum++);
                    dayNumb[i].addMouseListener(this);
                    
                    if(makeDatesColorfull(dayNum-1,Month,Year)){
                        
                        dayNumb[i].setOpaque(true);
                        dayNumb[i].setBackground(new Color(184,0,0));
                        dayNumb[i].setForeground(new Color(0xFF,0xFF,0xFF));
                        dayNumb[i].setName("Colored");
                    }else{
                        dayNumb[i].setOpaque(true);
                        dayNumb[i].setBackground(null); 
                        dayNumb[i].setForeground(null);
                        dayNumb[i].setName("No.");
                    } 
                    
                    if(dayNum-1 == TODAY.getDate() && Month == TODAY.getMonth()+1 && Year==TODAY.getYear()+1900){
                        dayNumb[i].setOpaque(true);
                        dayNumb[i].setBackground(new Color(165,172,182)); 
                        dayNumb[i].setName("Today");
                    }
                    
                    
                }  
                
            }
        }else{
        
            
        }    
    }
    
    public static int GET_NUMB_OF_DAYS(int Month, int Year){
        int DayNumb = 0;
        Boolean isLeap;
        
        
        switch(Month){
            case 2: isLeap = LEAP_YEAR(Year);
                    if(isLeap)DayNumb = 29;  
                    else DayNumb = 28;
                    break;
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12: DayNumb = 31;
                     break;
            case 4:
            case 6:
            case 9:
            case 11: DayNumb = 30;
                     break; 
        }
        
        return DayNumb;
    }
    
    public static int GET_FIRST_INDEX(int Month, int Year,int Last){
        int FirstIndex = 6;
        if(Month == 1 && Year == 2017) FirstIndex = 6;
        else if(Month == 1 && Year != 2017){
            if(LEAP_YEAR(Year-1)){
                FirstIndex = (Last+335)%7;
                return FirstIndex;
            }
            else{
                FirstIndex = (Last+334)%7;
                return FirstIndex;
            }
        }else{
            FirstIndex = Last;
            return FirstIndex;
        }
        
        return FirstIndex;
        
    }
    public static int GET_LAST_INDEX(int FirstIndex, int numbOfDays){
        int LastIndex = 0;
        LastIndex = (FirstIndex + numbOfDays)%7;
        return LastIndex;
    }
    
    
    
    
    private static boolean LEAP_YEAR(int Year){
        return ((Year % 4 == 0) && (Year % 100 != 0)) || (Year % 400 == 0);
    }
    
    public void getDates(){
        for(int i=0;i<File.TABLE_WITH_FILE_DATA.length && i < File.TABLE_LENGTH();i++){
            dates[i] = new Date();
            dates[i].setDate(File.GET_DAY(i));
            dates[i].setMonth(File.GET_MONTH(i)-1);
            dates[i].setYear(File.GET_YEAR(i)-1900);
            dates[i].setHours(File.GET_HOUR(i));
            dates[i].setMinutes(File.GET_MINUTE(i));
            dates[i].setSeconds(0);           
            //System.out.println(dates[i]);
        }
    }
    public boolean makeDatesColorfull(int Day,int Month, int Year){
            int length = File.TABLE_LENGTH();
            Boolean status = false;
            for (int i=0;i<length;i++){

               int datesY = dates[i].getYear()+1900; 
               int datesM = dates[i].getMonth()+1;
               int datesD = dates[i].getDate();
               


               
               if(Year == datesY && Month == datesM && Day == datesD){
                   // System.out.println(datesY + " " + datesM + " "+datesD);
                           status = true;
               }               
            }

            return status;
    }
    
    
    
    public Insets getInsets(){
        return new Insets(30,30,30,30);
    }
    
    public void mouseClicked(MouseEvent event){
        Object source = event.getSource();
        for(int i=0;i<dayNumb.length;i++){
            if(source == dayNumb[i] && dayNumb[i].getName().equals("Colored")){
                for(int j = 0; j < dayNumb.length;j++){  
                        if(block == 0 && File.GET_DAY(j)-1 == i-FirstIndex && File.GET_MONTH(j)-1== monthNumb&&
                                File.GET_YEAR(j) == YEAR){
                            block++;
                            MainApp.CC.setVisible(true);
                            CenterContent.SET_LINE(j);
                            MainApp.REPAINT(0);
                        }   
                }
            }
        }
        
    }
    public void mouseEntered(MouseEvent event){
        Object source = event.getSource();
        for (int i=0;i<dayNumb.length;i++){
            if(source == dayNumb[i] && !(dayNumb[i].getText().equals("  "))){
                dayNumb[i].setOpaque(true);
                dayNumb[i].setBackground(new Color(255,128,000));
                dayNumb[i].setForeground(new Color(0xFF,0xFF,0xFF));
                dayNumb[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        }
    }
    public void mouseExited(MouseEvent event){
        Object source = event.getSource();
            for(int i=0;i<dayNumb.length;i++){
                if(source == dayNumb[i]){    
                    if(dayNumb[i].getName().equals("Colored")){
                        dayNumb[i].setOpaque(true);
                        dayNumb[i].setBackground(new Color(184,0,0));
                        dayNumb[i].setForeground(new Color(0xFF,0xFF,0xFF));
                    }else if(dayNumb[i].getName().equals("Today")){
                    dayNumb[i].setOpaque(true);
                    dayNumb[i].setBackground(new Color(165,172,182));  
                    dayNumb[i].setForeground(null);
                    }else{
                    dayNumb[i].setBackground(null);
                    dayNumb[i].setForeground(null);
                    }

                }
                
            }
    }
    public void mousePressed(MouseEvent event){ 
        Object source = event.getSource();
        for(int i = 0; i < dayNumb.length;i++){
            if(source == dayNumb[i]){
                if(i>=FirstIndex && block == 0  && !(dayNumb[i].getName().equals("Colored"))){
                        NewFile.day = i - FirstIndex;
                        NewFile.month = monthNumb;
                        NewFile.year = YEAR-2017;
                        new NewFile();
                        block++;     
                }
            }
        }
    }
    public void mouseReleased(MouseEvent event){       
        block = 0;
    }   
    public void actionPerformed(ActionEvent event){
        
        Object source = event.getSource();
        if(source == leftYear){
            if(YEAR > 2017){
                YEAR--;
                yearNumb.setText(""+YEAR);
                if(YEAR == 2017){
                leftYear.setEnabled(false);
                }
            }
            monthNumb = 0;
            monthName.setText(EngMonthName[monthNumb]);
            writeCalendar(monthNumb+1,YEAR,YEAR+1);
        }
        
        if(source == rightYear){
            YEAR++;
            yearNumb.setText(""+YEAR);
            leftYear.setEnabled(true);
            leftMonth.setEnabled(true);
            monthNumb = 0;
            monthName.setText(EngMonthName[monthNumb]);  
            getDates();
            writeCalendar(monthNumb+1,YEAR,YEAR-1);
        }
        
        if(source == leftMonth){
            if(monthNumb > 0)
            monthNumb--;
            monthName.setText(EngMonthName[monthNumb]);
            if(monthNumb == 0 && YEAR == 2017){
                leftMonth.setEnabled(false);
            }
            if(monthNumb == 0 && YEAR > 2017){
                monthNumb = 11;
                YEAR--;
                yearNumb.setText(""+YEAR);
                if(YEAR == 2017){
                    leftYear.setEnabled(false);
                }
                monthName.setText(EngMonthName[monthNumb]);
            }
            getDates();
            writeCalendar(monthNumb+1,YEAR,YEAR);
        }
        
        if(source == rightMonth){
            if(monthNumb <= 11){
            monthNumb++;
            
            monthName.setText(EngMonthName[monthNumb]);
            }
//            if(monthNumb >= 1){
//                leftMonth.setEnabled(true);
//            }
            if(monthNumb >= 1){
                leftMonth.setEnabled(true);
            }
            
            
            if(monthNumb > 11){
                YEAR++;
                yearNumb.setText(""+YEAR);
                if(YEAR >= 2018){
                leftYear.setEnabled(true);
                } 
                monthNumb = 0;
                monthName.setText(EngMonthName[monthNumb]);
                leftMonth.setEnabled(true);
            }
            getDates();            
            writeCalendar(monthNumb+1,YEAR,YEAR);
        }
        
    }
}