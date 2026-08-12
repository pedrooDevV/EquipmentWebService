package br.com.furb.web2.Services;

import br.com.furb.web2.Entities.TipoEquipamento;
import br.com.furb.web2.Repositories.TipoEquipamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoEquipamentoService {

    private final TipoEquipamentoRepository repository;

    public TipoEquipamentoService(TipoEquipamentoRepository repository) {
        this.repository = repository;
    }

    public List<TipoEquipamento> listar() {
        return repository.findAll();
    }

    public TipoEquipamento buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo de equipamento não encontrado"));
    }

    public TipoEquipamento salvar(TipoEquipamento tipo) {
        return repository.save(tipo);
    }

    public TipoEquipamento atualizar(Long id, TipoEquipamento dados) {

        TipoEquipamento tipo = buscarPorId(id);

        if (dados.getNome() != null) {
            tipo.setNome(dados.getNome());
        }

        return repository.save(tipo);
    }

    public void deletar(Long id) {
        TipoEquipamento tipo = buscarPorId(id);
        repository.delete(tipo);
    }

}