package kotiki.service;

import kotiki.entity.Owner;
import kotiki.repository.OwnerRepo;
import kotiki.security.entity.User;
import kotiki.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class OwnerService {
    @Autowired
    private OwnerRepo ownerRepo;
    @Autowired
    private UserRepository userRepository;

    public Owner create(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException(userId.toString()));

        Owner owner = new Owner(user.getUsername());
        return ownerRepo.save(owner);
    }
}
