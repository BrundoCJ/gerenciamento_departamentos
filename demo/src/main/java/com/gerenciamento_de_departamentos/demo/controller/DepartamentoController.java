package com.gerenciamento_de_departamentos.demo.controller;

import com.gerenciamento_de_departamentos.demo.entity.Departamento;
import com.gerenciamento_de_departamentos.demo.service.DepartamentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/departamentos")
public class DepartamentoController {

    private final DepartamentoService departamentoService;

    public DepartamentoController(DepartamentoService departamentoService) {
        this.departamentoService = departamentoService;
    }

    @PostMapping
    public ResponseEntity<Departamento> criarDepartamento(@RequestBody Departamento departamento) {
        Departamento departamentoSalvo = departamentoService.salvar(departamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(departamentoSalvo);
    }

    @GetMapping
    public ResponseEntity<List<Departamento>> listarDepartamentos() {
        return ResponseEntity.ok(departamentoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Departamento> buscarDepartamentoPorId(@PathVariable Long id) {
        Optional<Departamento> departamento = departamentoService.buscarPorId(id);
        return departamento.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDepartamento(@PathVariable Long id) {
        boolean deletado = departamentoService.deletar(id);
        if (deletado) return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }
}