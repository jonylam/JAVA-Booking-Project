package api;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;



public class Main {
    public static void main(String[] args) throws IOException {


        ArrayList<User> r1=new ArrayList<>();
        ArrayList<Accomodation> r2=new ArrayList<>();
        ArrayList<Comment> c=new ArrayList<>();

        ReadFromFile r=new ReadFromFile();
        /**
         * Παιρνουμε στην Main ολα τα καταλυματα που ειναι διαθεσιμα απο το αρχειο μεσο της ReadFromFile.
         */
        r2=r.readAccomodations();
        /*for(int j=0;j<r2.size();j++) {
            String[] data1 = r2.get(j).getGivens1();
            for (String a : data1) {
                System.out.println(a);
            }
        }*/

        /**
         * Παιρνουμε στην Main ολους τους χρηστες που ειναι διαθεσιμοι απο το αρχειο μεσο της ReadFromFile.
         */
        r1=r.readUsers();

        /**
         * Παιρνουμε στην Main ολα τα σχολια καταλυματων που ειναι διαθεσιμα απο το αρχειο μεσο της ReadFromFile.
         */
        c=r.readComments();

        //login loginpage=new login(r1,r2,c);
    }
}