package api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class Users extends User{
    ReadFromFile r=new ReadFromFile();

    public Users(){}
    public Users(String name,String lastname,String password,String role){
        super(name,lastname,password,role);
    }

    /**
     * Συναρτηση που επιστρεφει τα καταλυματα ταξινομημενα με βαση το ονομα τους.
     */
    public ArrayList<Accomodation> SearchByName(ArrayList<Accomodation> x)
    {

        ArrayList<Accomodation> tempAcc=x;

        Collections.sort(tempAcc, new Comparator<Accomodation>()
        {
            public int compare(Accomodation a1, Accomodation a2)
            {
                return Integer.valueOf(a1.getName().compareTo(a2.getName()));
            }

        });

        return tempAcc;
    }

    /**
     * Συναρτηση που επιστρεφει τα καταλυματα ταξινομημενα με βαση το τυπο τους.
     */
    public ArrayList<Accomodation> SearchByType(ArrayList<Accomodation> x)
    {

        ArrayList<Accomodation> tempAcc=x;

        Collections.sort(tempAcc, new Comparator<Accomodation>()
        {
            public int compare(Accomodation a1, Accomodation a2)
            {
                return Integer.valueOf(a1.getType().compareTo(a2.getType()));
            }

        });

        return tempAcc;
    }

    /**
     * Συναρτηση που επιστρεφει τα καταλυματα ταξινομημενα με βαση την τοποθεσια τους.
     */
    public ArrayList<Accomodation> SearchByLocation(ArrayList<Accomodation> x)
    {

        ArrayList<Accomodation> tempAcc=x;

        Collections.sort(tempAcc, new Comparator<Accomodation>()
        {
            public int compare(Accomodation a1, Accomodation a2)
            {
                return Integer.valueOf(a1.getLocation().compareTo(a2.getLocation()));
            }

        });
        return tempAcc;
    }

    /**
     * Συναρτηση που επιστρεφει τα καταλυματα ταξινομημενα με βαση τις παροχες τους.
     */
    public ArrayList<Accomodation> SearchByFacilities(ArrayList<Accomodation> x)
    {

        ArrayList<Accomodation> tempAcc=x;

        Collections.sort(tempAcc, new Comparator<Accomodation>()
        {
            public int compare(Accomodation a1, Accomodation a2)
            {
                return Integer.valueOf(a1.getDescription().compareTo(a2.getDescription()));
            }

        });

        return tempAcc;
    }

    /**
     * Συναρτηση που εισαγει μια αξιολογηση σε ενα καταλυμα.
     */
    public void setRating(String name,ArrayList<Comment> c,String Accomodation,String comment,String rate,String date) throws IOException {

        Comment tempComment=new Comment(name,Accomodation,comment,rate,date);

        c.add(tempComment);

        r.changeFileComments(c);

    }


    /**
     * Συναρτηση που διαγραφει μια αξιολογηση απο ενα καταλυμα.
     */
    public void deleteRating(String name,ArrayList<Comment> c,String Accomodation,String comment,String rate) throws IOException {
        for(Comment x:c)
        {
            if(x.getUser().equals(name))
            {

                if (x.getComment().equals(comment))
                {
                    if(x.getRate().equals(rate))
                    {
                        c.remove(x);
                        break;
                    }
                }

            }
        }
        r.changeFileComments(c);

    }

    /**
     *Συναρτηση που δημιουργει ενα χρηστη.
     *Θα πρεπει να ελενχω αν υπαρχει το name ηδη, και αν δεν υπαρχει να δημιουργω τον Χρηστη και να ανανεωνω το αρχειο με τους χρηστες.
     */
    public boolean newUser(ArrayList<User> x,String name,String lastname,String password) throws IOException {

        int flag=0;

        for(User w:x)
        {
            if(w.getName().equals(name))
            {
                flag=1;
                break;
            }

        }
        if(flag==1)
            return false;
        else
        {
            User tempUser=new Users(name,lastname,password,"user");
            x.add(tempUser);
            r.changeFileUsers(x);
            return true;
        }

    }

    /**
     *
     * @param x
     * @param k
     * Συναρτηση που υλοποιει το dashboard του Χρηστη που την καλει.Αρχικα βρισκει απο τον user που την καλει τις αξιολογησεις του απο το Arraylist Κ,
     * και αφου βρει για ποια καταληματα εχει δωσει αξιολογησεις, βρισκει απο το Arraylist Χ που περιεχει ολα τα καταληματα τα στοιχεια που
     * χρειαζεται να παρω για τα καταληματα αυτα.Επειτα υπολογιζει τον Μεσο ορο αξιολογησεων που εχει ο χρηστης συνολικα απο ολα τα καταληματα
     * που εχει αξιολογησει.
     */
    public ArrayList<String> dashboard(String name,ArrayList<Accomodation> x,ArrayList<Comment> k)
    {
        ArrayList<String> temp=new ArrayList<>();
        int count=0; //Πληθος απο καταληματα που εχει αξιολογησει ο user
        int sum=0; //Αθροισμα βαθμων των αξιολογησεων του user
        for(Comment w:k)
        {
            if(name.equals(w.getUser())) //Ταυτοποιηση του user που καλει την συναρτηση με αυτον που εχει κανει την αξιολογηση.
            {
                for(Accomodation a:x)
                {
                    if(w.getAccomodation().equals(a.getName())) //Ταυτοποιηση του ονοματος του καταληματος της αξιολογησης με το ονομα του καταληματος.
                    {
                        /*System.out.println("Accomodation: "+w.getAccomodation());
                        System.out.println("Type: "+a.getType());
                        System.out.println("Location: "+a.getLocation());
                        System.out.println("Description: "+a.getDescription());
                        System.out.println("Givens: "+a.getGivens());
                        System.out.println("Rate: "+w.getRate());
                        System.out.println("Date: "+w.getDate());*/

                        temp.add("For Accomodation: "+w.getAccomodation());
                        temp.add("Type: "+a.getType());
                        temp.add("Location: "+a.getLocation());
                        temp.add("Adress: "+a.getAdress());
                        temp.add("TK: "+a.getTK());
                        temp.add("Rate: "+w.getRate());


                        Integer i=Integer.valueOf(w.getRate()); //Μετατροπη του βαθμου rate της αξιολογησης απο String σε Integer
                        sum+=i;
                        count+=1;
                    }
                }

            }
        }
        if(count!=0) //Συνολικος μεσος ορος αξιολογησεων του User απο ολα τα καταληματα που αξιολογησε.
        {
            double mo=(double)sum/count;
            temp.add("Average rate of total comments: "+String.format("%.2f",mo));
            // System.out.println("M.O: "+String.format("%.2f",mo));
        }
        else //Ο user δεν εχει κανει καμια αξιολογηση
        {
            // System.out.println("No comments from user " + name);
            temp.add("No comments from user " + name);
        }
        return temp;
    }

}
