package br.com.furb.web2.Controllers;

import br.com.furb.web2.Entities.Equipamento;
import br.com.furb.web2.Services.EquipamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/RestApiFurb/equipamentos")
@Tag(
        name = "Equipamentos",
        description = "Gerenciamento dos equipamentos"
)
@SecurityRequirement(name = "bearerAuth")
public class EquipamentoController {

    private final EquipamentoService service;

    public EquipamentoController(EquipamentoService service) {
        this.service = service;
    }

    @Operation(
            summary = "Listar equipamentos",
            description = "Retorna todos os equipamentos cadastrados"
    )
    @GetMapping
    public ResponseEntity<List<Equipamento>> listar() {

        return ResponseEntity.ok(service.listar());
    }

    @Operation(
            summary = "Buscar equipamento por ID",
            description = "Busca um equipamento específico pelo seu ID"
    )
    @GetMapping("/{id}")
    public ResponseEntity<Equipamento> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @Operation(
            summary = "Criar equipamento",
            description = "Cadastra um novo equipamento. Acesso exclusivo para ADMIN."
    )
    @PostMapping
    public ResponseEntity<Equipamento> criar(
            @Valid @RequestBody Equipamento equipamento) {

        return ResponseEntity
                .status(201)
                .body(service.criar(equipamento));
    }

    @Operation(
            summary = "Atualizar equipamento",
            description = "Atualiza um equipamento existente. Acesso exclusivo para ADMIN."
    )
    @PutMapping("/{id}")
    public ResponseEntity<Equipamento> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody Equipamento equipamento) {

        return ResponseEntity.ok(
                service.atualizar(id, equipamento)
        );
    }

    @Operation(
            summary = "Excluir equipamento",
            description = "Remove um equipamento. Acesso exclusivo para ADMIN."
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @PathVariable Long id) {

        service.deletar(id);

        return ResponseEntity.noContent().build();
    }
}