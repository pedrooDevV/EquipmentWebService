package br.com.furb.web2.Repositories;

import br.com.furb.web2.Entities.Equipamento;
import br.com.furb.web2.Entities.TipoEquipamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipamentoRepository extends JpaRepository<Equipamento, Long> {
}
