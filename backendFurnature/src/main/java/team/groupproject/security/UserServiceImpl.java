package team.groupproject.security;

import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import team.groupproject.dto.MyUserDto;
import team.groupproject.entity.Myuser;
import team.groupproject.entity.Role;
import team.groupproject.filter.JwtFilter;
import team.groupproject.repository.RoleRepo;
import team.groupproject.repository.UserRepo;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passEncoder;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private JwtFilter jwtFilter;

    @Override
    public void save(Myuser myuser) {
        // get the string password
        String plainPass = myuser.getPassword();
        // encode it
        String hashedPass = passEncoder.encode(plainPass);
        // put the encoded passwrod back in the user
        myuser.setPassword(hashedPass);
        userRepo.save(myuser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Myuser myuser = findByUsername(username);

        if (myuser == null) {
            throw new UsernameNotFoundException("Invalid Username");
        } else {
            // User in an implementing class of UserDetails. So I can return this object.
            // authorities- must form method to convert List<Role> to List<GrantedAuthority>
            List<GrantedAuthority> authorities = convertToGrantedAuthority(myuser.getRoles());
            User springSecUser = new User(myuser.getUsername(), myuser.getPassword(), authorities);

            return springSecUser;
        }
    }

    private List<GrantedAuthority> convertToGrantedAuthority(List<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList();
        for (Role r : roles) {
            // convert r to granted authority
            GrantedAuthority auth = new SimpleGrantedAuthority(r.getRoleName());
            // add it to the list of granted authorities
            authorities.add(auth);
        }
        return authorities;
    }

    @Override
    public Myuser findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public Myuser findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userRepo.existsByEmail(username);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepo.existsByEmail(email);
    }

    @Override
    public void createUser(Myuser user, boolean isAdmin) {
        Role userRole = roleRepo.findByRoleName("customer");

        List<Role> roles = new ArrayList<>();
        if (isAdmin) {
            Role adminRole = roleRepo.findByRoleName("admin");
            roles.add(adminRole);
        }
        roles.add(userRole);
        user.setRoles(roles);
        save(user);
    }

    @Override
    public boolean existsById(Integer id) {
        return userRepo.existsById(id);
    }

    @Override
    public Myuser findById(int id) {
        return userRepo.findById(id);
    }

    @Override
    public Myuser findByResetPasswordToken(String resetPasswordToken) {
        return userRepo.findByResetPasswordToken(resetPasswordToken);
    }

    @Override
    public void updateResetPasswordToken(String token, Myuser myuserFromDB) {
        myuserFromDB.setResetPasswordToken(token);
        System.out.println(myuserFromDB.getUsername());
        userRepo.save(myuserFromDB);

    }

    @Override
    public boolean existsByResetPasswordToken(String token) {
        return userRepo.existsByResetPasswordToken(token);
    }

    @Override
    public List<MyUserDto> getAdminUsers() {
        return userRepo.getAdminUsers();
    }

}
