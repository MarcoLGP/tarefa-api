package com.uninter.trabalho.tarefas.trabalho_api_tarefas.repository;

import com.uninter.trabalho.tarefas.trabalho_api_tarefas.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
}
