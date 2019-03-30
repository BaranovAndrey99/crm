package crm.dao;

import javax.persistence.*;

@Entity
@Table(name="seekers")
public class Seeker extends Human{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String  seekerphone;


    public Seeker() {
    }

    public Seeker(String seekersurname, String seekerfirstname, String seekerfathername) {
        this.surname = surname;
        this.firstname = firstname;
        this.fathername = fathername;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getSeekerphone() {
        return seekerphone;
    }

    public void setSeekerphone(String seekerphone) {
        this.seekerphone = seekerphone;
    }
}
