package useradminLogin;

import java.time.LocalDate;
import java.util.UUID;


import Lombok.Data;
import Lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.sql.Date;

@Data
@NoArgsConstructor
@Entity
//@Table(name = "users");
public class User{
    /*
    @ID
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private UUID id;


     */
    //@Column(name = "user_id")
    private String userID;
    //@Column(name = "user_name");
    private String name;
    //@Column(name = "password")
    private String password;

    private Date admittedDate = Date.valueOf(LocalDate.now());

}