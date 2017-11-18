package OnTime;

import java.io.*;
import java.util.*;

public class File{
    static int DAY, MONTH, YEAR, HOUR, MINUTE;
    static String TITLE = null, CONTENT = null;
    static FileReader FR;
    static FileWriter FW;
    static BufferedReader BR;
    static String line;
    static String[] TABLE_WITH_FILE_DATA = new String[100];
    static int i = 0;
    static StringTokenizer st,st_compare;
    
    
    public static void READ_LINE(int lineNumber){
        

        st = new StringTokenizer(TABLE_WITH_FILE_DATA[lineNumber],"@@");
        
        if(st.hasMoreTokens()) YEAR = Integer.parseInt(st.nextToken());
        if(st.hasMoreTokens()) MONTH = Integer.parseInt(st.nextToken());
        if(st.hasMoreTokens()) DAY = Integer.parseInt(st.nextToken());
        if(st.hasMoreTokens()) HOUR = Integer.parseInt(st.nextToken());
        if(st.hasMoreTokens()) MINUTE = Integer.parseInt(st.nextToken());
        if(st.hasMoreTokens()) TITLE = st.nextToken();
        if(st.hasMoreTokens()) CONTENT = st.nextToken();
        else CONTENT = "";
//        System.err.println(YEAR+"\t"+MONTH+"\t"+DAY+
//                           "\t"+HOUR+"\t"+MINUTE+"\t"+TITLE+"\t"+CONTENT);
        
    }
    public static int GET_DAY(int lineNumber){
        READ_LINE(lineNumber);
        return DAY;
    }
    public static int GET_MONTH(int lineNumber){
        READ_LINE(lineNumber);
        return MONTH;
    }
    public static int GET_YEAR(int lineNumber){
        READ_LINE(lineNumber);
        return YEAR;
    }
    public static int GET_HOUR(int lineNumber){
        READ_LINE(lineNumber);
        return HOUR;
    }
    public static int GET_MINUTE(int lineNumber){
        READ_LINE(lineNumber);
        return MINUTE;
    }
    public static String GET_TITLE(int lineNumber){
        READ_LINE(lineNumber);
        return TITLE;
    }
    public static String GET_CONTENT(int lineNumber){
        READ_LINE(lineNumber);
        return CONTENT;
    }
    public static int TABLE_LENGTH(){
        int length = 0;
        
        for(int i=0;i<TABLE_WITH_FILE_DATA.length;i++){
            if(TABLE_WITH_FILE_DATA[i].equals("0")){
                //System.err.println(i);
                continue;
            }
            else{
                length++;
            }
                
        }
        
        return length;
    }
    
    
    
    public static void READ(String fileName){
        for(int i=0;i<TABLE_WITH_FILE_DATA.length;i++){
            TABLE_WITH_FILE_DATA[i] = "0";
        }
        try{
            FR = new FileReader(fileName);
            BR = new BufferedReader(FR);
            
            i = 0;
            line = BR.readLine();
            
            /*
            do{ 
                line = BR.readLine();
                TABLE_WITH_FILE_DATA[i] = line;
                System.out.println(TABLE_WITH_FILE_DATA[i]);
                i++;
            }while(line != null);
            */
            
            while(line != null){             
                    
                if(line.equals("0")) {                    
                    line = BR.readLine();
                    continue; 
                }

                TABLE_WITH_FILE_DATA[i] = line;
                line = BR.readLine();
                //System.out.println(TABLE_WITH_FILE_DATA[i]);
                i++;               
            }          
            
        } catch(IOException e){
            System.err.println(e.getMessage() + "ERROR File.READ");
        } finally { 
            try {
                if(BR != null)
                    BR.close();
                if(FR != null)
                    FR.close();
            } catch(IOException e){
               System.err.println(e.getMessage() + "ERROR File.READ"); 
            }
      }
       
    }
    
    
    ////////////////////////////////////////////////////////////////////////////
     
    
    public static void SAVE(String text){
        /*try(PrintWriter out = new PrintWriter("calendar.txt","UTF-8"))
        {
            out.println(text);
            
        } catch(IOException e) {
            System.err.println(e.getMessage());
        }
        SORT();*/

        try(FileWriter FW = new FileWriter("calendar.txt",true))
        {
            
            FW.write(text);
            FW.write(System.lineSeparator());
    
            FW.close();
        } catch(IOException e) {
            System.err.println(e.getMessage());
        }
    }
    
    public static void SAVE_TAB(){
        DELETE();
        SORT();
        int length = TABLE_LENGTH();
        try(FileWriter FW = new FileWriter("calendar.txt",true)){
            for(int i=0;i<=length+1;i++){
                if(TABLE_WITH_FILE_DATA[i].equals("0")) continue;
                FW.write(TABLE_WITH_FILE_DATA[i]);
                FW.write(System.lineSeparator());
            }
        } catch (IOException e){
            System.err.println(e.getMessage());
        }
    }

    public static void SORT(){
        //DELETE();
        //Arrays.sort(TABLE_WITH_FILE_DATA);
        //for(int i = 0; i< TABLE_LENGTH();i++){
        //   System.err.println(TABLE_WITH_FILE_DATA[i]);
        //}
        //SAVE_TAB();
        int length = TABLE_LENGTH();
        int YearFirst, YearSecond, MonthFirst, MonthSecond, DayFirst,DaySecond,
                HourFirst,HourSecond,MinuteFirst,MinuteSecond;
        String buffor;
        for(int j=0;j<length*length;j++){
            for(int i =0; i < length;i++){
                st = new StringTokenizer(TABLE_WITH_FILE_DATA[i],"@@");
                if(i <= length-1) st_compare = new StringTokenizer(TABLE_WITH_FILE_DATA[i+1],"@@");

                if(st.hasMoreTokens()){

                YearFirst = Integer.parseInt(st.nextToken());
                if(st_compare.hasMoreTokens()){ 
                YearSecond = Integer.parseInt(st_compare.nextToken());

                if(YearFirst < YearSecond){
                    continue;
                }//if(YearFirst < YearSecond)
                else if(YearSecond < YearFirst){
                    //System.out.println("START   " + i);
                buffor = TABLE_WITH_FILE_DATA[i+1];
                TABLE_WITH_FILE_DATA[i+1] = TABLE_WITH_FILE_DATA[i];
                TABLE_WITH_FILE_DATA[i] = buffor;
                } else if(YearFirst == YearSecond){
                    if(st.hasMoreTokens()){
                    MonthFirst = Integer.parseInt(st.nextToken());
                    if(st_compare.hasMoreTokens()){
                    MonthSecond = Integer.parseInt(st_compare.nextToken());

                    if(MonthFirst < MonthSecond){
                        continue;
                    } else if(MonthSecond < MonthFirst){
                    buffor = TABLE_WITH_FILE_DATA[i+1];
                    TABLE_WITH_FILE_DATA[i+1] = TABLE_WITH_FILE_DATA[i];
                    TABLE_WITH_FILE_DATA[i] = buffor;
                    } else if(MonthFirst == MonthSecond){
                        if(st.hasMoreTokens()){
                        DayFirst = Integer.parseInt(st.nextToken());
                        if(st_compare.hasMoreTokens()){
                        DaySecond = Integer.parseInt(st_compare.nextToken());
                        if(DayFirst < DaySecond){
                            continue;
                        } else if(DaySecond < DayFirst){
                        buffor = TABLE_WITH_FILE_DATA[i+1];
                        TABLE_WITH_FILE_DATA[i+1] = TABLE_WITH_FILE_DATA[i];
                        TABLE_WITH_FILE_DATA[i] = buffor;
                        }else if(DayFirst == DaySecond){
                            if(st.hasMoreTokens()){
                            HourFirst = Integer.parseInt(st.nextToken());
                            if(st_compare.hasMoreTokens()){
                            HourSecond = Integer.parseInt(st_compare.nextToken());
                            if (HourFirst < HourSecond){
                            continue;
                            }//if (HourFirst < HourSecond)
                            else if (HourFirst > HourSecond){
                            buffor = TABLE_WITH_FILE_DATA[i+1];
                            TABLE_WITH_FILE_DATA[i+1] = TABLE_WITH_FILE_DATA[i];
                            TABLE_WITH_FILE_DATA[i] = buffor;
                            }//else if (HourFirst > HourSecond)
                            else if (HourFirst == HourSecond){
                                if(st.hasMoreTokens()){
                                MinuteFirst = Integer.parseInt(st.nextToken());
                                if(st_compare.hasMoreTokens()){
                                MinuteSecond = Integer.parseInt(st_compare.nextToken());
                                if (MinuteFirst < MinuteSecond){
                                continue;
                                }//if (MinuteFirst < MinuteSecond)
                                else if (MinuteFirst > MinuteSecond){
                                buffor = TABLE_WITH_FILE_DATA[i+1];
                                TABLE_WITH_FILE_DATA[i+1] = TABLE_WITH_FILE_DATA[i];
                                TABLE_WITH_FILE_DATA[i] = buffor;
                                }//else if (MinuteFirst > MinuteSecond)
                                }//if(st_compare.hasMoreTokens())
                                }//if(st.hasMoreTokens())                          
                            }//else if (HourFirst == HourSecond)
                            }//if(st_compare.hasMoreTokens())
                            }//if(st.hasMoreTokens()) 
                        }//else if(DayFirst == DaySecond)
                        }//if(st_compare.hasMoreTokens())
                        }//if(st.hasMoreTokens()){
                    }//else if(MonthFirst == MonthSecond)
                    }//if(st_compare.hasMoreTokens())
                    }//if(st.hasMoreTokens())
                }//else if(YearFirst == YearSecond)
                }//if(st_compare.hasMoreTokens())
                }//if(st.hasMoreTokens())
            }
        }       
    }
    
    
    
    ////////////////////////////////////////////////////////////////////////////
    public static void DELETE_OLD(int lineNumber){
        TABLE_WITH_FILE_DATA[lineNumber] = "0";
    }
    
        
    public static void DELETE(){
        try(FileWriter FW = new FileWriter("calendar.txt"))
        {
            FW.write("");
        } catch (IOException e){
            System.err.println(e.getMessage());
        }
        
    }
}
