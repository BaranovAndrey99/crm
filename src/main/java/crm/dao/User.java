package crm.dao;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="users")
public class User extends Human{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String usernickname;
    private String userpassword;
    private boolean useractive;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsernickname() {
        return usernickname;
    }

    public void setUsernickname(String usernickname) {
        this.usernickname = usernickname;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public boolean isUseractive() {
        return useractive;
    }

    public void setUseractive(boolean useractive) {
        this.useractive = useractive;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
