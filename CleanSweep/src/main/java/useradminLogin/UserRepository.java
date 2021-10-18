package useradminLogin;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID>{
    List<User> findByUserID(String userID);
    
}
