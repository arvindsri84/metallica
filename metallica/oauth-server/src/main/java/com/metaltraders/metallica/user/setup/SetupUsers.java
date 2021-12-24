package com.metaltraders.metallica.user.setup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.metaltraders.metallica.user.entity.User;
import com.metaltraders.metallica.user.repository.UserRepository;

@Component
public class SetupUsers implements CommandLineRunner {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public void run(String... args) throws Exception {
		createOrUpdate(new User("asri68",this.passwordEncoder.encode("test"),"Arvind Srivastava",true));
		createOrUpdate(new User("metallica",this.passwordEncoder.encode("metal"),"Metallica Admin",true));
	}
	
	private void createOrUpdate(User user){
		User existingUser = this.repository.findOneByUsername(user.getUsername());
		if(existingUser == null){
			this.repository.save(user);
		}else{
			existingUser.setName(user.getName());
			existingUser.setPassword(user.getPassword());
			existingUser.setEnabled(user.isEnabled());
			this.repository.save(existingUser);
		}
	}

}
