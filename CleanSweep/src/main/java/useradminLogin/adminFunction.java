package useradminLogin;

import org.springframework.beans.facotry.annotation.Autowired;

import java.util.List;
import java.util.UUID;

public class adminFunction implements AdminUI{
    @Autowired
    private AdminRepository repository;

    @Override
    public List<Admin> findAll(){
        return repository.findAll();
    }

    @Override 
    public Admin update(Admin admin){
        repository.save(admin);
        return admin;

    }

    @Override
    public Admin findByID(String adminID){
        return repository.findByID(UUID.fromString(adminID)).get();
    }

    @Override 
    public Admin findByAdminID(String adminID){
        if (repository.findByAdminID(adminID).size() == 0) return new Admin();
        else return repository.findByAdminID(adminID).get(0);
    }
    @Override
    public void deleteByID(String adminID){
        repository.deleteByID(UUID.fromString(adminID));
    }
    
}
