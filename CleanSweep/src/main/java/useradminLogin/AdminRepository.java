package useradminLogin;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface AdminRepository extends JpaRepository<Admin, UUID>{
    List<Admin> findByAdminID(String adminID);
}
