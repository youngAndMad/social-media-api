package danekerscode.socialmediaapi.mapper;

import danekerscode.socialmediaapi.model.Address;
import danekerscode.socialmediaapi.payload.request.AddressDTO;
import org.mapstruct.Mapper;

@Mapper
public interface AddressMapper {
    Address toAddress(AddressDTO addressDTO);

    AddressDTO toRequest(Address address);
}
