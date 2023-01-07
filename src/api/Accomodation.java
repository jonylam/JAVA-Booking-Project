package api;

import java.util.ArrayList;

public class Accomodation {

    private String host;
    private String name;
    private String type;
    private String location;
    private String adress;
    private String TK;
    private String description;
    private String givens;

    private String[] Givens;//pinakas Givens opoy ka8e stoixeio toy antoistixh stin katalili paroxi toy accomodation apo tis 9 sinolika(1.thea .... 9.parking).


    public Accomodation()
    {
        host = null;
        name = null;
        type = null;
        location = null;
        description = null;
        givens = null;
        Givens=new String[9];
    }

    public Accomodation(String host,String name,String type,String location,String adress,String TK,String description,String givens)
    {
        this.adress=adress;
        this.TK=TK;
        this.host=host;
        this.name=name;
        this.type=type;
        this.location=location;
        this.description=description;
        this.givens=givens;
        Givens=new String[9];
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTK() {
        return TK;
    }

    public String getAdress() {
        return adress;
    }

    public void setTK(String TK) {
        this.TK = TK;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setHost(String host){this.host=host;}

    public void setType(String type) {
        this.type = type;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setGivens(String givens) {
        this.givens = givens;
    }

    public String getHost(){return host;}
    public String getName()
    {return name;}
    public String getType()
    {return type;}
    public String getLocation()
    {return location;}
    public String getDescription()
    {return description;}
    public String getGivens()
    {return givens;}


    //sinartisi poy dimioyrgei san ena string tis paroxes toy katalimatos apo ton pinaka Givens me tis paroxes toy katalimatos
    public String toString(String[] Givens){
        String a =null;
        for(int i=0;i<9;i++)
        {
            if(i==0) {
                if(Givens[i].equals(" ")) {
                    a =" ";
                    continue;
                }
                a = Givens[i];
                continue;
            }
            if(Givens[i].equals(" "))
            {
                a=a+"-"+" ";
            }
            else{
                a=a+"-"+Givens[i];
            }
            if(i==8)
                a=a+"-";
        }
        return a;
    }
    public String[] toArray(String givens){
        String [] tempGivens=new String[9];
        String []data=givens.split("-");
        return data;
    }
    public void setGivens1(String[] Givens){
        this.Givens=Givens;
    }

    public String[] getGivens1(){return Givens;}
}
