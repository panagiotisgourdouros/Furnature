package team.groupproject.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import team.groupproject.entity.Address;
import team.groupproject.entity.Myuser;
import team.groupproject.repository.AddressRepo;
import team.groupproject.repository.UserRepo;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    AddressRepo addressRepo;

    @Autowired
    UserRepo userRepo;

    @Override
    public Address findById(int id) {
        Address myaddress = null;
        try {
            myaddress = addressRepo.findById(id);

        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("There is not address with id " + id);
        }
        return myaddress;
    }

    @Override
    public List<Address> findByUserId(Myuser user) {
        return addressRepo.findByUserId(user);
    }

    @Override
    public void save(Address address) {
        addressRepo.save(address);
    }

    @Override
    public void delete(Address address) {
        addressRepo.delete(address);
    }

}
