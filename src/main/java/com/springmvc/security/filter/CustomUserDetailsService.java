package com.springmvc.security.filter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.UserDetailsManagerConfigurer.UserDetailsBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springmvc.dao.UserDao;
import com.springmvc.model.UserControl;


@Service
public class CustomUserDetailsService implements UserDetailsService {

	protected static Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

	@Autowired
	public UserDao userDao;
	
	
	public UserDetails loadUserByUsername(String username)	
			throws UsernameNotFoundException {
		try{
			logger.info("username-1-->"+username);
		
            UserControl dbUser = userDao.findBycorreo(username);
            if(dbUser == null) {
	        	logger.error("Error en el Login: no existe el usuario '" + dbUser.getCorreo() + "' en el sistema!");
	        	throw new UsernameNotFoundException("Username: " + dbUser.getCorreo() + " no existe en el sistema!");
	        }
            logger.info("dbUser-1-->"+dbUser.getPassword());
            logger.info("role-1-->"+dbUser.getId_tipo_usuario());
            boolean enabled = true;
            boolean accountNonExpired = true;
            boolean credentialsNonExpired = true;
            boolean accountNonLocked = true;

            return new User(
            		dbUser.getCorreo(), 
            		dbUser.getPassword(),
                    enabled,
                    accountNonExpired,
                    credentialsNonExpired,
                    accountNonLocked,
                    getAuthorities(dbUser.getId_tipo_usuario()) );
           

    }catch(Exception e){
        e.printStackTrace();
        return null;
    }
    //return null;
			
	    
	  
		
	}


	private Collection<? extends SimpleGrantedAuthority> getAuthorities(Integer access)  {
		List<SimpleGrantedAuthority> authList = new ArrayList<SimpleGrantedAuthority>(2);
		
		// All users are granted with ROLE_USER access
		// Therefore this user gets a ROLE_USER by default
		
		
		// Check if this user has admin access 
		// We interpret Integer(1) as an admin user
		if (access.intValue() == 1) {
			authList.add(new SimpleGrantedAuthority("ROLE_USER"));
			logger.debug("Grant ROLE_USER to this user");

	    } else if (access.intValue() == 2) {
	    	authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
	    	logger.debug("Grant ROLE_ADMIN to this user");
	    } else if (access.intValue() == 3) {
	    	authList.add(new SimpleGrantedAuthority("ROLE_MULTI"));
	    	logger.debug("Grant ROLE_MULTI to this user");
	    }
		// Return list of granted authorities
		return authList;
  }

	
}