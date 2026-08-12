package br.com.furb.web2.Controllers;

import br.com.furb.web2.Entities.TipoEquipamento;
import br.com.furb.web2.Services.TipoEquipamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/RestApiFurb/tipos")
public class TipoEquipamentoController {

    private final TipoEquipamentoService service;

    public TipoEquipamentoController(TipoEquipamentoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<TipoEquipamento>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoEquipamento> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<TipoEquipamento> criar(
            @RequestBody TipoEquipamento tipo) {

        return ResponseEntity
                .status(201)
                .body(service.salvar(tipo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoEquipamento> atualizar(
            @PathVariable Long id,
            @RequestBody TipoEquipamento tipo) {

        return ResponseEntity.ok(service.atualizar(id, tipo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {

        service.deletar(id);

        return ResponseEntity.noContent().build();
    }
}
