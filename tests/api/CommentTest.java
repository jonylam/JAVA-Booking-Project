package api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommentTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    public void setDate() {
        Comment comment=new Comment("User1","Accomodation1","poly xalia katalima, htan poly bromika.","1","19/2/22");
        comment.setDate("00/00/00");
        assertEquals("00/00/00",comment.getDate());
    }

    @Test
    public void setAccomodation() {
        Comment comment=new Comment("User1","Accomodation1","poly xalia katalima, htan poly bromika.","1","19/2/22");
        comment.setAccomodation("Acc1");
        assertEquals("Acc1",comment.getAccomodation());
    }

    @Test
    public void setComment() {
        Comment comment=new Comment("User1","Accomodation1","poly xalia katalima, htan poly bromika.","1","19/2/22");
        comment.setComment("poly bromiko");
        assertEquals("poly bromiko",comment.getComment());
    }

    @Test
    public void setRate() {
        Comment comment=new Comment("User1","Accomodation1","poly xalia katalima, htan poly bromika.","1","19/2/22");
        comment.setRate("2");
        assertEquals("2",comment.getRate());
    }

    @Test
    public void setUser() {
        Comment comment=new Comment("User1","Accomodation1","poly xalia katalima, htan poly bromika.","1","19/2/22");
        comment.setUser("User2");
        assertEquals("User2",comment.getUser());
    }

    @Test
    public void getUser() {
        Comment comment=new Comment();
        comment.setUser("User2");
        assertEquals("User2",comment.getUser());
    }

    @Test
    public void getAccomodation() {
        Comment comment=new Comment();
        comment.setAccomodation("Acc1");
        assertEquals("Acc1",comment.getAccomodation());
    }

    @Test
    public void getComment() {
        Comment comment=new Comment();
        comment.setComment("poly bromiko");
        assertEquals("poly bromiko",comment.getComment());
    }

    @Test
    public void getRate() {
        Comment comment=new Comment();
        comment.setRate("2");
        assertEquals("2",comment.getRate());
    }

    @Test
    public void getDate() {
        Comment comment=new Comment();
        comment.setDate("00/00/00");
        assertEquals("00/00/00",comment.getDate());
    }
}