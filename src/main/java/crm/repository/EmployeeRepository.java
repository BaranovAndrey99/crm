package crm.repository;

import crm.dao.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {


    List<Employee> findAll();
    List<Employee> findEmployeeBySurnameAndFirstnameAndFathername(String surname, String firstname, String fathername);
    Employee findEmployeeById(long id);
    Employee findEmployeeByEmployeenickname(String employeenickname);
}
