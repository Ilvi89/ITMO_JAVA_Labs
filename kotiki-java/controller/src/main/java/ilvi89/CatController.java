package ilvi89;

import ilvi89.dao.CatDao;
import ilvi89.dao.OwnerDao;
import ilvi89.model.Cat;
import org.springframework.web.bind.annotation.*;

@RestController
public class CatController {

    // Todo: return dto from rest!!!! or json
    // Todo: service initialisation configure!!!
    private static final CatService catService = new CatService(new CatDao());
    private static final OwnerService ownerService = new OwnerService(new OwnerDao());

    @GetMapping("/")
    public String getAll() {
        return "Hello! \n" + catService.findAll();
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable long id) {
        return "Cat page:\n" + catService.findById(id);
    }

    @PostMapping("/")
    public String create(@RequestParam String name, @RequestParam Long ownerId) {
        if (ownerService.findById(ownerId) == null) {
            // Todo: return bed status and response
            return "err";
        }

        var cat = catService.create(
                new Cat(name, ownerId)
        );

        return cat.toString();
    }

}