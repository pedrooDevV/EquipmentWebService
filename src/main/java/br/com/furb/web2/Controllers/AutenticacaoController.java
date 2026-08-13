package br.com.furb.web2.Controllers;

import br.com.furb.web2.DTO.AutenticacaoDTO;
import br.com.furb.web2.DTO.LoginResponseDTO;
import br.com.furb.web2.DTO.RegistroDTO;
import br.com.furb.web2.Entities.Usuario;
import br.com.furb.web2.Repositories.UserRepository;
import br.com.furb.web2.Services.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/RestApiFurb")
@Tag(
        name = "Autenticação",
        description = "Endpoints de autenticação e usuários"
)
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    TokenService tokenService;

    @Operation(
            summary = "Realizar login",
            description = "Autentica o usuário e retorna um token JWT"
    )
    @PostMapping("/login")
    public ResponseEntity login(
            @RequestBody @Valid AutenticacaoDTO autenticacaoDTO) {

        var userPassword = new UsernamePasswordAuthenticationToken(
                autenticacaoDTO.login(),
                autenticacaoDTO.password()
        );

        var autenticacao = authenticationManager.authenticate(userPassword);

        var token = tokenService.gerarToken(
                (Usuario) autenticacao.getPrincipal()
        );

        return ResponseEntity.ok(
                new LoginResponseDTO(token)
        );
    }

    @Operation(
            summary = "Registrar usuário",
            description = "Registra um novo usuário"
    )
    @PostMapping("/registrar")
    public ResponseEntity registra(
            @RequestBody @Valid RegistroDTO registroDTO) {

        if (this.userRepository.findByLogin(registroDTO.login()) != null) {
            return ResponseEntity.badRequest().build();
        }

        String senhaEncriptada = new BCryptPasswordEncoder()
                .encode(registroDTO.password());

        Usuario usuario = new Usuario(
                registroDTO.login(),
                senhaEncriptada,
                registroDTO.role()
        );

        this.userRepository.save(usuario);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }
}