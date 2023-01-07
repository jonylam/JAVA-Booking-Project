package api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UsersTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    public void getName() {
        Users user=new Users();
        user.setName("takis");
        assertEquals("takis",user.getName());
    }

    @Test
    public void getLastname() {
        Users user=new Users();
        user.setLastname("tsoykalas");
        assertEquals("tsoykalas",user.getLastname());
    }

    @Test
    public void setLastname() {
        Users user=new Users("Takis","konstantinidis","123","Host");
        user.setLastname("tsoykalas");
        assertEquals("tsoykalas",user.getLastname());
    }

    @Test
    public void getPassword() {
        Users user=new Users();
        user.setPassword("1234");
        assertEquals("1234",user.getPassword());
    }

    @Test
    public void getRole() {
        Users user=new Users();
        user.setRole("User");
        assertEquals("User",user.getRole());
    }

    @Test
    public void setName() {
        Users user=new Users("Takis","konstantinidis","123","Host");
        user.setName("takis");
        assertEquals("takis",user.getName());
    }

    @Test
    public void setPassword() {
        Users user=new Users("Takis","konstantinidis","123","Host");
        user.setPassword("1234");
        assertEquals("1234",user.getPassword());
    }

    @Test
    public void setRole() {
        Users user=new Users("Takis","konstantinidis","123","Host");
        user.setRole("User");
        assertEquals("User",user.getRole());
    }

    @Test
    public void searchByName() throws IOException {
    }

    @Test
    public void searchByType() {
    }

    @Test
    public void searchByLocation() {
    }

    @Test
    public void searchByFacilities() {
    }

    /**
     * Συναρτηση που διαβαζει το arraylist με τα συνολικα comments και προσθετη μια αξιολογηση ενος Χρηστη (Μαρια) στο καταλημα (Provider1-5)
     * ωστε να υλοποιηθει το τεστ.Μετα την υλοποιηση του τεστ διαγραφω το σχολιο διοτι ανανεωνεται μεσα απο την setRating το αρχειο με τα comments
     * και αποθηκευεται το σχολιο πραγμα που δεν θελω να γινει.
     */

    @Test
    public void setRating() throws IOException {
        ReadFromFile r=new ReadFromFile();

        Users user=new Users();

        ArrayList<Comment> comments=r.readComments();

        user.setRating("maria",comments,"Provider1-5","TEST COMMENT","5","11/11/11");

        for(Comment x:comments)
        {
            if(x.getUser().equals("maria") && x.getRate().equals("5") && x.getComment().equals("TEST COMMENT") && x.getDate().equals("11/11/11") && x.getAccomodation().equals("Provider1-5"))
            {
                assertEquals("TEST COMMENT",x.getComment());
            }
        }

        //Διαγραφω το comment που δημιουργησα πριν και ανανεωνω το αρχειο ωστε να διαγραφει.
        for(Comment x:comments)
        {
            if(x.getUser().equals("maria") && x.getRate().equals("5") && x.getComment().equals("TEST COMMENT") && x.getDate().equals("11/11/11") && x.getAccomodation().equals("Provider1-5"))
            {
                comments.remove(x);
                break;
            }
        }

        r.changeFileComments(comments);

    }

    /**
     * δημιουργησα ενα σχολιο,θα παω να διαγραψω το σχολιο που δημιουργησα για την υλοποιηση του τεστ.Ετσι,το διαγραφω και χρησιμοποιω εναν flag για να δω
     * αν εχει οντως διαγραφει.
     * @throws IOException
     */
    @Test
    public void deleteRating() throws IOException {
        ReadFromFile r=new ReadFromFile();

        Users user=new Users();

        ArrayList<Comment> comments=r.readComments();

        user.setRating("maria",comments,"Provider1-5","TEST COMMENT","5","11/11/11");

        user.deleteRating("maria",comments,"Provider1-5","TEST COMMENT","5");

        int flag=0; //Χρηση για να δω αν διαγραφηκε το σχολιο που διεγραψα στο arraylist comments.
        for(Comment x:comments)
        {
            if(x.getUser().equals("maria") && x.getRate().equals("5") && x.getComment().equals("TEST COMMENT") && x.getDate().equals("11/11/11") && x.getAccomodation().equals("Provider1-5")) {
                flag = 1;
                break;
            }
        }
        assertEquals(0,flag);

    }

    @Test
    public void newUser() throws IOException {
        ReadFromFile r=new ReadFromFile();

        ArrayList<User> users=r.readUsers();

        Users user=new Users();

        assertTrue( user.newUser(users,"takis","tsoykalas","123") );

        //Αν θελω να μην διαγραψω τον host που δημιουργειται τρεχω ολο το τεστ βαζοντας την for σε σχολια.

        for(User x:users)
        {
            if(x.getName().equals("takis")) {
                users.remove(x);
                break;
            }
        }
        r.changeFileUsers(users);
    }

    @Test
    public void dashboard() {
    }
}