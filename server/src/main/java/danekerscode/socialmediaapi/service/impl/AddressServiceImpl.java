package danekerscode.socialmediaapi.service.impl;

import danekerscode.socialmediaapi.model.Address;
import danekerscode.socialmediaapi.repository.AddressRepository;
import danekerscode.socialmediaapi.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Override
    public void deleteByID(Integer id) {
        addressRepository.deleteById(id);
    }

    @Override
    public Optional<Address> getById(Integer id) {
        return addressRepository.findById(id);
    }

    @Override
    public List<Address> getAll() {
        return addressRepository.findAll();
    }
}
