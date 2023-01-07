package api;

import java.awt.image.AreaAveragingScaleFilter;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadFromFile{

    public ArrayList<User> temp=new ArrayList<>();

    /**
     * diabasma users apo arxeio
     * @return
     * @throws IOException
     */
    public ArrayList<User> readUsers() throws IOException {
        File fileText=new File("src/Users.txt");
        BufferedReader reader=new BufferedReader(new FileReader(fileText));
        String line=null;
        String[] data;
        while( (line= reader.readLine()) !=null )
        {

            /*data=line.split(",");
            User n=new User();
            n.setName(data[0]);
            n.setPassword(data[1]);
            n.setRole(data[2]);
            temp.add(n);*/
            data=line.split(",");
            User n=new User();
            n.setName(data[0]);
            n.setLastname(data[1]);
            n.setPassword(data[2]);
            n.setRole(data[3]);
            temp.add(n);
        }
        return temp;
    }

    /**
     * enimerosi toy arxeioy users
     * @param x
     * @throws IOException
     */
    public void changeFileUsers(ArrayList<User> x) throws IOException {

        File tempFile=new File("src/Users.txt");
        PrintWriter writer=new PrintWriter(new FileWriter(tempFile));
        String line=null;

        for (User k:x) {
            line=k.getName()+","+k.getLastname()+","+k.getPassword()+","+k.getRole();
            writer.println(line);
            writer.flush();
        }
        writer.close();
    }

    /**
     * diabasma gia Accomodation
     * @return
     * @throws IOException
     */
    ArrayList<Accomodation> temp1=new ArrayList<>();
    public ArrayList<Accomodation> readAccomodations() throws IOException {
        File fileText=new File("src/Accomodations.txt");
        BufferedReader reader=new BufferedReader(new FileReader(fileText));
        String line=null;
        String[] data1;
        String[] data2;//xrishmopoihte gia na dwsw times apo to string poy periexei oles tis paroxes mazemenes xwrismenes me -, se ena Givens[9] toy ka8e accomodation me ka8e stoixeio na einai mia paroxi (thea,mpanio..parking)
        int i=0;
        while( (line= reader.readLine()) !=null )
        {

            data1=line.split(",");
            Accomodation w=new Accomodation(data1[0],data1[1],data1[2],data1[3],data1[4],data1[5],data1[6],data1[7]);
            data2=w.toArray(data1[7]);//kalw tin toArray poy dinei times se ka8e paroxi i toy Givens[9] toy accomodation w apo to string me tis paroxes.
            w.setGivens1(data2);//dinw to array poy dimioyrgisa ws timi gia na arxikopoihsw to Givens[9] toy ka8e accomodation.
            temp1.add(w);
        }
        return temp1;
    }

    /**
     * enimerosi gia accomodation toy idi yparxontos arxeioy
     * @param x
     * @throws IOException
     */
    public void changeFileAccomodations(ArrayList<Accomodation> x) throws IOException {

        File tempFile=new File("src/Accomodations.txt");
        PrintWriter writer=new PrintWriter(new FileWriter(tempFile));
        String line=null;

        for (Accomodation k:x) {
            line=k.getHost()+","+k.getName()+","+k.getType()+","+k.getLocation()+","+k.getAdress()+","+k.getTK()+","+k.getDescription()+","+k.getGivens();
            writer.println(line);
            writer.flush();
        }
        writer.close();

    }


    /**
     * Διαβασμα τον comments για καθε accomodation.Το διαβασμα γινεται με τον εξης τροπο: Οταν διαβασω το χαρακτηρα ---->>
     * ξερω οτι μετα απο αυτον ακολουθουν 4 διαδοχικες σειρες οπου η πρωτη αφορα το ονομα του Accomodation,
     * η δευτερη αφορα το ονομα του User, η τριτη το σχολιο το οποιο εκανε ο user στο accomodation,
     * η τεταρτη το βαθμο που εδωσε ο user στο accomodation.
     * @return
     * @throws IOException
     */

    public ArrayList<Comment> temp2=new ArrayList<>();
    public ArrayList<Comment> readComments() throws IOException {
        File fileText=new File("src/Comments.txt");
        BufferedReader reader=new BufferedReader(new FileReader(fileText));
        String line=null;
        String s="---->>";
        int flag=0,k=0;
        Comment c=new Comment();
        while( (line= reader.readLine()) !=null )
        {
            if(line.equals(s))
            {
                flag=1;
                k=1;
                continue;
            }
            if(flag==1 && k>0 && k<=5)
            {
                // System.out.println(k+" "+line);
                if(k==1) {
                    c.setAccomodation(line);
                }
                if(k==2)
                    c.setUser(line);
                if(k==3)
                    c.setComment(line);
                if(k==4)
                    c.setRate(line);
                if(k==5)
                    c.setDate(line);
                k++;
                if(k==6) {
                    temp2.add(c);
                    c=new Comment();
                    k=0;
                    flag=0;
                }
            }

        }

        return temp2;
    }

    /**
     * eisagwgh comments sto arxeio.
     * @param x
     * @throws IOException
     */
    public void changeFileComments(ArrayList<Comment> x) throws IOException {

        File tempFile=new File("src/Comments.txt");
        PrintWriter writer=new PrintWriter(new FileWriter(tempFile));
        String line=null;

        for (Comment k:x) {
            line="---->>"+"\n"+k.getAccomodation()+"\n"+k.getUser()+"\n"+k.getComment()+"\n"+k.getRate()+"\n"+k.getDate();
            writer.println(line);
            writer.flush();
        }
        writer.close();

    }

    public ArrayList<User> getUsers()
    {
        return this.temp;
    }

    public ArrayList<Accomodation> getAccomodations()
    {
        return this.temp1;
    }

    public ArrayList<Comment> getComments()
    {
        return this.temp2;
    }


}
