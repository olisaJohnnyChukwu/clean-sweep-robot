package useradminLogin;

import java.util.List;

public class AdminUI {
    public List<Admin> findAll();

    public Admin update(Admin admin);

    public Admin findByID(String adminID);

    public Admin findByAdminID(String adminID);

    public void deleteByID(String adminID);
}
