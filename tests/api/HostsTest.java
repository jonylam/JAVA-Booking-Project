package api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class HostsTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    public void getName() {
        Hosts user=new Hosts();
        user.setName("takis");
        assertEquals("takis",user.getName());
    }

    @Test
    public void getLastname() {
        Hosts user=new Hosts();
        user.setLastname("tsoykalas");
        assertEquals("tsoykalas",user.getLastname());
    }

    @Test
    public void setLastname() throws IOException {
        Hosts user=new Hosts("Takis","konstantinidis","123","Host");
        user.setLastname("tsoykalas");
        assertEquals("tsoykalas",user.getLastname());
    }

    @Test
    public void getPassword() {
        Hosts user=new Hosts();
        user.setPassword("1234");
        assertEquals("1234",user.getPassword());
    }

    @Test
    public void getRole() {
        Hosts user=new Hosts();
        user.setRole("User");
        assertEquals("User",user.getRole());
    }

    @Test
    public void setName() throws IOException {
        Hosts user=new Hosts("Takis","konstantinidis","123","Host");
        user.setName("takis");
        assertEquals("takis",user.getName());
    }

    @Test
    public void setPassword() throws IOException {
        Hosts user=new Hosts("Takis","konstantinidis","123","Host");
        user.setPassword("1234");
        assertEquals("1234",user.getPassword());
    }

    @Test
    public void setRole() throws IOException {
        Hosts user=new Hosts("Takis","konstantinidis","123","Host");
        user.setRole("User");
        assertEquals("User",user.getRole());
    }

    /**
     * Το τεστ παιρνει σε ενα arraylist τα συνολικα καταληματα.Επειτα, αφου δημιουργει εναν host δημιουργει και ενα καταλημα, το οποιο προσθετει
     * μεσο της μεθοδου που τεσταρουμε στο arraylist.Αν το τελευταιο στοιχειο του arraylist που περιεχει ολα τα συνολικα καταληματα ειναι το ιδιο
     * με αυτο που προσθεσα τοτε η μεθοδος λειτουργει σωστα.
     * @throws IOException
     */
    @Test
    public void addAccomodation() throws IOException {


        ReadFromFile r=new ReadFromFile();

        Hosts host=new Hosts("takis","tsoykalas","123","host");

        ArrayList<Accomodation> accomodations=r.readAccomodations();

        ArrayList<Comment> comments=new ArrayList<>();

        Accomodation accomodation=new Accomodation("takis","Acc1","diari","serres","leoforoy nikis","52344","description","givens");

        host.addAccomodation("takis",accomodations,accomodation);

        /*for(int i=0;i<accomodations.size();i++)
            System.out.println(accomodations.get(i).getName());
        System.out.println("----");*/

        assertEquals("Acc1", accomodations.get(accomodations.size() - 1).getName() );

        // host.deleteAccomodation("takis",accomodations,comments,accomodation);

        /*for(int i=0;i<accomodations.size();i++)
            System.out.println(accomodations.get(i).getName());
        System.out.println("----");*/

    }

    /**
     * Η μεθοδος δοκιμαζει την διαγραφη ενος καταληματος απο τα ηδη υπαρχοντα που ειναι στο arraylist που περιεχει ολα τα συνολικα καταληματα.Ετσι,
     * διαγραφω το στοιχειο που προσθεσα στο προηγουμενο τεστ της addAccomodation στο arraylist γιατι μεσο εκεινης της μεθοδου ανανεωνεται και το αρχειο
     * με τα accomodations,οποτε και προσθετει ενα επιπλεον καταλημα πραγμα που δν θελω.Ετσι,η μεθοδος διαγραφει το καταλημα και επειδη χρειαζεται και το arraylist
     * με τα comments του δημιουργω ενα κενο, εφοσον το καταλημα για την δοκιμη των δυο συναρτησεων δν εχει σχολια.
     * Προσεχω να τρεξω πρωτα το προηγουμενο τεστ ωστε να δημιουργηθει το accomodation και μετα να το διαγραψω.
     * @throws IOException
     */

    @Test
    public void deleteAccomodation() throws IOException {
        ReadFromFile r=new ReadFromFile();

        Hosts host=new Hosts("takis","tsoykalas","123","host");

        ArrayList<Accomodation> accomodations=r.readAccomodations();

        ArrayList<Comment> comments=r.readComments();

        host.deleteAccomodation("takis",accomodations,comments,accomodations.get(accomodations.size() - 1));

        assertNotEquals("Acc1",accomodations.get(accomodations.size() - 1).getName());

    }

    @Test
    public void dashboard() {
    }

    /**
     * Για το τεστ αυτο δημιουργω εναν νεο host.Επειτα μπορω να τον διαγραψω ωστε να μην υπαρχει πλεον στο αρχειο με τους Users αν θελω.
     * Aν θελω να τον διαγραψω βαζω σε σχολια την for και επειτα την ανανεωση αρχειου.
     * @throws IOException
     */
    @Test
    public void newHost() throws IOException {
        ReadFromFile r=new ReadFromFile();

        ArrayList<User> users=r.readUsers();

        Hosts host=new Hosts();

        assertTrue( host.newHost(users,"xristos","genikas","123") );

        //Αν θελω να μην διαγραψω τον host που δημιουργειται τρεχω ολο το τεστ βαζοντας την for σε σχολια.

        for(User x:users)
        {
            if(x.getName().equals("xristos")) {
                users.remove(x);
                break;
            }
        }

        r.changeFileUsers(users);

    }
}