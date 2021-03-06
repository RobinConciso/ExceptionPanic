package repositories;

import exceptions.NamedException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NameRepository extends JpaRepository<String, Long> {

    void saveOne(String name) throws NamedException;
}
