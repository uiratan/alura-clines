package br.com.caelum.clines.api.users;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.caelum.clines.shared.exceptions.ResourceAlreadyExistsException;
import br.com.caelum.clines.shared.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
	
	private final UserRepository userRepository;
    private final UserViewMapper viewMapper;
    private final UserFormMapper formMapper;
        
	public List<UserView> findAll() {
        return userRepository.findAll().stream().map(viewMapper::map).collect(toList());		
    }

	public Long createUserBy(UserForm form) {
		userRepository.findByEmail(form.getEmail()).ifPresent(user -> {
			throw new ResourceAlreadyExistsException("User already exists");
		});
		
        var user = formMapper.map(form);

        userRepository.save(user);

        return user.getId();
    }

	public UserView showUserBy(Long id) {
		var user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cannot find user"));

        return viewMapper.map(user);

	}

}
