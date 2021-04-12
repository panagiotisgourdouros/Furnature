package team.groupproject.service;

import java.util.List;
import org.springframework.stereotype.Service;
import team.groupproject.entity.Address;
import team.groupproject.entity.Myuser;

@Service
public interface AddressService {

	public Address findById(int id);

	public List<Address> findByUserId(Myuser user);

	public void save(Address address);

	public void delete(Address address);

}
