package com.candidato.agendafinanceira.repositories;

import com.candidato.agendafinanceira.entities.Agendamento;
import org.springframework.data.repository.CrudRepository;

public interface AgendamentoRepository extends CrudRepository<Agendamento, Long> {

    Agendamento findById(long id);
}
