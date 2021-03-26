package services;

import entities.Person;

import java.util.Optional;

public interface NameService {

    Optional<String> getNameById(Long id);

    String getNameFromFile(String filename, boolean eo) throws Exception;

    void addName(String name);

    void savePerson(Person person);
}
