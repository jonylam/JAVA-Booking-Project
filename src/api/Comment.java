package api;

public class Comment {

    private String accomodation; //ονομα καταληματος που σχολιαζεται.
    private String user;        //ονομα χρηστη που σχολιαζει το καταλημα.
    private String comment;     //σχολιο για το καταλημα.
    private String rate;        //βαθμος αξιολογησης απο 1 εως 5.

    private String date;        //ημερομηνια αξιολογησης

    public Comment(){
        this.accomodation=null;
        this.comment=null;
        this.user=null;
        this.rate=null;
        this.date=null;
    }

    /**
     * kirios gia na eisagw comments afoy ta dimioyrgisw sto arxeio ton 8elw auton ton kostraktora
     * @param user
     * @param accomodation
     * @param comment
     * @param rate
     */
    public Comment(String user,String accomodation,String comment,String rate,String date) {
        this.accomodation=accomodation;
        this.comment=comment;
        this.user=user;
        this.rate=rate;
        this.date=date;
    }

    public void setDate(String date){this.date=date;}

    public void setAccomodation(String accomodation) {
        this.accomodation = accomodation;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUser()
    {return this.user;}

    public String getAccomodation()
    {return this.accomodation;}

    public String getComment()
    {return this.comment;}

    public String getRate()
    {return this.rate;}

    public String getDate()
    {return this.date;}

}

