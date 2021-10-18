package useradminLogin;

import java.util.List;
public interface findUserUI{
    public List<User> findAll();
    public User update(User user);

    public User findByID(String userID);

    public User findByUserID(String userID);

    public List<User> findAllByUserID(String userID);

    public void deleteByID(String userID);


}
