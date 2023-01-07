package api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    public void getName() {
        User user=new User();
        user.setName("takis");
        assertEquals("takis",user.getName());
    }

    @Test
    public void getLastname() {
        User user=new User();
        user.setLastname("tsoykalas");
        assertEquals("tsoykalas",user.getLastname());
    }

    @Test
    public void setLastname() {
        User user=new User("Takis","konstantinidis","123","Host");
        user.setLastname("tsoykalas");
        assertEquals("tsoykalas",user.getLastname());
    }

    @Test
    public void getPassword() {
        User user=new User();
        user.setPassword("1234");
        assertEquals("1234",user.getPassword());
    }

    @Test
    public void getRole() {
        User user=new User();
        user.setRole("User");
        assertEquals("User",user.getRole());
    }

    @Test
    public void setName() {
        User user=new User("Takis","konstantinidis","123","Host");
        user.setName("takis");
        assertEquals("takis",user.getName());
    }

    @Test
    public void setPassword() {
        User user=new User("Takis","konstantinidis","123","Host");
        user.setPassword("1234");
        assertEquals("1234",user.getPassword());
    }

    @Test
    public void setRole() {
        User user=new User("Takis","konstantinidis","123","Host");
        user.setRole("User");
        assertEquals("User",user.getRole());
    }
}