package services;

import entities.Person;
import exceptions.IdMissingException;
import exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.NameRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
public class NameServiceImpl implements NameService {

    private final NameRepository nameRepository;
    private final PersonService transactionalPersonService;

    @Autowired
    public NameServiceImpl(NameRepository nameRepository, PersonService personService) {
        this.nameRepository = nameRepository;
        this.transactionalPersonService = personService;
    }

    @Override
    public Optional<String> getNameById(Long id) {
        return nameRepository.findById(id);
    }

    @Override
    public String getNameFromFile(String filename, boolean eo) throws Exception {
        if (eo) {
            try {
                return getNameFromFileExceptionOverload(filename);
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            }
        } else {
            return getNameFromFileCatch(filename);
        }
    }

    private String getNameFromFileExceptionOverload(String filename) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/" + filename));
        return reader.readLine();
    }

    private String getNameFromFileCatch(String filename) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/" + filename));
            return reader.readLine();
        } catch (IOException e) {
            return "NO-FILE";
        }
    }

    @Override
    public void addName(String name) {
        try {
            if (name == null) {
                throw new ServiceException("name is null");
            }
            nameRepository.saveOne(name);

        } catch (Exception e) {
            throw new ServiceException("ERROR");
        }
    }

    @Override
    public void savePerson(Person person) {
        try {
            transactionalPersonService.saveOrUpdatePerson(person);
        } catch (IdMissingException ime) {
            person.setId(UUID.randomUUID());
            addName(person.getName());
            transactionalPersonService.saveOrUpdatePerson(person);
        }
    }

}
