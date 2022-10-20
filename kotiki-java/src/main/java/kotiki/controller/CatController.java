package kotiki.controller;


import kotiki.entity.Cat;
import kotiki.repository.CatRepo;
import kotiki.repository.OwnerRepo;
import kotiki.security.UserDetailsServiceImpl;
import kotiki.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cats")
public class CatController {
    @Autowired
    private CatRepo catRepo;
    @Autowired
    private OwnerRepo ownerRepo;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private JwtUtils jwtUtils;


    @GetMapping
    public List<Cat> getAll() {
        return catRepo.findAll();
    }

    @GetMapping("{id}")
    public Cat getByid(@PathVariable Long id) {
        return catRepo.findById(id).get();
    }

    @PostMapping()
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public Cat create(@RequestParam String name,
                      @RequestHeader(name = "Authorization") String token) {
        Cat cat = new Cat(name);

        String username = jwtUtils.getUserNameFromJwtToken(token.substring(7, token.length()));

        cat.setOwner(ownerRepo.findByName(username).orElseThrow());
        return catRepo.save(cat);
    }

    @PostMapping("admin")
    @PreAuthorize("hasRole('ADMIN')")
    public Cat create(@RequestParam String name, @RequestParam Long userId) {
        Cat cat = new Cat(name);
        cat.setOwner(ownerRepo.findById(userId).orElseThrow(() -> {
            return new UsernameNotFoundException(userId.toString());
        }));
        return catRepo.save(cat);
    }
}