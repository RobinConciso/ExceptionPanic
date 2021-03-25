package rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import services.NameService;

@RestController("api/name")
public class NameResource {

    private final NameService nameService;

    @Autowired
    public NameResource(NameService nameService) {
        this.nameService = nameService;
    }

    @GetMapping
    public String getNameById(@RequestParam Long id) {
        return nameService.getNameById(id);
    }

    @GetMapping
    public String readNameFromFile(@RequestParam String filename) {
        try {
            return nameService.getNameFromFile(filename, false);
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR";
        }
    }

    @PutMapping
    public void addName(@RequestParam String name) {
        nameService.addName(name);
    }
}
