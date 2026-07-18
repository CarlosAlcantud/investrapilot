package org.alcantud.springcloud.msvc.budgetservice.msvc_budgetservice.controllers;

import org.alcantud.springcloud.msvc.budgetservice.msvc_budgetservice.entity.Budget;
import org.alcantud.springcloud.msvc.budgetservice.msvc_budgetservice.services.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BudgetController {

    @Autowired
    private BudgetService service;

    @GetMapping
    public ResponseEntity<List<Budget>> listar(){
        return ResponseEntity.ok(service.listar());

    }

    @GetMapping("/{id}")
    public ResponseEntity<Budget> detalle(@PathVariable Long id){
        Optional<Budget> o = service.porId(id);
        if(o.isPresent()){
            return ResponseEntity.ok(o.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/")
    public ResponseEntity<?> crear(@RequestBody Budget budget){

        Budget budgetDb = service.guardar(budget);
        return ResponseEntity.status(HttpStatus.CREATED).body(budgetDb);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@RequestBody Budget budget, @PathVariable Long id){

        Optional<Budget> o = service.porId(id);
        if(o.isPresent()){
            Budget budgetDb = o.get();
            if(budget.getName() !=null) budgetDb.setName(budget.getName());

            return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(budgetDb));
        }

        return ResponseEntity.notFound().build();

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){


        Optional<Budget> bdb = service.porId(id);

        if(bdb.isPresent()){
            service.eliminar(id);
            //NoContent es un OK pero sin contenido ya que el metodo no devuelve nada, simplemente va o no va.
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

}
