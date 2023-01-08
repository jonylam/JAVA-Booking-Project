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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

public class SetRatings extends JFrame implements ActionListener {


    String username;
    String chosenAccomodation;

    JTextField Rating;
    JTextField Date;
    JTextField Comment;
    JButton sumbit;
    ArrayList<Comment> comments;
    JButton back;
    ArrayList<User> users;
    ArrayList<Accomodation> accomodations;

    JPanel buttonPanel;

    JPanel centerPanel;
    JLabel submitMessage;
    JButton delete;
    JButton save;
    JTextField Commentold;
    JTextField Rateold;


    public SetRatings(String username,ArrayList<User>users,ArrayList<Accomodation>accomodations, ArrayList<Comment> comments,String chosenAccomodation){
        this.username=username;
        this.chosenAccomodation=chosenAccomodation;
        this.comments=comments;
        this.accomodations=accomodations;
        this.users=users;

        //δημιουργω το παραθυρο
        this.setVisible(true);
        this.setSize(550, 500);
        this.setLocationRelativeTo(null);
        this.setTitle("SetRating");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());

        //δημιουργω το panel που θα περιεχει τα κουμπια BACK SUBMIT.
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER,50,10));

        //δημιουργω το panel που θα περιεχει να συμπληρωθουν τα στοιχεια για να καταχωρηθει η αξιολογηση του χρηστη.
        centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());


        //πεδιο για να συμπληρωσω την βαθμολογια 1-5
        Rating=new JTextField("Set Rating: (1-5)");
        Rating.setPreferredSize(new Dimension(200,30));
        Rating.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Rating.setText("");
            }
        });

        //πεδιο για να συμπληρωσω το σχολιο
        Comment=new JTextField("Set Comment: ");
        Comment.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Comment.setText("");
            }
        });
        Comment.setPreferredSize(new Dimension(500,50));

        //πεδιο για να συμπληρωσω την ημερομηνια
        Date=new JTextField("Set Date: (day/month/year)");
        Date.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Date.setText("");
            }
        });
        Date.setPreferredSize(new Dimension(200,30));

        //panel που περιεχει τα πεδια για να συμπληρωθουν η ημερομηνια και η βαθμολογια.
        JPanel rating_date=new JPanel();
        rating_date.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel ratingword=new JLabel("Rating:");
        rating_date.add(ratingword);
        rating_date.add(Rating);
        JLabel dateword=new JLabel("    Date:");
        rating_date.add(dateword);
        rating_date.add(Date);
        rating_date.setPreferredSize(new Dimension(500,150));
        centerPanel.add(rating_date,BorderLayout.NORTH);

        //panel που περιεχει το πεδιο για να συμπληρωθει το σχολιο.
        JPanel comment=new JPanel();
        comment.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel commentword=new JLabel("Comment:");
        comment.add(commentword);
        comment.add(Comment);
        comment.setPreferredSize(new Dimension(500,150));
        centerPanel.add(comment,BorderLayout.CENTER);


        //Τα κουμπια submit,back τα οποια προσθετω στο ButtonPanel
        sumbit=new JButton("Submit");
        sumbit.setFocusable(false);
        sumbit.addActionListener(this);
        back=new JButton("Back");
        back.setFocusable(false);
        back.addActionListener(this);
        submitMessage=new JLabel("");
        buttonPanel.add(back);
        buttonPanel.add(sumbit);
        buttonPanel.add(submitMessage);
        centerPanel.add(buttonPanel,BorderLayout.SOUTH);



        this.add(centerPanel);


    }

    //2os constr
    public SetRatings(String username,ArrayList<User>users,ArrayList<Accomodation>accomodations, ArrayList<Comment> comments,String chosenAccomodation,Comment c){
        this.username=username;
        this.chosenAccomodation=chosenAccomodation;
        this.comments=comments;
        this.accomodations=accomodations;
        this.users=users;

        //δημιουργω το παραθυρο
        this.setVisible(true);
        this.setSize(550, 500);
        this.setLocationRelativeTo(null);
        this.setTitle("Proccess Rating");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());

        //δημιουργω το panel που θα περιεχει τα κουμπια back και submit.
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER,50,10));

        //δημιουργω το panel που θα περιεχει να συμπληρωθουν τα στοιχεια για να καταχωρηθει η αξιολογηση του χρηστη.
        centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());


        //πεδιο για να συμπληρωσω την βαθμολογια 1-5
        Rating=new JTextField(c.getRate());
        Rateold= new JTextField((c.getRate())); //το χρησιμοποιω για την επεξεργασια της βαθμολογιας του σχολιου, διοτι δημιουργω ενα νεο και διαγραφω το παλιο.
        Rating.setPreferredSize(new Dimension(200,30));


        //πεδιο για να συμπληρωσω το σχολιο
        Comment=new JTextField(c.getComment());
        Commentold= new JTextField((c.getComment())); //το χρησιμοποιω για την επεξεργασια του σχολιου, διοτι δημιουργω ενα νεο και διαγραφω το παλιο.
        Comment.setPreferredSize(new Dimension(500,50));

        //πεδιο για να συμπληρωσω την ημερομηνια
        Date=new JTextField(c.getDate());
        Date.setPreferredSize(new Dimension(200,30));

        //panel που περιεχει τα πεδια για να συμπληρωθουν η ημερομηνια και η βαθμολογια.
        JPanel rating_date=new JPanel();
        rating_date.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel ratingword=new JLabel("Rating:");
        rating_date.add(ratingword);
        rating_date.add(Rating);
        JLabel dateword=new JLabel("    Date:");
        rating_date.add(dateword);
        rating_date.add(Date);
        rating_date.setPreferredSize(new Dimension(500,150));
        centerPanel.add(rating_date,BorderLayout.NORTH);

        //panel που περιεχει το πεδιο για να συμπληρωθει το σχολιο.
        JPanel comment=new JPanel();
        comment.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel commentword=new JLabel("Comment:");
        comment.add(commentword);
        comment.add(Comment);
        comment.setPreferredSize(new Dimension(500,150));
        centerPanel.add(comment,BorderLayout.CENTER);


        //Τα κουμπια save και delete για να αποθηκευτει ή να διαγραφτει το σχολιο
        save=new JButton("Save");
        save.setFocusable(false);
        save.addActionListener(this);
        back=new JButton("Back");
        back.setFocusable(false);
        back.addActionListener(this);
        submitMessage=new JLabel("");
        delete=new JButton("Delete");
        delete.setFocusable(false);
        delete.addActionListener(this);
        buttonPanel.add(back);
        buttonPanel.add(save);
        buttonPanel.add(delete);
        buttonPanel.add(submitMessage);
        centerPanel.add(buttonPanel,BorderLayout.SOUTH);



        this.add(centerPanel);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //ελεγχω αν πατηθηκε το κουμπι submit και κανω τις αναλογες πραξεις καλωντας την συναρτηση setRating.
        if(e.getSource()==sumbit)
        {
            String rating=Rating.getText();
            String date=Date.getText();
            String comment=Comment.getText();


            //περιπτωση που καποιο πεδιο ειναι κενο
            if(rating.equals("") || date.equals("") || comment.equals("") || rating.equals("Set Rating: ") || date.equals("Set Date: ") || comment.equals("Set Comment: "))
            {
                submitMessage.setText("Fill All the TextFields.");
            }
            else {
                Users u = new Users();
                try {
                    u.setRating(username, comments, chosenAccomodation, comment, rating, date);
                    new UserHomePage(users, accomodations, comments, username);
                    dispose();

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        //ελεγχω αν πατηθηκε το κουμπι back και στελνω αν πατηθηκε το χρηστη στην αρχικη οθονη.
        if(e.getSource()==back)
        {
            new UserHomePage(users,accomodations,comments,username);
            dispose();
        }


        //Αν ο χρηστης επιλεξει save, διαγραφω το παλιο σχολιο που ειχε κανει και δημιοργω ενα νεο σχολιο το οποιο εισαγω στο Arraylist με τα σχολια
        if(e.getSource()==save)
        {
            Users u = new Users();
            String oldrate=Rateold.getText();
            String oldcomment=Commentold.getText();

            try {
                u.deleteRating(username,comments,chosenAccomodation,oldcomment,oldrate);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }


            String rating=Rating.getText();
            String date=Date.getText();
            String comment=Comment.getText();

            //περιπτωση που καποιο πεδιο ειναι κενο
            if(rating.equals("") || date.equals("") || comment.equals("") || rating.equals("Set Rating: ") || date.equals("Set Date: ") || comment.equals("Set Comment: "))
            {
                submitMessage.setText("Fill All the TextFields.");
            }
            else {

                try {
                    u.setRating(username, comments, chosenAccomodation, comment, rating, date);
                    new UserHomePage(users, accomodations, comments, username);
                    dispose();

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        }

        //Αν ο χρηστης επιλεξει το delete τοτε διαγραφω το σχολιο του
        if(e.getSource()==delete)
        {
            Users u = new Users();
            String oldrate=Rating.getText();
            String oldcomment=Comment.getText();

            try {
                u.deleteRating(username,comments,chosenAccomodation,oldcomment,oldrate);
                new UserHomePage(users, accomodations, comments, username);
                dispose();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        }
    }
}


