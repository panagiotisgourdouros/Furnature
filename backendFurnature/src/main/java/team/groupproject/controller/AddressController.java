package team.groupproject.controller;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.groupproject.entity.Address;
import team.groupproject.entity.Myuser;
import team.groupproject.dto.AddressesDTO;
import team.groupproject.model.MessageResponse;
import team.groupproject.repository.UserRepo;
import team.groupproject.security.UserService;
import team.groupproject.service.AddressService;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

@RestController
@CrossOrigin
@RequestMapping("/api/users")
public class AddressController {

    @Autowired
    AddressService addressService;

    @Autowired
    UserRepo userRepo;

    @Autowired
    UserService userService;

    @GetMapping("/addresses")
    @CrossOrigin
    public ResponseEntity<?> getAddressesOfUser(Authentication authentication) {
        Myuser user = null;
        try {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            user = userService.findByUsername(userDetails.getUsername());
        } catch (Exception e) {
            ResponseEntity.badRequest().body(new MessageResponse("The session has expired"));
        }
        List<Address> addresses = addressService.findByUserId(user);
        List<AddressesDTO> responseAddresses = new ArrayList<>();
        System.out.println(addresses.toString());
        if (user != null && addresses.isEmpty()) {
            return ResponseEntity.ok(new MessageResponse("No addresses registered yet. Please register a shipping Address in your Adress Book"));
        } else {
            for (Address a : addresses) {
                responseAddresses.add(new AddressesDTO(a.getBilling(), a.getCity(), a.getCountry(), a.getHouseNumber(),
                        a.getId(), a.getPostalCode(), a.getShipping(), a.getStreetName()));
            }
            return new ResponseEntity<>(responseAddresses, HttpStatus.OK);
        }
    }

    @PostMapping("/createaddress")
    @CrossOrigin
    public ResponseEntity<?> createAddress(@Valid @RequestBody Address newAddress, Authentication authentication) {
        Myuser user = null;

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        user = userService.findByUsername(userDetails.getUsername());

        Address address = new Address(newAddress.getBilling(), newAddress.getCity(), newAddress.getCountry(),
                newAddress.getHouseNumber(), user, newAddress.getPostalCode(), newAddress.getShipping(),
                newAddress.getStreetName());
        addressService.save(address);
        return ResponseEntity.ok(new MessageResponse("Address added sucessfully!"));
    }

    @DeleteMapping("/deleteaddress/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable int id, Authentication authentication) {

        Address addressToDelete = addressService.findById(id);
        if (addressToDelete == null) {
            throw new ResourceNotFoundException("Address does not exist");
        }

        addressService.delete(addressToDelete);
        return ResponseEntity.ok(new MessageResponse("Address was deleted successfully"));

    }
}
