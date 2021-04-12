package team.groupproject.security;

import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import team.groupproject.dto.MyUserDto;
import team.groupproject.entity.Myuser;

@Service
public interface UserService extends UserDetailsService {

    public Myuser findByUsername(String username);

    public Myuser findById(int id);

    public void save(Myuser myuser);

    public Myuser findByEmail(String email);

    public Boolean existsByUsername(String username);

    public Boolean existsByEmail(String email);

    public void createUser(Myuser user, boolean isAdmin);

    public boolean existsById(Integer id);

    public Myuser findByResetPasswordToken(String resetPasswordToken);

    public void updateResetPasswordToken(String token, Myuser myuserFromDB);

    public boolean existsByResetPasswordToken(String token);

    public List<MyUserDto> getAdminUsers();

    // public Myuser findByRoleId(Integer id);
}
