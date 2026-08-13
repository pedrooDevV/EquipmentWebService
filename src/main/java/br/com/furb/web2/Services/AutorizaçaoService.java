package br.com.furb.web2.Services;

import br.com.furb.web2.Exceptions.UsuarioNaoEncontradoException;
import br.com.furb.web2.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutorizaçaoService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        UserDetails usuario = userRepository.findByLogin(username);

        if (usuario == null) {
            throw new UsernameNotFoundException(
                    "Usuário não encontrado"
            );
        }

        return usuario;
    }
}