package br.com.caelum.clines.api.users;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.repository.Repository;

import br.com.caelum.clines.shared.domain.Aircraft;
import br.com.caelum.clines.shared.domain.User;

public interface UserRepository extends Repository<User, Long> {

	Optional<User> findByEmail(String code);
	
	Collection<User> findAll();
	
	void save(User user);

}
