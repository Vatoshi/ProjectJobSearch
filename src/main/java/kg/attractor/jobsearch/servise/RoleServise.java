package kg.attractor.jobsearch.servise;

import kg.attractor.jobsearch.models.Role;
import kg.attractor.jobsearch.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServise {
    private final RoleRepository roleRepository;

    public Role findById(Long id){
        return roleRepository.findById(id).orElse(null);
    }
}
