package crm.controller;

import crm.dao.Employee;
import crm.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/employee")
    public String employeePage(){
        return "employee";
    }

    /*
     * Handling of form for writing list of all employees.
     */
    @RequestMapping(params = "listemployee", method = RequestMethod.POST)
    public void listEmployee(@ModelAttribute("listemployeeform") Employee employee, Map<String, Object> model){
        Iterable<Employee> allEmployeesFromDb = employeeRepository.findAll();
        if(((List<Employee>) allEmployeesFromDb).isEmpty()){
            model.put("listnoresult", "Работники не найдены");
        } else {
            model.put("listresult", allEmployeesFromDb);
        }
    }

    /*
     * Handling of form for addition of new emloyee.
     */
    @RequestMapping(params = "addemployee", method = RequestMethod.POST)
    public void addEmployee(@ModelAttribute("addemployeeform") Employee employee, Map<String, Object> model){
        Employee employeeFromDb = employeeRepository.findEmployeeByEmployeenickname(employee.getEmployeenickname());
        if(employeeFromDb != null){
            model.put("adderror", "Такой работник уже существует!");
        } else {
            employeeRepository.save(employee);
        }
    }

    /*
     * Handling of form for search of emloyee.
     */
    @RequestMapping(params = "search", method = RequestMethod.POST)
    public void searchEmployee(@ModelAttribute("searchemployeeform") Employee employee, @RequestParam String surname, @RequestParam String firstname, @RequestParam String fathername, Map<String, Object> model){
        Iterable<Employee> employeeFromDb = employeeRepository.findEmployeeBySurnameAndFirstnameAndFathername(surname, firstname, fathername);
        if(((List<Employee>) employeeFromDb).isEmpty()){
            model.put("searchnoresult", "Работники не найдены");
        } else {
            model.put("searchresult", employeeFromDb);
        }
    }

    /*
     * Handling of form for deleting of emloyee.
     */
    @RequestMapping(params = "deleteemployee", method = RequestMethod.POST)
    public void deleteEmployee(@ModelAttribute("deleteemployeeform") Employee employee, Map<String, Object> model){
        Employee employeeFromDb = employeeRepository.findEmployeeById(employee.getId());
        if(employeeFromDb == null){
            model.put("deleteerror", "Работник не существует!");
        } else {
            employeeRepository.delete(employee);
        }
    }
}
