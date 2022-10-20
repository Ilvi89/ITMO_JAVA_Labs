package kotiki.controller;


import kotiki.entity.Cat;
import kotiki.entity.Owner;
import kotiki.repository.CatRepo;
import kotiki.repository.OwnerRepo;
import kotiki.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/owners")
public class OwnerController {
    @Autowired
    private OwnerRepo ownerRepo;

    @GetMapping
    public List<Owner> getAll() {
        return ownerRepo.findAll();
    }

    @GetMapping("{id}")
    public Owner getByid(@PathVariable Long id) {
        return ownerRepo.findById(id).get();
    }


    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public Owner create(@RequestParam String name) {
        Owner cat = new Owner(name);
        return ownerRepo.save(cat);
    }
}
