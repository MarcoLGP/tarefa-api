package com.uninter.trabalho.tarefas.trabalho_api_tarefas.controller;

import com.uninter.trabalho.tarefas.trabalho_api_tarefas.model.Tarefa;
import com.uninter.trabalho.tarefas.trabalho_api_tarefas.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarefa")
public class TarefaController {

    @Autowired
    private TarefaRepository tarefaRepository;

    @GetMapping
    public List<Tarefa> getAllTarefas() {
        return tarefaRepository.findAll();
    }

    @PostMapping
    public Tarefa postTarefa(@RequestBody Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> putTarefa(@PathVariable("id") Long id, @RequestBody Tarefa tarefa) {
        var tarefaExistente = tarefaRepository.findById(id);

        if (tarefaExistente.isEmpty())
            return ResponseEntity.notFound().build();

        Tarefa novaTarefa = new Tarefa();
        novaTarefa.setId(tarefa.getId());
        novaTarefa.setNome(tarefa.getNome());
        novaTarefa.setDataEntrega(tarefa.getDataEntrega());
        novaTarefa.setResponsavel(tarefa.getResponsavel());
        tarefaRepository.save(novaTarefa);

        return ResponseEntity.ok(novaTarefa);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTarefa(@PathVariable("id") Long id) {
        if (!tarefaRepository.existsById(id))
            return ResponseEntity.notFound().build();

        tarefaRepository.deleteById(id);

        return ResponseEntity.status(202).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> getByIdTarefa(@PathVariable("id") Long id) {
        var tarefa = tarefaRepository.findById(id).orElse(null);
        if (tarefa == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(tarefa);
        }
    }
}
