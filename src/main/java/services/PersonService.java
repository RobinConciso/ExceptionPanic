package services;

import entities.Person;
import exceptions.IdMissingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repositories.PersonRepository;


@Service
@Transactional
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public void saveOrUpdatePerson(Person person) {
        if (person.getId() == null) {
            throw new IdMissingException("person id was null");
        }

        personRepository.save(person);
    }
}
