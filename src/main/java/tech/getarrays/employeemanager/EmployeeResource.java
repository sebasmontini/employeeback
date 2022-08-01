package tech.getarrays.employeemanager;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.getarrays.employeemanager.model.Employee;
import tech.getarrays.employeemanager.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeResource {
    private final EmployeeService employeeService;
    
    public EmployeeResource(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAllEmployees (){           //Método que devuelve una HTTP Response y en el cuerpo habrá una lista o array de empleados.
        List<Employee> employees = employeeService.findAllEmployees();  //En este punto lo que tenemos que hacer es llamar al servicio y el servicio devolverá el listado
        return new ResponseEntity<>(employees, HttpStatus.OK);          //de todos los empleados que se encuntren en la Base de Datos.
    }
    
    @GetMapping("/find/{id}")
    public ResponseEntity<Employee> getEmployeeById (@PathVariable("id") Long id){                 //Método para buscar un employee por su id.
        Employee employee = employeeService.findEmployeeById(id);  
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }
    
    //Método para agregar un employee. Usa método Post ya que realizará un cambio en el Back End.
    @PostMapping("/add")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){     //en addEmployee esperamos el objeto completo employee que va a estar en formato json que vendrá del usuario.
        Employee newEmployee = employeeService.addEmployee(employee);
        return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
    }
    
    //Método para actualizar un employee. Usa el método Put ya que está haciendo una actualización sobre algo que ya exite.
    @PutMapping("/update")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee){     //en addEmployee esperamos el objeto completo employee que va a estar en formato json que vendrá del usuario.
        Employee updateEmployee = employeeService.updateEmployee(employee);
        return new ResponseEntity<>(updateEmployee, HttpStatus.OK);
    }
    
    @Transactional
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") Long id){     //en addEmployee esperamos el objeto completo employee que va a estar en formato json que vendrá del usuario.
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}