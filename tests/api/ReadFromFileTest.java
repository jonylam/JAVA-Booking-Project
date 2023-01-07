package api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ReadFromFileTest {

    @BeforeEach
    void setUp() {
    }

    /**
     * Αν το arraylist που δημιουργω που περιεχει τους χρηστες δεν ειναι κενο τοτε το τεστ λειτουργει σωστα. Μπορω να το εμφανισω αν θελω για επιπλεον καληψη.
     * @throws IOException
     */
    @Test
    public void readUsers() throws IOException {
        ReadFromFile r=new ReadFromFile();

        ArrayList<User> users=r.readUsers();

        assertFalse(users.isEmpty());
    }

    /**
     * Δημιουργω το arraylist που περιεχει ολους τους χρηστες.Επιπλεον, δημιουργω εναν χρηστη , τον προσθετω στο arraylist και ανανεωνω το αρχειο με τους χρηστες.
     * Στην συνεχεια ελεγχω αν το τελευταιο στοιχειο του arraylist ειναι οντως ο χρηστης που προσθεσα, και επιπλεον βλεπω αν στο αρχειο εχει προσθεθει ο χρηστης.
     * Τελος, διαγραφω τον χρηστη απο το arraylist και ξανα ανανεωνω το αρχειο ωστε να διαγραφει ο χρηστης επειδη ειναι μονο για την υλοποιηση του τεστ.
     * @throws IOException
     */
    @Test
    public void changeFileUsers() throws IOException {
        ReadFromFile r=new ReadFromFile();

        ArrayList<User> users=r.readUsers();

        Users tempuser=new Users("kwstas","lampropoylos","123","user");

        users.add(tempuser);

        r.changeFileUsers(users);

        assertTrue(users.get(users.size()-1).getName().equals("kwstas") && users.get(users.size()-1).getLastname().equals("lampropoylos") );

        users.remove(tempuser);

        r.changeFileUsers(users);
    }

    /**
     * Αν το arraylist που δημιουργω που περιεχει τα καταληματα δεν ειναι κενο τοτε το τεστ λειτουργει σωστα. Μπορω να το εμφανισω αν θελω για επιπλεον καληψη.
     * @throws IOException
     */
    @Test
    public void readAccomodations() throws IOException {
        ReadFromFile r=new ReadFromFile();

        ArrayList<Accomodation> accomodations=r.readAccomodations();

        assertFalse(accomodations.isEmpty());
    }

    /**
     * Δημιουργω το arraylist που περιεχει ολa τa καταληματα.Επιπλεον, δημιουργω ενα καταλημα , το προσθετω στο arraylist και ανανεωνω το αρχειο με τα καταληματα.
     * Στην συνεχεια ελεγχω αν το τελευταιο στοιχειο του arraylist ειναι οντως το καταλημα που προσθεσα, και επιπλεον βλεπω αν στο αρχειο εχει προσθεθει το καταλημα.
     * Τελος, διαγραφω το καταλημα απο το arraylist και ξανα ανανεωνω το αρχειο ωστε να διαγραφει το καταλημα επειδη ειναι μονο για την υλοποιηση του τεστ.
     * @throws IOException
     */
    @Test
    public void changeFileAccomodations() throws IOException {
        ReadFromFile r=new ReadFromFile();

        ArrayList<Accomodation> accomodations=r.readAccomodations();

        Accomodation accomodation1= new Accomodation("Provider1","TestHostel","diari","serres","lewforoy","52422","Testdescr","TestGivens");

        accomodations.add(accomodation1);

        r.changeFileAccomodations(accomodations);

        assertTrue(accomodations.get(accomodations.size()-1).getName().equals("TestHostel") && accomodations.get(accomodations.size()-1).getHost().equals("Provider1"));

        accomodations.remove(accomodation1);

        r.changeFileAccomodations(accomodations);
    }

    /**
     * Αν το arraylist που δημιουργω που περιεχει τα σχολια δεν ειναι κενο τοτε το τεστ λειτουργει σωστα. Μπορω να το εμφανισω αν θελω για επιπλεον καληψη.
     * @throws IOException
     */
    @Test
    public void readComments() throws IOException {
        ReadFromFile r=new ReadFromFile();

        ArrayList<Comment> comments=r.readComments();

        assertFalse(comments.isEmpty());
    }

    /**
     * Για το τεστ αυτο δημιουργω ενα σχολιο και το προσθετω στο arraylist που δημιουργησα που περιεχει ολα τα σχολια.
     * Καλω την συναρτηση που ανανεωνει το αρχειο που περιεχει τα σχολια, και ελεγχω αν οντως το τελευταιο comment στο arraylist ειναι αυτο που δημιουργησα,
     * και βλεπω στο Αρχειο οτι εχει προστεθει το comment και οποτε το Τεστ ειναι επιτυχης.Τελος διαγραφω το σχολιο απο το arraylist με τα σχολια,
     * και ξανα ανανεωνω το αρχειο με τα σχολια για να διαγραφει το σχολιο που δημιουργησα για να υλοποιηθει η δοκιμη για το τεστ αυτο.
     * @throws IOException
     */
    @Test
    public void changeFileComments() throws IOException {
        ReadFromFile r=new ReadFromFile();

        ArrayList<Comment> comments=r.readComments();

        Comment comment1=new Comment("maria","Provider1-5","TEST","5","11/11/11");

        comments.add(comment1);

        r.changeFileComments(comments);

        assertTrue(comments.get(comments.size()-1).getComment().equals("TEST") && comments.get(comments.size()-1).getRate().equals("5") && comments.get(comments.size()-1).getDate().equals("11/11/11") && comments.get(comments.size()-1).getUser().equals("maria"));

        comments.remove(comment1);

        r.changeFileComments(comments);
    }

    /**
     * Διαβαζω πρωτα τους users και μετα καλω την συναρτηση για ενα αλλο arraylist και αν δεν ειναι κενο, τοτε λειτουργει σωστα η συναρτηση.
     * Μπορω να εμφανισω και users για επιπλεον καληψη.
     * @throws IOException
     */
    @Test
    public void getUsers() throws IOException {
        ReadFromFile r=new ReadFromFile();

        ArrayList<User> users=r.readUsers();

        ArrayList<User> temp=r.getUsers();

        assertFalse(temp.isEmpty());
    }

    /**
     * Διαβαζω πρωτα τa καταληματα και μετα καλω την συναρτηση για ενα αλλο arraylist και αν δεν ειναι κενο, τοτε λειτουργει σωστα η συναρτηση.
     * Μπορω να εμφανισω και τα καταληματα για επιπλεον καληψη.
     * @throws IOException
     */
    @Test
    public void getAccomodations() throws IOException {
        ReadFromFile r=new ReadFromFile();

        ArrayList<Accomodation> accomodations=r.readAccomodations();

        ArrayList<Accomodation> temp=r.getAccomodations();

        assertFalse(temp.isEmpty());
    }

    /**
     * Διαβαζω πρωτα τα σχολια και μετα καλω την συναρτηση για ενα αλλο arraylist και αν δεν ειναι κενο, τοτε λειτουργει σωστα η συναρτηση.
     * Μπορω να εμφανισω και users για επιπλεον καληψη.
     * @throws IOException
     */
    @Test
    public void getComments() throws IOException {
        ReadFromFile r=new ReadFromFile();

        ArrayList<Comment> comments=r.readComments();

        ArrayList<Comment> temp=r.getComments();

        assertFalse(temp.isEmpty());
    }
}