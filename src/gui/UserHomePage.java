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
import java.util.ArrayList;

public class UserHomePage extends JFrame implements ActionListener {


    private JTextField writeΑttribute;
    private JButton searchAccomodation;
    private JButton logoutButton;
    private JPanel northPanel;
    private JList accomodationPanel;
    private JList dashBoardList;

    private JPanel finalPanel;

    private JFrame frame;
    JScrollPane w;

    JButton dashboardButton;

    JComboBox comboBox;

    DefaultListModel model2 = new DefaultListModel();


    ArrayList<User> users;
    ArrayList<Accomodation> accomodations;
    ArrayList<Comment> comments;

    private String name;

    public UserHomePage(ArrayList<User> users, ArrayList<Accomodation> accomodations, ArrayList<Comment> comments, String name) {
        this.users = users;
        this.accomodations = accomodations;
        this.comments = comments;
        this.name=name;


        frame= new JFrame();
        finalPanel = new JPanel(); //το τελικο panel που θα τοποθετηθει στο frame και περιεχει ολα τα panels που χρησιμοποιουνται στην κλαση
        northPanel = new JPanel(); // περιεχει την αναζητηση την ταξινομιση και logout, και τοποθετειται στο πανω μερος του frame
        accomodationPanel = new JList(); // περιεχει τη λιστα με τα accomodations και τα χαρακτηριστικα τους

        //φτιαχνω τα στοιχεια που θα προσθεσω στο πανω μερος της αρχικης οθονης του User
        dashboardButton = new JButton("DashBoard");
        dashboardButton.addActionListener(this);
        dashboardButton.setFocusable(false);

        logoutButton = new JButton("Logout");
        logoutButton.addActionListener(this);
        logoutButton.setFocusable(false);

        writeΑttribute = new JTextField("Search Accomodation");
        writeΑttribute.setPreferredSize( new Dimension( 200, 24 ) );
        searchAccomodation = new JButton("Search");
        searchAccomodation.setFocusable(false);
        searchAccomodation.addActionListener(this);

        //δημιουργω μια λιστα οπου επιλεγει ο χρηστης με βαση ποιο χαρακτηριστικο των accomodations θα κανει ταξινομιση
        String[] sorts = {"sort by name", "sort by type", "sort by location", "sort by givens"};

        comboBox = new JComboBox(sorts);
        comboBox.addActionListener(this);

        //προσθετω στο northPanel τα χαρακτηριστικα που θα εμφανιζει τα οποια τοποθετουνται το ενα διπλα στο αλλο με FlowLayout
        FlowLayout flow = new FlowLayout();
        northPanel.setLayout(flow);

        northPanel.add(writeΑttribute);
        northPanel.add(searchAccomodation);
        northPanel.add(comboBox);
        northPanel.add(dashboardButton);
        northPanel.add(logoutButton);


        //οριζω το παχος και το μηκος καθε κελιου στη λιστα
        accomodationPanel = new JList();
        accomodationPanel.setFixedCellWidth(70);
        accomodationPanel.setFixedCellHeight(70);


        //γεμιζω καθε κελι με τα χαρακτηριστικα του καθε accomodation με τον συγκεκριμενο τροπο
        for (Accomodation accomodation : accomodations) {
            model2.addElement("Accomondation Name: "+accomodation.getName()+" , Type: " + accomodation.getType() +" , Location: "+ accomodation.getLocation() +" , Adress: "+accomodation.getAdress() +" , TK: "+accomodation.getTK()+" , Description: "+ accomodation.getDescription() +" , Givens: "+ accomodation.getGivens());
        }

        accomodationPanel.setModel(model2);

        //ο χρηστης επιλεγει ενα accomodation και εμφανιζεται νεο παραθυρο με τις ενεργειες που μπορει να κανει πανω σ αυτο
        accomodationPanel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                //η συναρτηση convertAccomodationName μου δινει το καταλυμα που εχει κλικαρει ο χρηστης
                Accomodation chosenAccomodation = convertAccomodationName(accomodations, accomodationPanel);
                AccomodationWindow aw = new AccomodationWindow(chosenAccomodation,users,accomodations,comments,name);
                dispose();


            }
        });

        /*
        τοποθετω στο finalPanel ολα τα panels που χρειαζεται να εχει, τα οποια διατασσονται με μορφη borderLayout,
        και το προσθετω στο παραθυρο που προβαλλεται στον χρηστη
         */
        BorderLayout border = new BorderLayout();
        finalPanel.setLayout(border);

        finalPanel.add(northPanel, BorderLayout.NORTH);
        finalPanel.add(accomodationPanel, BorderLayout.CENTER);

        finalPanel.add(new JScrollPane(accomodationPanel));
        frame.add(finalPanel);

        this.setContentPane(finalPanel);
        this.setVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(dim.width, dim.height);
        this.setLocationRelativeTo(null);
        this.setTitle("User Homepage");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }


    /*
    αναλογως το ειδος ταξινομισης που επιλεγει ο χρηστης στο παραθυρο που του εμφανιζεται, γινεται ταξινομιση ως προς
    τον τροπο που εχει επιλεξει
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == comboBox) {
            Users u = new Users();
            ArrayList<Accomodation> acc;


            JComboBox cb = (JComboBox)e.getSource();
            String message = (String)cb.getSelectedItem();
            switch (message){
                case "sort by name":  acc =u.SearchByName(accomodations);
                    break;
                case "sort by type": acc =u.SearchByType(accomodations);
                    break;
                case "sort by location" :  acc =u.SearchByLocation(accomodations);
                    break;
                case "sort by givens":  acc =u.SearchByFacilities(accomodations);
                    break;
                default:    acc = new ArrayList<>();
            }


            /* Το model2 περιειχε ολα τα accomodations με τυχαια σειρα. Αφου ο χρηστης επελεξε ενα ειδος ταξινομησης, διαγραφω
             ολα τα accomodations του model2 και του προσθετω τα accomodations που περιεχει το acc τα οποια ειναι ταξινομημενα
             */
            model2.removeAllElements();

            for (Accomodation accomodation : acc) {
                model2.addElement("Accomondation Name: "+accomodation.getName()+" , Type: " + accomodation.getType() +" , Location: "+ accomodation.getLocation() +" , Adress: "+accomodation.getAdress() +" , TK: "+accomodation.getTK()+" , Description: "+ accomodation.getDescription() +" , Givens: "+ accomodation.getGivens());
            }

            accomodationPanel.setModel(model2);

        }

        /**
         * Αν πατησω το κουμπι logout θα εμφανιστει το αντιστοιχο μηνυμα και αν πατησω yes θα κανει logout, ενω αν πατησω οχι η το Χ θα μεινει εντος του προγραμματος.
         * Αν κανει logout θα με παει ταυτοχρονα στην σελιδα την αρχικη που θα πρεπει να ξανα δωσω τα στοιχεια ενος Χρηστη για να ταυτοποιηθει.
         */
        if(e.getSource()==logoutButton)
        {
            int response=JOptionPane.showConfirmDialog(this,"Do you want to logout?","Confirm",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
            if(response==JOptionPane.YES_OPTION)
            {
                this.dispose();

                new login(users,accomodations,comments);
            }

        }

        if(e.getSource()==searchAccomodation)
        {
            int flag=0; //xrisimopoieitai gia to an brw to katalima to opoio kanw search
            String search= writeΑttribute.getText();

            /**
             * αναζητω το καταλυμα που εγραψε ο User στο search bar και αν υπαρχει διαγραφω την τρεχουσα λιστα με τα καταλυματα
             * που εχει το JList στο panel και προσθετω σ αυτην μονο το καταλυμα που εχει κανει αναζητηση ο User
             */
            for(Accomodation accomodation:accomodations)
            {
                if(accomodation.getName().equals(search))
                {
                    flag=1; // brika to katalima poy kanw search
                    model2.removeAllElements();
                    model2.addElement("Accomondation Name: "+accomodation.getName()+" , Type: " + accomodation.getType() +" , Location: "+ accomodation.getLocation() +" , Adress: "+accomodation.getAdress() +" , TK: "+accomodation.getTK()+" , Description: "+ accomodation.getDescription() +" , Givens: "+ accomodation.getGivens());
                    accomodationPanel.setModel(model2);
                    break;
                }
            }

            /**
             * εμφανιζω ολα τα καταλυματα αν flag=0 γιατι δεν βρεθηκε καποιο καταλυμα με βαση το search που εκανε ο host.
             * Διαγραφω ολη την model2 και τη δημιουργω ξανα με ολα τα συνολικα καταλυματα.
             */
            if(flag==0){
                model2.removeAllElements();
                for (Accomodation accomodation : accomodations) {
                    model2.addElement("Accomondation Name: "+accomodation.getName()+" , Type: " + accomodation.getType() +" , Location: "+ accomodation.getLocation() +" , Adress: "+accomodation.getAdress() +" , TK: "+accomodation.getTK()+" , Description: "+ accomodation.getDescription() +" , Givens: "+ accomodation.getGivens());
                }

                accomodationPanel.setModel(model2);
            }
        }


        if(e.getSource()==dashboardButton)
        {
            Users u=new Users();

            //παιρνω το arraylist με τα στοιχεια που χρειαζομαι απο την κληση της dashboard για τον user που ειναι logged in.
            ArrayList<String> dash=u.dashboard(name,accomodations,comments);

            //δημιουργω το JList μου για να εμφανιζω τα καταλληλα στοιχεια.
            dashBoardList=new JList<>();
            DefaultListModel das = new DefaultListModel();

            for(int i=0;i<dash.size();i++)
            {
                das.addElement(dash.get(i));
            }
            dashBoardList.setModel(das);
            dashBoardList.setFixedCellHeight(70);

            //δημιουργω το JFrame για το dashBoard του user.
            JFrame dashBoardFrame=new JFrame();
            dashBoardFrame.setLayout(new BorderLayout());

            //ονομαζω το dashboard με το επιθετο του user που ειναι logged in.
            for(User x:users){
                if(x.getName().equals(name))
                {
                    JLabel userdashBoard=new JLabel(x.getLastname()+"'s DashBoard");
                    dashBoardFrame.add(userdashBoard,BorderLayout.NORTH);
                    break;
                }
            }


            //Panel για τα κουμπια του Dashboard, οποια και αν ειναι αυτα.
            JPanel dashBoardButtons=new JPanel();
            JButton back=new JButton("Back");
            back.setFocusable(false);
            dashBoardButtons.add(back);

            //Αν ο User πατησει το back τοτε το παραθυρο του dashboard θα κλεισει και θα επιστρεψει στην αρχικη οθονη
            back.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new UserHomePage(users,accomodations,comments,name);
                    dashBoardFrame.dispose();
                }
            });

            //ο χρηστης επιλεγει απο το JList dashBoardList στο dashBoardFrame ενα Accomodation
            // και εμφανιζεται η καρτελα για το Accomodation αυτο(AccomodationWindow).

            dashBoardList.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    for(Accomodation w:accomodations)
                    {
                        if(dashBoardList.getSelectedValue().equals("For Accomodation: "+w.getName()) ) {
                            new AccomodationWindow(w, users, accomodations, comments, name);
                            dashBoardFrame.dispose();
                        }
                    }
                }
            });

            //χαρακτηριστικα του DashBoardFrame
            dashBoardFrame.add(dashBoardButtons,BorderLayout.SOUTH);
            dashBoardFrame.add(dashBoardList,BorderLayout.CENTER);
            dashBoardFrame.add(new JScrollPane(dashBoardList));
            dashBoardFrame.setSize(700, 700);
            dashBoardFrame.setVisible(true);
            dashBoardFrame.setLocationRelativeTo(null);
            dashBoardFrame.setTitle("User DashBoard");
            dashBoardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.dispose();
        }

    }



    /*
    Η συναρτηση αυτη χρησιμοποιειται για βρω ποιο accomodation ταυτιζεται με το accomodation που εχει κλικαρει ο host στην
    λιστα. Για να γινει αυτος ο ελγχος μετατρεπω καθε accomodation σε string (το name) στην μορφη που εχει γραφτει στην λιστα
    Επειτα ελεγχω αν ειναι ιδια, και αν ειναι τοτε θα επιστρεψω αυτο το accomodation
     */
    public Accomodation convertAccomodationName(ArrayList<Accomodation> accomodation, JList accomodationPanel){

        Accomodation tempAcc = new Accomodation();
        String name;

        for(Accomodation a: accomodation){
            name=("Accomondation Name: "+a.getName()+" , Type: " + a.getType() +" , Location: "+ a.getLocation() +" , Adress: "+a.getAdress() +" , TK: "+a.getTK()+" , Description: "+ a.getDescription() +" , Givens: "+ a.getGivens());
            if(name.equals(accomodationPanel.getSelectedValue())) {
                tempAcc = a;
                break;
            }
        }


        return tempAcc;
    }

}