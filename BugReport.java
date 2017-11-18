package OnTime;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class BugReport extends JFrame implements ActionListener{
    JLabel[] error = new JLabel[8];
    JLabel description;
    JPanel top,options,content,buttons,holder;
    JCheckBox[] option = new JCheckBox[8];
    JPanel[] line = new JPanel[8];
    JTextArea contentText;
    JScrollPane scroll;
    JButton send;
    String listOfBox = "";
    JTextField subjectSend;
    
    static Toolkit TK = Toolkit.getDefaultToolkit();
    static Dimension SCREEN_SIZE = TK.getScreenSize();
    public float screenHeight = SCREEN_SIZE.height;
    public float screenWidth = SCREEN_SIZE.width;
    
    public BugReport(){
        super("Report a bug");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        ImageIcon sendIcon = new ImageIcon("send.gif");
        
        holder = new JPanel();
        holder.setLayout(new BorderLayout());
        
        top = new JPanel();
        description = new JLabel("Here you can report a bug.");
        description.setFont(new Font("Courier New",Font.BOLD,24));
        top.add(description);
        
        options = new JPanel();
        //options.setLayout(new BoxLayout(options, BoxLayout.Y_AXIS));
        
        for(int i=0;i<option.length;i++){
            option[i] = new JCheckBox();
            
        }
        
        error[0] = new JLabel("There are problems while creating the file.");
        error[1] = new JLabel("There are problems while editing the event.");
        error[2] = new JLabel("There are problems while reading/opening the Inscruction.");
        error[3] = new JLabel("There are problems while reading/opening the License.");
        error[4] = new JLabel("There are problem with left part of the app (Calendar).");
        error[5] = new JLabel("There are problems with center part of the app (Edit a event)");
        error[6] = new JLabel("There are problems with right part of the app (List of events).");
        error[7] = new JLabel("There are problems with grammatical correctness.");
               
        for(int i =0;i<line.length;i++){
            line[i]=new JPanel();
        }
        
        for(int i=0; i<line.length;i++){
        line[i].add(option[i]);
        line[i].add(error[i]);
        }
        
        
       for(int i =0;i<line.length;i++){
            options.add(line[i]);
        }
       contentText = new JTextArea(10,34);
       contentText.setFont(new Font("Courier New",Font.PLAIN,16));
       contentText.setLineWrap(true);
       scroll = new JScrollPane(contentText,
               JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
               JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
               );
       options.add(scroll);
       subjectSend = new JTextField(34);
       subjectSend.setText("Type your name here...");
       subjectSend.setFont(new Font("Courier New",Font.PLAIN,16));
       options.add(subjectSend);
        
       buttons = new JPanel();
        send = new JButton("Send",sendIcon);
        send.addActionListener(this);
        buttons.add(send);
        
        holder.add(top,BorderLayout.NORTH);
        holder.add(options,BorderLayout.CENTER);
        holder.add(buttons,BorderLayout.SOUTH);
        setLocation((int)(screenWidth / 2.9), (int)(screenHeight / 3.8));
        add(holder);
        setVisible(true);
        setSize(400,700);
        
    }
    
    public void actionPerformed(ActionEvent event){
       Object source = event.getSource();
       if(source == send){
          String[] ToTab = { TO };
          for(int i=0;i<line.length;i++){
              if(option[i].isSelected() == true){
                  listOfBox += ""+error[i].getText()+"\n";
              }
          }
          String contentToMail = listOfBox +"\n"+contentText.getText();
         
          sendFromGmail(USER_NAME,PASSWORD,ToTab,
                  subjectSend.getText()+"- report a bug",
                  contentToMail); 
          this.dispose();
       }
    }

    private static String USER_NAME = "reportabug.ontime";
    private static String PASSWORD = "OnTime123";
    private static String TO = "OnTimeOYD@gmail.com";
    
    private static void sendFromGmail(String from, String pass, String[] to,
            String subject,String body){
        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        
        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);
        
        try {
            message.setFrom(new InternetAddress(from));
            InternetAddress[] toAddress = new InternetAddress[to.length];

            // To get the array of addresses
            for( int i = 0; i < to.length; i++ ) {
                toAddress[i] = new InternetAddress(to[i]);
            }

            for( int i = 0; i < toAddress.length; i++) {
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }

            message.setSubject(subject);
            message.setText(body);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }
        catch (AddressException ae) {
            ae.printStackTrace();
        }
        catch (MessagingException me) {
            me.printStackTrace();
        }
    }   
}
