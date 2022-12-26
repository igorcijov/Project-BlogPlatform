package spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.controller.vo.PersonVO;
import spring.domain.Person;
import spring.service.PersonService;
import spring.util.StringUtil;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class RestSimpleController implements SimpleRestApi {

    @Autowired
    PersonService personService;

    @Override
    public ResponseEntity<PersonVO> add(@Valid PersonVO body) {
        String firstName = body.getFirstName();
        String lastName = body.getLastName();
        int age = body.getAge();

        Person newPerson = new Person(firstName, lastName, age);
            personService.add(newPerson);
        return ResponseEntity.ok(PersonVO.valueOf(newPerson));
    }

    @Override
    public ResponseEntity<Void> delete(Integer id) {
        if (personService.remove(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<PersonVO> get(Integer id) {
        Person person = personService.findById(id);
        if (person != null) {
            return ResponseEntity.ok(PersonVO.valueOf(person));
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<List<PersonVO>> getAll() {
        List<PersonVO> personsVO = personService.findAll().stream()
                .map(e -> PersonVO.valueOf(e))
                .collect(Collectors.toList());
        return ResponseEntity.ok(personsVO);
    }

    @Override
    public ResponseEntity<PersonVO> update(Integer id, PersonVO body) {
        Person person = personService.update(id, body);
        return ResponseEntity.ok(PersonVO.valueOf(person));
    }
}