package services;

import entities.Person;

public interface NameService {

    String getNameById(Long id);

    String getNameFromFile(String filename, boolean eo) throws Exception;

    void addName(String name);

    void savePerson(Person person);
}
