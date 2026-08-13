package br.com.furb.web2.Security;

import br.com.furb.web2.Repositories.UserRepository;
import br.com.furb.web2.Services.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    TokenService tokenService;
    @Autowired
    UserRepository userRepository;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        var token = recuperaToken(request);

        System.out.println("TOKEN: " + token);

        if (token != null) {

            var login = tokenService.validaToken(token);

            System.out.println("LOGIN DO TOKEN: " + login);

            UserDetails userDetails = userRepository.findByLogin(login);

            System.out.println("USUARIO: " + userDetails);
            System.out.println("AUTHORITIES: " + userDetails.getAuthorities());

            var authentication = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities()
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            System.out.println(
                    "AUTHENTICATED: " +
                            SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
            );
        }

        filterChain.doFilter(request, response);
    }

    private String recuperaToken(HttpServletRequest httpServletRequest) {
        var autoHeader = httpServletRequest.getHeader("Authorization");

        if (autoHeader == null || !autoHeader.startsWith("Bearer ")) {
            return null;
        }

        return autoHeader.replace("Bearer ", "");
    }
}
