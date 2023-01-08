package api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccomodationTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    public void setName() {
        Accomodation accomodation=new Accomodation("provider1","Accomodation1","diari","Serres","lewforos-papandreoy","53100","fotino diari sto kentro tis polis","Give Accomodation View-Give Accomodation bath-Give Accomodation wash- -Give Accomodation warm-Give Accomodation Internet-Give Accomodation kitchen-Give Accomodation outside-Give Accomodation parking");
        accomodation.setName("Acc1");
        assertEquals("Acc1",accomodation.getName());
    }

    @Test
    public void getTK() {
        Accomodation accomodation=new Accomodation();
        accomodation.setTK("11111");
        assertEquals("11111",accomodation.getTK());
    }

    @Test
    public void getAdress() {
        Accomodation accomodation=new Accomodation();
        accomodation.setAdress("papandreoy");
        assertEquals("papandreoy",accomodation.getAdress());
    }

    @Test
    public void setTK() {
        Accomodation accomodation=new Accomodation("provider1","Accomodation1","diari","Serres","lewforos-papandreoy","53100","fotino diari sto kentro tis polis","Give Accomodation View-Give Accomodation bath-Give Accomodation wash- -Give Accomodation warm-Give Accomodation Internet-Give Accomodation kitchen-Give Accomodation outside-Give Accomodation parking");
        accomodation.setTK("11111");
        assertEquals("11111",accomodation.getTK());
    }

    @Test
    public void setAdress() {
        Accomodation accomodation=new Accomodation("provider1","Accomodation1","diari","Serres","lewforos-papandreoy","53100","fotino diari sto kentro tis polis","Give Accomodation View-Give Accomodation bath-Give Accomodation wash- -Give Accomodation warm-Give Accomodation Internet-Give Accomodation kitchen-Give Accomodation outside-Give Accomodation parking");
        accomodation.setAdress("papandreoy");
        assertEquals("papandreoy",accomodation.getAdress());
    }

    @Test
    public void setHost() {
        Accomodation accomodation=new Accomodation("provider1","Accomodation1","diari","Serres","lewforos-papandreoy","53100","fotino diari sto kentro tis polis","Give Accomodation View-Give Accomodation bath-Give Accomodation wash- -Give Accomodation warm-Give Accomodation Internet-Give Accomodation kitchen-Give Accomodation outside-Give Accomodation parking");
        accomodation.setHost("Provider2");
        assertEquals("Provider2",accomodation.getHost());
    }

    @Test
    public void setType() {
        Accomodation accomodation=new Accomodation("provider1","Accomodation1","diari","Serres","lewforos-papandreoy","53100","fotino diari sto kentro tis polis","Give Accomodation View-Give Accomodation bath-Give Accomodation wash- -Give Accomodation warm-Give Accomodation Internet-Give Accomodation kitchen-Give Accomodation outside-Give Accomodation parking");
        accomodation.setType("souita");
        assertEquals("souita",accomodation.getType());
    }

    @Test
    public void setLocation() {
        Accomodation accomodation=new Accomodation("provider1","Accomodation1","diari","Serres","lewforos-papandreoy","53100","fotino diari sto kentro tis polis","Give Accomodation View-Give Accomodation bath-Give Accomodation wash- -Give Accomodation warm-Give Accomodation Internet-Give Accomodation kitchen-Give Accomodation outside-Give Accomodation parking");
        accomodation.setLocation("Thessaloniki");
        assertEquals("Thessaloniki",accomodation.getLocation());
    }

    @Test
    public void setDescription() {
        Accomodation accomodation=new Accomodation("provider1","Accomodation1","diari","Serres","lewforos-papandreoy","53100","fotino diari sto kentro tis polis","Give Accomodation View-Give Accomodation bath-Give Accomodation wash- -Give Accomodation warm-Give Accomodation Internet-Give Accomodation kitchen-Give Accomodation outside-Give Accomodation parking");
        accomodation.setDescription("aplo kai fotino diari");
        assertEquals("aplo kai fotino diari",accomodation.getDescription());
    }

    @Test
    public void setGivens() {
        Accomodation accomodation=new Accomodation("provider1","Accomodation1","diari","Serres","lewforos-papandreoy","53100","fotino diari sto kentro tis polis","thea sto boyno-WC- - -tzaki-Internet-kitchen-auli-parking");
        accomodation.setGivens("thea sto boyno-WC- - -tzaki-Internet-kitchen-auli-parking");
        assertEquals("thea sto boyno-WC- - -tzaki-Internet-kitchen-auli-parking",accomodation.getGivens());
    }

    @Test
    public void getHost() {
        Accomodation accomodation=new Accomodation();
        accomodation.setHost("Provider1");
        assertEquals("Provider1",accomodation.getHost());
    }

    @Test
    public void getName() {
        Accomodation accomodation=new Accomodation();
        accomodation.setName("Lam");
        assertEquals("Lam",accomodation.getName());
    }

    @Test
    public void getType() {
        Accomodation accomodation=new Accomodation();
        accomodation.setType("diari");
        assertEquals("diari",accomodation.getType());
    }

    @Test
    public void getLocation() {
        Accomodation accomodation=new Accomodation();
        accomodation.setLocation("Serres");
        assertEquals("Serres",accomodation.getLocation());
    }

    @Test
    public void getDescription() {
        Accomodation accomodation=new Accomodation();
        accomodation.setDescription("Poly fotino kai sto kentro tis polis me ypnodomatio koyzina kai mpanio.");
        assertEquals("Poly fotino kai sto kentro tis polis me ypnodomatio koyzina kai mpanio.",accomodation.getDescription());
    }

    @Test
    public void getGivens() {
        Accomodation accomodation=new Accomodation();
        accomodation.setGivens("Give Accomodation View-Give Accomodation bath-Give Accomodation wash- -Give Accomodation warm-Give Accomodation Internet-Give Accomodation kitchen-Give Accomodation outside-Give Accomodation parking");
        assertEquals("Give Accomodation View-Give Accomodation bath-Give Accomodation wash- -Give Accomodation warm-Give Accomodation Internet-Give Accomodation kitchen-Give Accomodation outside-Give Accomodation parking",accomodation.getGivens());
    }
}