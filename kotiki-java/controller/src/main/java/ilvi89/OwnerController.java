package ilvi89;

import ilvi89.dao.CatDao;
import ilvi89.dao.OwnerDao;
import ilvi89.model.Owner;
import org.springframework.web.bind.annotation.*;

@RestController
public class OwnerController {
    // Todo: return dto from rest!!!! or json
    // Todo: service initialisation configure!!!
    private static final CatService catService = new CatService(new CatDao());
    private static final OwnerService ownerService = new OwnerService(new OwnerDao());

    @GetMapping("/")
    public String getAll() {
        return "Hello! \n" + ownerService.findAll();
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable long id) {
        return "Owner page:\n" + ownerService.findById(id);
    }

    @PostMapping("/")
    public String create(@RequestParam String name) {
        var owner = new Owner(name);

        ownerService.create(owner);

        return owner.toString();
    }
}
