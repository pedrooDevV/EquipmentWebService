package br.com.furb.web2.Controllers;

import br.com.furb.web2.Entities.Equipamento;
import br.com.furb.web2.Services.EquipamentoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/RestApiFurb/equipamentos")
public class EquipamentoController {

    private final EquipamentoService service;

    public EquipamentoController(EquipamentoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Equipamento>> listar() {

        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipamento> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Equipamento> criar(
            @Valid @RequestBody Equipamento equipamento) {

        return ResponseEntity
                .status(201)
                .body(service.criar(equipamento));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Equipamento> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody Equipamento equipamento) {

        return ResponseEntity.ok(
                service.atualizar(id, equipamento)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @PathVariable Long id) {

        service.deletar(id);

        return ResponseEntity.noContent().build();
    }
}