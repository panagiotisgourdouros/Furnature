package team.groupproject.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import team.groupproject.dto.MyUserDto;
import team.groupproject.entity.Myuser;

@Repository
public interface UserRepo extends JpaRepository<Myuser, Integer> {

    public Myuser findByUsername(String username);

    public Myuser findById(int id);

    // public Myuser findByRoleId(Integer id);
    public Myuser findByEmail(String email);

    public Boolean existsByUsername(String username);

    public void deleteByUsername(String username);

    public Boolean existsByEmail(String email);

    public boolean existsById(Integer id);

    public Myuser findByResetPasswordToken(String resetPasswordToken);

    public boolean existsByResetPasswordToken(String token);

    public Boolean existsByEmailAndUsernameNot(String email, String username);

    @Query(value = "select myusers.id, myusers.title, myusers.username, myusers.first_name, myusers.last_name, myusers.email, myusers.phone_number from myusers"
            + " INNER JOIN myusers_roles"
            + " ON myusers.id = myusers_roles.myuser_id"
            + " where myusers_roles.role_id=22" /* + " group by products.id" */,
            nativeQuery = true)
    public List<MyUserDto> getAdminUsers();

}
