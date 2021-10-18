package useradminLogin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@CondtionalOnProperty(name = "datasource", havingValue = "db")
public class findUser implements findUserUI{
    @Autowired
    private UserRepository repository;

    @Override
    public List<User> findAll(){
        return repository.findAll();
    }

    @Override
    public User update(User user){
        repository.save(user);
        return user;
    }
    
    @Override
    public User findByID(String id){
        return repository.findByID(UUID.fromString(id)).get();
    }

    public User findByUserID(String userID){
        if (repository.findByUserID(userID).size() == 0) return new User();
        else return repository.findByUserID(userID).get(0);
    }

    public List<User> findAllByUserID(String userid){
        if (repository.findByUserID(userid) == null) return null;
        else return repository.findByUserID(userid);
    }

    @Override
    public void deleteByID(String id){
        repository.deleteByID(UUID.fromString(id));
    }
}
