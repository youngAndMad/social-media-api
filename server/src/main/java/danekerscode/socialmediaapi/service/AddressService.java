package danekerscode.socialmediaapi.service;

import danekerscode.socialmediaapi.model.Address;


public interface AddressService {
    void deleteById(Integer id);

    void save(Address address);
}
