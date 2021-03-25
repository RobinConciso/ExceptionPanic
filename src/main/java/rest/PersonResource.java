package rest;

import entities.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import services.NameService;

@RestController("api/person")
@Transactional
public class PersonResource {

    private final NameService nameService;

    @Autowired
    public PersonResource(NameService nameService) {
        this.nameService = nameService;
    }

    @PutMapping
    public void addPerson(@RequestParam String name) {
        Person person = new Person(null, name);
        nameService.savePerson(person);
    }

    @PostMapping
    public void updatePerson(@RequestParam Person person) {
        nameService.savePerson(person);
    }
}
