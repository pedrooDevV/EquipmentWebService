package br.com.furb.web2.Repositories;

import br.com.furb.web2.Entities.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<Usuarios, String> {

    UserDetails findByLogin(String login);

}
