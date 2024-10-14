package by.tms.zenapic29onl.service;

import by.tms.zenapic29onl.entity.Role;
import by.tms.zenapic29onl.exception.NotFoundException;
import by.tms.zenapic29onl.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService implements CrudOperation<Role> {

    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void delete(Role role) {
        roleRepository.delete(role);

    }

    @Override
    public void delete(Long id) {
        roleRepository.deleteById(id);

    }

    @Override
    public Role update(Role role) {
        return roleRepository.save(role);

    }

    @Override
    public Role findById(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Post not Found"));

    }
}
