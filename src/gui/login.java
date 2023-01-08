package gui;

import api.Accomodation;
import api.Comment;
import api.User;
import api.Hosts;
import api.ReadFromFile;
import api.Users;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class login implements ActionListener {

    ArrayList<User> users;
    ArrayList<Accomodation> accomodations;
    ArrayList<Comment> comments;
    JFrame frame=new JFrame();
    JButton loginButton=new JButton("LogIn");//κουμπι για να κανω εισοδο με τα στοιχεια username, password που εδωσα.
    JButton register=new JButton("Register");//κουμπι για να κανω εγγραφη.
    JTextField userIDField=new JTextField();//πεδιο για να δωσω το username κατα την εισοδο.
    JPasswordField userPasswordField=new JPasswordField();//πεδιο για να δωσω το password κατα την εισοδο.
    JLabel userIDLabel =new JLabel("UserName:");//label για το username κατα την εισοδο.
    JLabel userPasswordLabel =new JLabel("Password:");//label για τον κωδικο κατα την εισοδο.

    /**
     * label που χρησιμοποιειται για να εμφανιζω το καταλληλο μηνυμα αν δεν υπαρχει ο χρηστης κατα την εισοδο,η για το αν εδωσα λαθος κωδικο χρηστη.
     */
    JLabel loginMessage =new JLabel();

    /**
     * label που χρησιμοποιειται για να εμφανιζω το καταληλο μηνυμα αν υπαρχει ο χρηστης κατα την εγγραφη
     * ή για να εμφανιζω το μηνυμα αν καποιο απο τα πεδια username, password ειναι κενα.
     */
    JLabel registerMessage =new JLabel("");


    //οταν ο χρηστης κανει εγγραφη, αν ειναι User τοτε κανει κλιν στην κουκιδα του User, ενω αν ειναι Host στην κουκιδα του Host
    JRadioButton radioButtonUser;
    JRadioButton radioButtonHost;

    public login(ArrayList<User> users,ArrayList<Accomodation> accomodations,ArrayList<Comment> comments)
    {
        this.users=users;
        this.accomodations=accomodations;
        this.comments=comments;


        //δινω τις καταληλες διαστασεις στα label για το username,password για το login.
        userIDLabel.setBounds(50,100,75,25);
        userPasswordLabel.setBounds(50,150,75,25);

        //δινω τις καταληλες διαστασεις στα fields για το username,password για το login.
        userIDField.setBounds(125,100,200,25);
        userPasswordField.setBounds(125,150,200,25);

        //δινω τις καταληλες διαστασεις στα loginbutton και register button.
        loginButton.setBounds(125,200,100,25);
        loginButton.setFocusable(false);
        loginButton.addActionListener(this);

        register.setBounds(225,200,100,25);
        register.setFocusable(false);
        register.addActionListener(this);

        //διασταση για το μηνυμα αναλογα με το αν υπαρχει ο χρηστης, η εδωσα λαθος κωδικο χρηστη.
        loginMessage.setBounds(125,250,2500,35);


        //προσθετω ολα τα στοιχεια που θα εμφανιστουν στον χρηστη στο παραθυρο
        frame.add(userIDLabel);
        frame.add(userPasswordLabel);
        frame.add(userIDField);
        frame.add(userPasswordField);
        frame.add(loginButton);
        frame.add(register);
        frame.add(loginMessage);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,420);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==loginButton)
        {
            //παιρνω τις τιμες που εδωσα στα textFields για username,password.
            String username=userIDField.getText();
            String password=String.valueOf(userPasswordField.getPassword());

            //ελεγχος για το αν υπαρχει ο χρηστης. Αν υπαρχει ελεγχω αν ειναι user η host για να καλεσω τις αντιστοιχες κλασεις και συναρτησεις.
            int flag=0;
            for(User x:users)
            {
                if(x.getName().equals(username))
                {
                    flag=1;
                    if(x.getPassword().equals(password))
                    {
                        //αν ο χρηστης ειναι User ανοιγω την αρχικη οθονη του User
                        if(x.getRole().equals("user"))
                        {
                            UserHomePage usergui=new UserHomePage(users,accomodations,comments,x.getName());
                            frame.dispose();

                        }
                        if(x.getRole().equals("host"))
                        {
                            //Αν ο χρηστης ειναι Host ανοιγω την αρχικη οθονη του Host
                            HostHomePage hostgui=new HostHomePage(users,accomodations,comments,x.getName());
                            frame.dispose();
                        }


                    }
                    else
                    {
                        loginMessage.setText("Wrong password.");
                    }

                }

            }
            if(flag==0)
                loginMessage.setText("user doesnt exist.");
        }

        if(e.getSource()==register)
        {
            //δημιουργω ενα νεο Frame1 για να δινω εκει (νεο username και  νεο password) για να γινει το register.
            JFrame frame1=new JFrame();

            //δημιουργω την επιλογη για το αν θα ειναι host η user ο χρηστης προς εγγραφη.
            JPanel TypeOfUser=new JPanel();
            TypeOfUser.setLayout(new FlowLayout(FlowLayout.CENTER));
            TypeOfUser.setBounds(70,50,300,40);

            radioButtonUser=new JRadioButton("user");
            radioButtonHost=new JRadioButton("host");
            radioButtonUser.setSelected(true);

            ButtonGroup group=new ButtonGroup();
            group.add(radioButtonUser);
            group.add(radioButtonHost);

            TypeOfUser.add(radioButtonUser);
            TypeOfUser.add(radioButtonHost);
            frame1.add(TypeOfUser);

            //τα labels για να δωθουν τα νεα username,password.
            JLabel newusernameLabel=new JLabel("Username:");
            JLabel newlastnameLabel=new JLabel("Last Name:");
            JLabel newpasswordLabel=new JLabel("Password:");
            //τα textfield για τα νεα username,password
            JTextField newusernameField=new JTextField();
            JTextField newlastnameField=new JTextField();
            JTextField newpasswordField=new JTextField();
            //το κουμπι που κανει submit και θα εμφανιζω το αναλογο μηνυμα αν υπαρχει το username η θα πραγματοποιει την εγγραφη
            JButton submit=new JButton("Submit");
            //το κουμπι που θα με πηγαινει στην αρχικη οθονη του login.
            JButton back=new JButton("Back");

            //ορια και τιμες για καθενα απο τα label,field,back,submit.
            newusernameLabel.setBounds(50,100,75,25);
            newlastnameLabel.setBounds(50,150,75,25);
            newpasswordLabel.setBounds(50,200,75,25);

            newusernameField.setBounds(125,100,200,25);
            newlastnameField.setBounds(125,150,200,25);
            newpasswordField.setBounds(125,200,200,25);

            submit.setBounds(225,250,100,25);
            submit.setFocusable(false);
            submit.addActionListener(this);

            back.setBounds(125,250,100,25);
            back.setFocusable(false);
            //το κουμπι back που θα με παει στην αρχικη login
            back.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new login(users,accomodations,comments);
                    frame1.dispose();
                }
            });

            //το κουμπι submit που θα πρατει αναλογα αν υπαρχει το username η οχι.Αν καποιο πεδιο ειναι κενο απο τα username η password θα το ζητα υποχρεωτικα.
            submit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String newusername= newusernameField.getText();
                    String newlastname=newlastnameField.getText();
                    String newpassword=newpasswordField.getText();
                    registerMessage.setBounds(125,300,2500,35);
                    frame1.add(registerMessage);
                    Users u =new Users();
                    Hosts h=new Hosts();
                    if(newusername.equals("") || newpassword.equals("") || newlastname.equals(""))
                    {
                        registerMessage.setText("Fill All TextFields.");
                    }
                    else {
                        try {
                            if(radioButtonUser.isSelected()) {
                                if (u.newUser(users, newusername,newlastname,newpassword)) {
                                    new UserHomePage(users, accomodations, comments, newusername);
                                    frame1.dispose();
                                } else
                                    registerMessage.setText("Username Already Exists!");
                            }
                            if(radioButtonHost.isSelected()){
                                if (h.newHost(users, newusername,newlastname, newpassword)) {
                                    new HostHomePage(users, accomodations, comments, newusername);
                                    frame1.dispose();
                                } else
                                    registerMessage.setText("Username Already Exists!");
                            }

                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
            });


            //προσθετω ολα τα στοιχεια που θα εμφανιστουν στον χρηστη στο παραθυρο
            frame1.add(back);
            frame1.add(submit);
            frame1.add(newusernameField);
            frame1.add(newlastnameField);
            frame1.add(newpasswordField);
            frame1.add(newusernameLabel);
            frame1.add(newlastnameLabel);
            frame1.add(newpasswordLabel);
            frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame1.setSize(420,420);
            frame1.setLayout(null);
            frame1.setVisible(true);
            frame1.setLocationRelativeTo(null);
            frame.dispose();
        }
    }
}
