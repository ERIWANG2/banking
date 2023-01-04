package rdc.ericwangi.banking.services;

import rdc.ericwangi.banking.dto.AuthenticationRequest;
import rdc.ericwangi.banking.dto.AuthenticationResponse;
import rdc.ericwangi.banking.dto.UserDto;

public interface UserService extends AbstractService<UserDto>{

    Integer validateAccount(Integer id);

    Integer invalidateAccount(Integer id);

    AuthenticationResponse register(UserDto userDto);

    AuthenticationResponse authenticate(AuthenticationRequest request);
}
