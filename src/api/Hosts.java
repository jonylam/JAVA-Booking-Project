package api;

import java.io.IOException;
import java.util.ArrayList;

public class Hosts extends User{

    ReadFromFile r=new ReadFromFile();

    public Hosts(){

    }
    public Hosts(String name,String lastname,String password,String role) throws IOException {
        super(name,lastname,password,role);
    }

    /**
     *
     * @param x
     * @return
     * @throws IOException
     * Συναρτηση που προσθετη ενα νεο καταλημα του Host.Παιρνει το υπαρχον Arraylist με τα καταληματα και αφου δημιουργηθει ενα νεο καταλημα με τα
     * χαρακτηρηστικα του το προσθετει στο Arraylist.Τελος ενημερωνει το αρχειο με τα συνολικα καταληματα.
     */
    public void addAccomodation(String hostname,ArrayList<Accomodation> x,Accomodation newaccomodation) throws IOException
    {
        x.add(newaccomodation);

        r.changeFileAccomodations(x);
    }

    /**
     * @param x
     * @param AccomodationName
     * @return
     * @throws IOException
     * Συναρτηση που παιρνει ως παραμετρο το Arraylist με τα Accomodation και ενα Name ενος καταληματος,
     * και διαγραφει το καταλημα με ονομα Name αφου το βρει στο Arraylist.Επειτα ενημερωνεται το αρχειο με τα καταληματα.
     */
    public void deleteAccomodation(String hostname,ArrayList<Accomodation> x,ArrayList<Comment> comments,Accomodation AccomodationName) throws IOException
    {
        //μπορω νομιζω και να την κανω void.
        for(Accomodation k:x)
        {
            if(k.getHost().equals(hostname))
            {
                if(k.getName().equals(AccomodationName.getName()))
                {
                    x.remove(k);
                    //delete τα comments για το συγκεκριμενο Accomodation.
                    for(Comment c:comments)
                    {
                        if(c.getAccomodation().equals(k.getName()))
                        {
                            comments.remove(c);
                            r.changeFileComments(comments);
                        }
                    }

                    break;
                }

            }
        }
        r.changeFileAccomodations(x);

    }

    /**
     *
     * @param x
     * @param k
     * Συναρτηση που υλοποιει το dashboard του host.Εμφανιζει για καθε καταλημα το ονομα του, το τυπο του και την τοποθεσια του.
     * Επειτα εμφανιζει τον Μεσο Ορο καθε καταληματος του Host που την καλει,Εαν δεν εχει εμφανιζει το αντιστοιχο μηνυμα.
     * Τελος εμφανιζει τα συνολικα σχολια απο ολα τα καταλυματα του host αλλα και τον συνολικο Μεσο ορο απο ολες τις αξιολογησεις συνολικα.
     */
    public ArrayList<String> dashboard(String hostname,ArrayList<Accomodation> x,ArrayList<Comment> k)
    {
        ArrayList<String> temp=new ArrayList<>();
        int count=0;// Μετραει το πληθος απο τις συνολικες αξιολογησεις του host
        int count1; //Μετραει το πληθος απο τις αξιολογησεις ενος καταληματος του host σε καθε επαναληψη
        int sum=0; //Αθροισμα βαθμολογιας για τις συνολικες αξιολογησεις του host
        int sum1; //Αθροισμα βαθμολογιας ενος καταληματος του host σε καθε επαναληψη
        for(Accomodation a:x)
        {
            count1=0;
            sum1=0;
            for(Comment w:k)
            {
                if (w.getAccomodation().equals(a.getName())) //Ελεγχος για το αν το καταλημα a ταυτιζεται με το καταλημα που αναφερεται η αξιολογηση w.
                {
                    //Ελεγχος για το αν ο host που καλει την συναρτηση ταυτιζεται με τον host του καταληματος a,που πρεπει να ειναι ο host
                    // του καταληματος της αξιολογησης w.
                    if (a.getHost().equals(hostname)) {

                        count1++;
                        count++;
                        Integer i = Integer.valueOf(w.getRate()); //Μετατροπη του βαθμου rate της αξιολογησης απο String σε Integer
                        sum += i;
                        sum1+=i;

                    }
                }

            }
            if(a.getHost().equals(hostname)) //Ελεγχος για το αν το καταλημα ανηκει στον host
            {
                //System.out.println("Accomodation: " + a.getName());
                //System.out.println("Type: " + a.getType());
                //System.out.println("Location: " + a.getLocation());

                temp.add("Accomodation: " + a.getName());
                temp.add("Type: " + a.getType());
                temp.add("Location: " + a.getLocation());
            }
            if(count1!=0) //Εμφανιζει τον Μεσο ορο του καθε καταληματος που ανηκει στον host
            {
                double mo1=(double)sum1/count1;
                //System.out.println("M.O "+a.getName()+": "+String.format("%.2f",mo1));
                temp.add("M.O "+a.getName()+": "+String.format("%.2f",mo1));
            }
            else // Αν δεν υπαρχουν σχολια για το καταλημα του host εμφανιζει το αντιστοιχο μηνυμα
            {
                if(a.getHost().equals(hostname)) {
                    //System.out.println("No comments for Accomodation " + a.getName() + " which is " + a.getHost() + "s");
                    temp.add("No comments for Accomodation " + a.getName() + " which is " + a.getHost() + "s");
                }
            }


        }

        if(count!=0) //τα συνολικα σχολια του host απο ολα τα καταληματα του
        {
            //System.out.println("TotalComments: "+count+" for host "+hostname);
            temp.add("TotalComments: "+count+" for host "+hostname);
            double mo=(double)sum/count;
            //System.out.println("M.O Of Total Comments from all host accomodations: "+String.format("%.2f",mo));
            temp.add("M.O Of Total Comments from all host accomodations: "+String.format("%.2f",mo));
        }
        else //συνολικα σχολια του host 0
        {
            //System.out.println("TotalComments: " + count + " for host " + hostname);
            temp.add("TotalComments: " + count + " for host " + hostname);
        }


        return temp;
    }


    public boolean newHost(ArrayList<User> x,String name,String lastname,String password) throws IOException {

        int flag=0;//an flag=1 tote to username yparxei diaforetika oxi.

        //elegxos an yparxei to username kai an yparxei epistrefw false diaforetika dimioyrgw to host kai epistrefw true.
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
            User tempUser=new Hosts(name,lastname,password,"host");
            x.add(tempUser);
            r.changeFileUsers(x);
            return true;
        }

    }
}

