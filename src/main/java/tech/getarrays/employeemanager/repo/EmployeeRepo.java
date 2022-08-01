package tech.getarrays.employeemanager.repo;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import tech.getarrays.employeemanager.model.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Long>{ //<Clase, Tipo de la PK>

    public void deleteEmployeeById(Long id);   //Spring entiende a través del nombre del método, que tiene que ejecutar una query para eliminar
                                        //un registro de la BD por Id. Esto se llama un Query Method por la convención de nombre.
    //Spring va a crear un query que va a eliminar el employee cuyo id sea igual al pasado como parámetro del query method.

    public Optional<Employee> findEmployeeById(Long id);  //Como en el método anterior, Spring es capaz de entender por el nombre del método que tiene
                                                //que ejecutar una query con un select, buscando el employee cuyo id coincida con el id
                                                //pasado como parámetro del método.
        
}
