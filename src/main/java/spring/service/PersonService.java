package spring.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.controller.vo.PersonVO;
import spring.domain.Person;
import spring.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    static final Logger logger = LoggerFactory.getLogger(PersonService.class);

    @Autowired
    private PersonRepository personRepository;

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public void add(Person person) {
        personRepository.save(person);
        logger.info("Added person {}", person);
    }

    public Person findById(int idx) {
        Optional<Person> result = personRepository.findById(idx);
        return result.isPresent() ? result.get() : null;
    }

    public List<Person> findByFirstName(String firstName) {
        return personRepository.findByFirstName(firstName);
    }

    public Person update(int idx, PersonVO personVO) {
        Person old = personRepository.findById(idx).get();
        old.setFirstName(personVO.getFirstName());
        old.setLastName(personVO.getLastName());
        old.setAge(personVO.getAge());
        logger.info("Updated person {}", old);
        return personRepository.save(old);
    }

    public boolean remove(int idx) {
        Person person = findById(idx);
        if (person != null) {
            personRepository.delete(person);
            logger.info("Removed person {}", person);
            return true;
        }
        return false;
    }
}