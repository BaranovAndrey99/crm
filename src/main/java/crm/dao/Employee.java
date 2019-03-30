package crm.dao;

import javax.persistence.*;

@Entity
@Table(name="employees")
public class Employee extends Human{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String  employeeposition;
    private String  employeenickname;

    public Employee() {
    }

    public Employee(String surname, String firstname, String fathername) {
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


    public String getEmployeenickname() {
        return employeenickname;
    }

    public void setEmployeenickname(String employeenickname) {
        this.employeenickname = employeenickname;
    }


    public String getEmployeeposition() {
        return employeeposition;
    }

    public void setEmployeeposition(String employeeposition) {
        this.employeeposition = employeeposition;
    }

}
