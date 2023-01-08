package gui;


import api.Accomodation;
import api.Comment;
import api.User;
import api.Hosts;
import api.ReadFromFile;
import api.Users;


import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;


public class AccomodationWindow extends JFrame implements ActionListener {


    Hosts h=new Hosts();

    //labels για καθε χαρακτηριστικο που εχει το accomodation
    private JLabel nameLabel;//το συγκεκριμενο label θα εμφανιζει στο πανω μερος της οθονης το ονομα του accomodation που επιλεξε χρηστης
    private JLabel typeLabel;
    private JLabel locationLabel;
    private JLabel adressLabel;
    private JLabel TKLabel;
    private JLabel descriptionLabel;
    private JLabel givensLabel;
    private JLabel totalCommentsLabel;
    private JLabel averageRate;



    private JPanel finalPanel;
    private JPanel centerPanel;
    private JPanel buttonPanel;




    private JButton rateAccomodationButton;
    private JButton returnButton;



    //Η λιστα που θα περιεχει τα σχολια για το accomodation
    private JList commentList;




    ArrayList<User> users;
    ArrayList<Accomodation> accomodations;
    ArrayList<Comment> comments;

    String username;
    String role;

    Accomodation chosenAccomodation;
    JLabel commentsLabel;
    JButton totalProccess;
    JButton delete;

    public AccomodationWindow(Accomodation chosenAccomodation, ArrayList<User> users, ArrayList<Accomodation> accomodations, ArrayList<Comment> comments, String name){

        this.users = users;
        this.accomodations = accomodations;
        this.comments = comments;
        this.username = name;
        this.chosenAccomodation = chosenAccomodation;


        int countComments = 0;
        int sumRating=0;

        //ελεγχω αν ο χρηστης ειναι Host ή User
        for(User x:users)
        {
            if(x.getName().equals(name))
            {
                if(x.getRole().equals("user"))
                    role="user";
                else
                    role="host";
                break;
            }
        }


        //καθε label περιγραφει ενα διαφορετικο χαρακτηριστικο του accomodation
        nameLabel = new JLabel(chosenAccomodation.getName());
        typeLabel = new JLabel(chosenAccomodation.getType());
        locationLabel = new JLabel(chosenAccomodation.getLocation());
        adressLabel = new JLabel(chosenAccomodation.getAdress());
        TKLabel = new JLabel(chosenAccomodation.getTK());
        descriptionLabel = new JLabel(chosenAccomodation.getDescription());
        givensLabel = new JLabel(chosenAccomodation.getGivens());
        totalCommentsLabel = new JLabel();
        averageRate = new JLabel("M.O of Total Comments: ");



        //στοιχιζω τα κουμπια στην ιδια γραμμη.
        buttonPanel = new JPanel(); // σ αυτο μπαινουν τα κουμπια για να υποβαλλει αξιολογησεις ή να παει στην αρχικη οθονη
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50,20));

        //καθε button περιγραφει τη λειτουργια που θα εκτελεσει ο χρηστης αν το πατησει

        //τι θα κανει το Set Rating αν ειναι συνδεμενος ο user ωστε να αφησει αξιολογηση.Αν ειναι συνδεμενος ενας host δεν εχει επιλογη αξιολογησης.
        if(role.equals("user"))
        {
            rateAccomodationButton = new JButton("Set Rating");
            rateAccomodationButton.setFocusable(false);
            rateAccomodationButton.addActionListener(this);
            buttonPanel.add(rateAccomodationButton);
        }

        if(role.equals("host"))
        {
            if(chosenAccomodation.getHost().equals(name)) {
                totalProccess = new JButton("Proccess");
                totalProccess.setFocusable(false);
                totalProccess.addActionListener(this);
                buttonPanel.add(totalProccess);

                delete = new JButton("Delete");
                delete.addActionListener(this);
                delete.setFocusable(false);
                buttonPanel.add(delete);
            }
        }

        //Τι θα κανει το Back κουμπι αμα πατηθει.
        returnButton = new JButton("Back");
        returnButton.setFocusable(false);
        returnButton.addActionListener(this);
        buttonPanel.add(returnButton);






        //το centerPanel ειναι το panel που αποτελειται απο ολα τα στοιχεια που εμφανιζονται στο κεντρο της οθονης
        centerPanel = new JPanel();
        GridLayout grid = new GridLayout(10,1,10,10);
        centerPanel.setLayout(grid);

        //το finalPanel ειναι το panel που αποτελειται απο ολα τα υπολοιπα panels και εμφανιζει το τελικο αποτελεσμα στην οθονη
        finalPanel = new JPanel();
        BorderLayout board = new BorderLayout();
        finalPanel.setLayout(board);





        // η λιστα θα περιεχει ολα τα σχολια που εχει λαβει το καταλυμα
        commentList = new JList();
        DefaultListModel aComment = new DefaultListModel();


        commentList.setFixedCellWidth(1000);
        commentList.setFixedCellHeight(70);


        //Εξεταζω για καθε σχολιο, απο το Arraylist με τα σχολια, αν αντιστοιχει στο accomodation που ελεγχω. Αν ναι, τοτε θα προστεθει στο commentList.
        for(Comment comment:comments)
        {
            if(comment.getAccomodation().equals(chosenAccomodation.getName())) {
                aComment.addElement("User: " + comment.getUser()+ "   Rate: " + comment.getRate() + "   Date: " + comment.getDate() + "   Comment: " + comment.getComment());
                countComments++;
                sumRating+=Integer.parseInt(comment.getRate());
            }
        }

        commentList.setModel(aComment);


        //ο χρηστης επιλεγει ενα comment και εμφανιζεται νεο παραθυρο με τις ενεργειες που μπορει να κανει πανω σ αυτο συμφωνα με τους περιορισμους.
        if(role.equals("user")) {
            commentList.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {

                    String name1 = null;
                    Comment tempComment = null;

                    //Βρισκω ποιο comment εχει επιλεχθει.
                    for (Comment c : comments) {
                        name1 = ("User: " + c.getUser() + "   Rate: " + c.getRate() + "   Date: " + c.getDate() + "   Comment: " + c.getComment());
                        if (name1.equals(commentList.getSelectedValue())) {
                            tempComment = c;
                            break;
                        }
                    }

                    //Eλεγχω αν το σχολιο που επιλεγω με το ποντικι ειναι του χρηστη που ειναι συνδεμενος ωστε να προβω σε πραξεις επανω σε αυτο το σχολιο.
                    if(tempComment.getUser().equals(name)) {
                        new SetRatings(name, users, accomodations, comments, chosenAccomodation.getName(), tempComment);
                        dispose();
                    }

                }
            });
        }



        //label που εμφανιζει την βαθμολογια για το καταλυμα αν εχει εστω και ενα σχολιο.
        if(countComments != 0)
            averageRate.setText("Rate out of 10: " + Double.toString((double) sumRating/countComments));
        else
            averageRate.setText("Rate out of 10: -");



        //Το label που θα εμφανιζει τα συνολικα σχολια του καταλυματος.
        totalCommentsLabel.setText("Total comments for this Accomodation: " + Integer.toString(countComments));


        //βαζω στο centerpanel ολα τα χαρακτηριστικα του καταλυματος.
        centerPanel.add(typeLabel);
        centerPanel.add(locationLabel);
        centerPanel.add(adressLabel);
        centerPanel.add(TKLabel);
        centerPanel.add(descriptionLabel);
        centerPanel.add(givensLabel);
        centerPanel.add(totalCommentsLabel);
        centerPanel.add(averageRate);

        //label που θα εμφανιζει τα σχολια παρακατω.
        commentsLabel =new JLabel("Comments:");
        centerPanel.add(commentsLabel);
        centerPanel.add(commentList);
        centerPanel.add(new JScrollPane(commentList));


        centerPanel.setPreferredSize(new Dimension(5,5));


        //τοποθετω τα labels στο τελικο panel το οποιο βλεπει ο χρηστης
        finalPanel.add(nameLabel, BorderLayout.NORTH);
        finalPanel.add(centerPanel, BorderLayout.CENTER);
        finalPanel.add(buttonPanel, BorderLayout.SOUTH);



        this.add(finalPanel);
        this.setContentPane(finalPanel);

        this.setVisible(true);
        this.setSize(1000, 900);
        this.setLocationRelativeTo(null);
        this.setTitle("Accomodation " + chosenAccomodation.getName());
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //αν ο Host πατησει αυτο το κουμπι, τοτε ανοιγει νεο παραθυρο για την επεξεργασια του accomodation και κλεινει το παραθυρο που ειναι ηδη ανοιχτο
        if(e.getSource()==totalProccess)
        {
            new HostAccomodationWindow(chosenAccomodation,users,accomodations,comments,username);
            this.dispose();
        }

        //αν ο Host πατησει αυτο το κουμπι, τοτε το accomodation διαγραφεται και ο host επιστρεφει στην αρχικη οθονη
        if(e.getSource()==delete)
        {
            try {
                h.deleteAccomodation(username,accomodations,comments,chosenAccomodation);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            new HostHomePage(users, accomodations, comments, username);
            this.dispose();
        }

        //αν ο χρηστης πατησει αυτο το κουμπι και ειναι Host θα επιστρεψει στην αρχικη οθονη του Host, ενω αν ειναι User θα επιστρεψει στην αρχικκη οθονη του User
        if(e.getSource() == returnButton){
            if(role.equals("user")) {
                new UserHomePage(users, accomodations, comments, username);
                dispose();
            }
            else {
                new HostHomePage(users, accomodations, comments, username);
                dispose();
            }
        }
        if(e.getSource() == rateAccomodationButton){

            try {
                new SetRatings(username,users,accomodations,comments,chosenAccomodation.getName());
                dispose();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}



