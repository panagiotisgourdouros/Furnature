package team.groupproject.converters;

import team.groupproject.dto.MyUserDto;
import team.groupproject.entity.Myuser;

public class MyUserConverter {

    public static MyUserDto convertMyUserToMyUserDto(Myuser myuser) {

        MyUserDto myUserDto = new MyUserDto();

        myUserDto.setId(myuser.getId());

        myUserDto.setEmail(myuser.getEmail());

        myUserDto.setTitle(myuser.getTitle());

        myUserDto.setFirstName(myuser.getFirstName());

        myUserDto.setLastName(myuser.getLastName());

        myUserDto.setUsername(myuser.getUsername());

        myUserDto.setPhoneNumber(myuser.getPhoneNumber());

        return myUserDto;

    }

}
