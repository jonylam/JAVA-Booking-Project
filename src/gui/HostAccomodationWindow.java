package gui;

import api.Accomodation;
import api.Comment;
import api.User;
import api.Hosts;
import api.ReadFromFile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;



public class HostAccomodationWindow implements ActionListener {
    ReadFromFile r=new ReadFromFile();
    Accomodation n=new Accomodation();
    Hosts h=new Hosts();

    ArrayList<User> users;
    ArrayList<Accomodation> accomodations;
    ArrayList<Comment> comments;


    private String name;
    private Accomodation ChosenAccomodation;

    JButton submit;
    String[]dataPrint; // χρησιμοποιω την dataPrint οπου θα βαλω τα Givens του accomodation


    //καθε label ζηταει απο τον χρηστη να συμπληρωσει το χαρακτηριστικο του accomodation που ζηταει
    JLabel labelAccName = new JLabel("Give Accomodation Name * ");
    JLabel labelAccType = new JLabel("Give Accomodation Type * ");
    JLabel labelAccLocation = new JLabel("Give Accomodation Location * ");
    JLabel labelAccAdress = new JLabel("Give Accomodation Adress * ");
    JLabel labelAccTk = new JLabel("Give TK for the Accomodation * ");
    JLabel labelAccDesc = new JLabel("Give Accomodation Description * ");
    JLabel labelAccView = new JLabel("Give Accomodation view");
    JLabel labelAccBath = new JLabel("Give Accomodation Bath");
    JLabel labelAccWash = new JLabel("Give Accomodation Wash");
    JLabel labelAccTv = new JLabel("Give Accomodation Tv");
    JLabel labelAccWarm = new JLabel("Give Accomodation warm");
    JLabel labelAccNet = new JLabel("Give Accomodation Net");
    JLabel labelAccKitchen = new JLabel("Give Accomodation Kitchen");
    JLabel labelAccOut = new JLabel("Give Accomodation Out");
    JLabel labelAccParking = new JLabel("Give Accomodation Parking");


    //το συγκεκριμενο label εμφανιζεται στην οθονη σε περιπτωση που ο χρηστης δεν εχει συμπληρωσει καποιο υποχρεωτικο πεδιο
    JLabel errorLabel=new JLabel();


    // Καθε textfield μπαινει διπλα στο label που ζηταει το αντιστοιχο δεδομενο απο το accomodation
    JTextField accName=new JTextField("Give Accomodation Name");
    JTextField accType=new JTextField("Give Accomodation Type");
    JTextField accLocation=new JTextField("Give Accomodation Location");
    JTextField accAdress=new JTextField("Give Accomodation Address");
    JTextField accTk=new JTextField("Give TK From Location");
    JTextField accDesc=new JTextField("Give Accomodation Description");
    JTextField accView=new JTextField("Give Accomodation View");
    JTextField accBath=new JTextField("Give Accomodation bath");
    JTextField accWash=new JTextField("Give Accomodation wash");
    JTextField accTv=new JTextField("Give Accomodation Tv");
    JTextField accWarm=new JTextField("Give Accomodation warm");
    JTextField accNet=new JTextField("Give Accomodation Internet");
    JTextField accKitchen=new JTextField("Give Accomodation kitchen");
    JTextField accOut=new JTextField("Give Accomodation outside");
    JTextField accParking=new JTextField("Give Accomodation parking");

    JFrame frame=new JFrame();

    //Το κουμπι save αποθηκευει τις αλλαγες στο accomodation που εχει κανει ο host και το delete διαγραφει το accomodation που εχει επιλεξει
    //Το κουμπι back επιστρεφει στην αρχικη οθονη χωρις να γινει καποια αλλαγη
    JButton save;
    JButton delete;
    JButton back;



    //κλαση για την επεξεργασια ενος accomodation απο τον host
    public HostAccomodationWindow(Accomodation accomodation, ArrayList<User> users,ArrayList<Accomodation> accomodations,ArrayList<Comment> comments,String name){
        this.users = users;
        this.accomodations = accomodations;
        this.comments = comments;
        this.name = name;
        ChosenAccomodation=accomodation;

        //Αρχικοποιηση των panel και προσθηκη layouts
        JPanel panel=new JPanel(new GridLayout(15,2,-100,0));
        JPanel finalPanel = new JPanel();
        BorderLayout border = new BorderLayout();
        finalPanel.setLayout(border);
        JPanel ButtonPanel = new JPanel();
        JPanel northPanel = new JPanel();
        //το northlabel εμφανιζεται στο πανω μερος της οθονης
        JLabel northLabel = new JLabel("The fields with * must be filled");
        northPanel.add(northLabel);

        //στα textfields δινω τα στοιχεια που εχει ηδη το accomodation και αυτα γινονται ορατα στον χρηστη
        accName.setText(ChosenAccomodation.getName());
        accType.setText(ChosenAccomodation.getType());
        accLocation.setText(ChosenAccomodation.getLocation());
        accAdress.setText(ChosenAccomodation.getAdress());
        accTk.setText(ChosenAccomodation.getTK());
        accDesc.setText(ChosenAccomodation.getDescription());
        dataPrint=ChosenAccomodation.getGivens1();

        accView.setText(dataPrint[0]);
        accBath.setText(dataPrint[1]);
        accWash.setText(dataPrint[2]);
        accTv.setText(dataPrint[3]);
        accWarm.setText(dataPrint[4]);
        accNet.setText(dataPrint[5]);
        accKitchen.setText(dataPrint[6]);
        accOut.setText(dataPrint[7]);
        accParking.setText(dataPrint[8]);

        back=new JButton("Back");
        back.addActionListener(this);
        back.setFocusable(false);

        save=new JButton("Save");
        save.addActionListener(this);
        save.setFocusable(false);


        labelAccName.setLabelFor(accName);
        labelAccType.setLabelFor(accType);
        labelAccLocation.setLabelFor(accLocation);
        labelAccAdress.setLabelFor(accAdress);
        labelAccTk.setLabelFor(accTk);
        labelAccDesc.setLabelFor(accDesc);
        labelAccView.setLabelFor(accView);
        labelAccBath.setLabelFor(accBath);
        labelAccWash.setLabelFor(accWash);
        labelAccTv.setLabelFor(accTv);
        labelAccWarm.setLabelFor(accWarm);
        labelAccNet.setLabelFor(accNet);
        labelAccKitchen.setLabelFor(accKitchen);
        labelAccOut.setLabelFor(accOut);
        labelAccParking.setLabelFor(accParking);



        //προσθετω στο panel που θα τοποθετηθει στην μεση της οθονης ολα τα labels και textfields
        panel.add(labelAccName);
        panel.add(accName);

        panel.add(labelAccType);
        panel.add(accType);

        panel.add(labelAccLocation);
        panel.add(accLocation);

        panel.add(labelAccAdress);
        panel.add(accAdress);

        panel.add(labelAccTk);
        panel.add(accTk);

        panel.add(labelAccDesc);
        panel.add(accDesc);

        panel.add(labelAccView);
        panel.add(accView);

        panel.add(labelAccBath);
        panel.add(accBath);

        panel.add(labelAccWash);
        panel.add(accWash);

        panel.add(labelAccTv);
        panel.add(accTv);

        panel.add(labelAccWarm);
        panel.add(accWarm);

        panel.add(labelAccNet);
        panel.add(accNet);

        panel.add(labelAccKitchen);
        panel.add(accKitchen);

        panel.add(labelAccOut);
        panel.add(accOut);

        panel.add(labelAccParking);
        panel.add(accParking);


        // προσθετω στο ButtonPanel ολα τα κουμπια που χρησιμοποιουνται για την επεξεργασια του καταλυματος
        //Το errorLabel εμφανιζεται διπλα απο το save αν ο χρηστης δεν συμπληρωσει ολα τα υποχρεωτικα πεδια
        ButtonPanel.add(errorLabel);
        ButtonPanel.add(save);
        ButtonPanel.add(back);

        //προσθετω και οριζω την διαταξη στο τελικο panel που θα εμφανιστει στην οθονη του χρηστη
        finalPanel.add(panel,BorderLayout.CENTER);
        finalPanel.add(ButtonPanel,BorderLayout.SOUTH);
        finalPanel.add(northPanel,BorderLayout.NORTH);



        frame.add(finalPanel);
        frame.setVisible(true);
        frame.setSize(700, 700);
        frame.setLocationRelativeTo(null);
        frame.setTitle("EDIT ACCOMODATION");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    //κλαση για την προσθηκη ενος νεου accomodation απο τον host
    public HostAccomodationWindow(ArrayList<User> users,ArrayList<Accomodation> accomodations,ArrayList<Comment> comments,String name){
        this.users = users;
        this.accomodations = accomodations;
        this.comments = comments;
        this.name = name;

        //Αρχικοποιηση των panel και προσθηκη layouts
        JPanel panel=new JPanel(new GridLayout(15,2,-100,0));
        JPanel finalPanel = new JPanel();
        BorderLayout border = new BorderLayout();
        finalPanel.setLayout(border);
        JPanel ButtonPanel = new JPanel();
        JPanel northPanel = new JPanel();
        JLabel northLabel = new JLabel("The fields with * must be filled");
        northPanel.add(northLabel);


        //Το κουμπι submit αποθηκευει το νεο accomodation που δημιουργησε ο host
        //Το κουμπι back επιστρεφει στην αρχικη οθονη χωρις να γινει καποια αλλαγη
        submit=new JButton("Submit");
        submit.addActionListener(this);
        submit.setFocusable(false);

        back=new JButton("Back");
        back.addActionListener(this);
        back.setFocusable(false);


        //προσθετω στο panel που θα τοποθετηθει στην μεση της οθονης ολα τα labels και textfields
        panel.add(labelAccName);
        panel.add(accName);

        panel.add(labelAccType);
        panel.add(accType);

        panel.add(labelAccLocation);
        panel.add(accLocation);

        panel.add(labelAccAdress);
        panel.add(accAdress);

        panel.add(labelAccTk);
        panel.add(accTk);

        panel.add(labelAccDesc);
        panel.add(accDesc);

        panel.add(labelAccView);
        panel.add(accView);

        panel.add(labelAccBath);
        panel.add(accBath);

        panel.add(labelAccWash);
        panel.add(accWash);

        panel.add(labelAccTv);
        panel.add(accTv);

        panel.add(labelAccWarm);
        panel.add(accWarm);

        panel.add(labelAccNet);
        panel.add(accNet);

        panel.add(labelAccKitchen);
        panel.add(accKitchen);

        panel.add(labelAccOut);
        panel.add(accOut);

        panel.add(labelAccParking);
        panel.add(accParking);


        // προσθετω στο ButtonPanel ολα τα κουμπια που χρησιμοποιουνται για την προσθηκη του καταλυματος
        //Το errorLabel εμφανιζεται διπλα απο το submit αν ο χρηστης δεν συμπληρωσει ολα τα υποχρεωτικα πεδια
        ButtonPanel.add(errorLabel);
        ButtonPanel.add(submit);
        ButtonPanel.add(back);


        //προσθετω και οριζω την διαταξη στο τελικο panel που θα εμφανιστει στην οθονη του χρηστη
        finalPanel.add(panel,BorderLayout.CENTER);
        finalPanel.add(ButtonPanel,BorderLayout.SOUTH);
        finalPanel.add(northPanel,BorderLayout.NORTH);

        frame.add(finalPanel);
        frame.setVisible(true);
        frame.setSize(700, 700);
        frame.setLocationRelativeTo(null);
        frame.setTitle("NEW ACCOMODATION");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==back)
        {
            //αν ο host πατησει το back επιστρεφει στην αρχικη οθονη
            new HostHomePage(users,accomodations,comments,name);
            frame.dispose();
        }

        if(e.getSource()==submit){
            //αν ο host πατησει το submit τοτε ή προσθετει το accomodation ή εμφανιζει μηνυμα να συμπληρωσει ολα τα πεδια
            String accName1 = accName.getText();
            String accType1 = accType.getText();
            String accLocation1 = accLocation.getText();
            String accAdress1 = accAdress.getText();
            String accTk1 = accTk.getText();
            String accDesc1 = accDesc.getText();

            //περιπτωση που καποιο κενο με * δεν ειναι συμπληρωμενο
            if(accName1.equals("") || accType1.equals("") || accLocation1.equals("") || accAdress1.equals("") || accTk1.equals("") || accDesc1.equals("")) {

                errorLabel.setText("Fill all the gaps with *");
            }
            else {


                String[] Givens = new String[9];
                Givens[0] = accView.getText();
                Givens[1] = accBath.getText();
                Givens[2] = accWash.getText();
                Givens[3] = accTv.getText();
                Givens[4] = accWarm.getText();
                Givens[5] = accNet.getText();
                Givens[6] = accKitchen.getText();
                Givens[7] = accOut.getText();
                Givens[8] = accParking.getText();


                for (int i = 0; i < Givens.length; i++) {
                    if (Givens[i].equals("")) {
                        Givens[i] = " ";
                    }
                }

                String givens = n.toString(Givens);

                Accomodation a = new Accomodation(name, accName1, accType1, accLocation1, accAdress1, accTk1, accDesc1, givens);
                a.setGivens1(Givens);
                try {
                    h.addAccomodation(name, accomodations, a);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                new HostHomePage(users, accomodations, comments, name);
                frame.dispose();
            }

        }

        if(e.getSource()==save){

            //αν ο host πατησει το save τοτε ή θα αποθηκευτουν οι αλλαγες που εκανε ή θα εμφανιστει μηνυμα για να συμπληρωσει ολα τα υποχρεωτικα πεδια
            String accname=accName.getText();
            String acctype=accType.getText();
            String acclocation=accLocation.getText();
            String accaddress=accAdress.getText();
            String acctk=accTk.getText();
            String accdesc=accDesc.getText();

            //περιπτωση που καποιο πεδιο με * δεν ειναι συμπληρωμενο
            if(accname.equals("") || acctype.equals("") || acclocation.equals("") || accaddress.equals("") || acctk.equals("") || accdesc.equals("")) {

                errorLabel.setText("Fill all the gaps with *");
            }
            else {

                dataPrint[0]=accView.getText();
                dataPrint[1]=accBath.getText();
                dataPrint[2]=accWash.getText();
                dataPrint[3]=accTv.getText();
                dataPrint[4]=accWarm.getText();
                dataPrint[5]=accNet.getText();
                dataPrint[6]=accKitchen.getText();
                dataPrint[7]=accOut.getText();
                dataPrint[8]=accParking.getText();


                for (int i = 0; i < dataPrint.length; i++) {
                    if (dataPrint[i].equals("")) {
                        dataPrint[i] = " ";
                    }
                }

                //περασμα των στοιχειων που εχει αλλαξει ο χρηστης σε ενα νεο accomodation.
                String toStringFromDataArray=n.toString(dataPrint);
                Accomodation temp=new Accomodation(name,accname,acctype,acclocation,accaddress,acctk,accdesc,toStringFromDataArray);
                temp.setGivens1(dataPrint);

                //με αυτον τον τροπο διαγραφοντας το παλιο accomodation και δημιουργοντας ενα νεο πετυχαινω την επεξεργασια του καταλυματος.
                try {
                    h.deleteAccomodation(name,accomodations,comments,ChosenAccomodation);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                try {
                    h.addAccomodation(name,accomodations,temp);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                new HostHomePage(users,accomodations,comments,name);
                frame.dispose();
            }

        }
    }
}


