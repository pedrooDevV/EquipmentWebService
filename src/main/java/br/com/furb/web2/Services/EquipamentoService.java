package br.com.furb.web2.Services;

import br.com.furb.web2.Entities.Equipamento;
import br.com.furb.web2.Entities.TipoEquipamento;
import br.com.furb.web2.Repositories.EquipamentoRepository;
import br.com.furb.web2.Repositories.TipoEquipamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipamentoService {

    private final EquipamentoRepository equipamentoRepository;
    private final TipoEquipamentoRepository tipoRepository;

    public EquipamentoService(
            EquipamentoRepository equipamentoRepository,
            TipoEquipamentoRepository tipoRepository) {

        this.equipamentoRepository = equipamentoRepository;
        this.tipoRepository = tipoRepository;
    }

    public List<Equipamento> listar() {
        return equipamentoRepository.findAll();
    }

    public Equipamento buscarPorId(Long id) {
        return equipamentoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Equipamento não encontrado"));
    }

    public Equipamento criar(Equipamento equipamento) {

        Long tipoId = equipamento.getTipo().getId();

        TipoEquipamento tipo = tipoRepository.findById(tipoId)
                .orElseThrow(() ->
                        new RuntimeException("Tipo de equipamento não encontrado"));

        equipamento.setTipo(tipo);

        return equipamentoRepository.save(equipamento);
    }

    public Equipamento atualizar(Long id, Equipamento dados) {

        Equipamento equipamento = buscarPorId(id);

        if (dados.getNome() != null) {
            equipamento.setNome(dados.getNome());
        }

        if (dados.getTipo() != null &&
                dados.getTipo().getId() != null) {

            TipoEquipamento tipo = tipoRepository
                    .findById(dados.getTipo().getId())
                    .orElseThrow(() ->
                            new RuntimeException(
                                    "Tipo de equipamento não encontrado"));

            equipamento.setTipo(tipo);
        }

        return equipamentoRepository.save(equipamento);
    }

    public void deletar(Long id) {

        Equipamento equipamento = buscarPorId(id);

        equipamentoRepository.delete(equipamento);
    }
}