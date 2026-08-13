package br.com.furb.web2.Controllers;

import br.com.furb.web2.Entities.TipoEquipamento;
import br.com.furb.web2.Services.TipoEquipamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/RestApiFurb/tipos")
@Tag(
        name = "Tipos de Equipamento",
        description = "Gerenciamento dos tipos de equipamento"
)
@SecurityRequirement(name = "bearerAuth")
public class TipoEquipamentoController {

    private final TipoEquipamentoService service;

    public TipoEquipamentoController(TipoEquipamentoService service) {
        this.service = service;
    }

    @Operation(
            summary = "Listar tipos",
            description = "Retorna todos os tipos de equipamento cadastrados"
    )
    @GetMapping
    public ResponseEntity<List<TipoEquipamento>> listar() {

        return ResponseEntity.ok(service.listar());
    }

    @Operation(
            summary = "Buscar tipo por ID",
            description = "Busca um tipo de equipamento pelo ID"
    )
    @GetMapping("/{id}")
    public ResponseEntity<TipoEquipamento> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @Operation(
            summary = "Criar tipo",
            description = "Cria um novo tipo de equipamento. Acesso exclusivo para ADMIN."
    )
    @PostMapping
    public ResponseEntity<TipoEquipamento> criar(
            @RequestBody TipoEquipamento tipo) {

        return ResponseEntity
                .status(201)
                .body(service.salvar(tipo));
    }

    @Operation(
            summary = "Atualizar tipo",
            description = "Atualiza um tipo de equipamento. Acesso exclusivo para ADMIN."
    )
    @PutMapping("/{id}")
    public ResponseEntity<TipoEquipamento> atualizar(
            @PathVariable Long id,
            @RequestBody TipoEquipamento tipo) {

        return ResponseEntity.ok(
                service.atualizar(id, tipo)
        );
    }

    @Operation(
            summary = "Excluir tipo",
            description = "Exclui um tipo de equipamento. Acesso exclusivo para ADMIN."
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @PathVariable Long id) {

        service.deletar(id);

        return ResponseEntity.noContent().build();
    }
}