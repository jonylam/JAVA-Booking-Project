package api;

import java.util.ArrayList;

public class User {

    private String name; //ονομα χρηστη

    private String lastname;//Επιθετο χρηστη
    private String password; //κωδικος χρηστη

    private String role; //Ρολος χρηστη.


    public User(){
        this.name=null;
        this.password=null;
        this.role=null;
        this.lastname=null;
    }
    public User(String name,String lastname,String password,String role){
        this.name=name;
        this.password=password;
        this.role=role;
        this.lastname=lastname;
    }


    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

