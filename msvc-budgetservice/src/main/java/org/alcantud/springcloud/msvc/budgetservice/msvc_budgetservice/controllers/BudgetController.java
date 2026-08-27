package org.alcantud.springcloud.msvc.budgetservice.msvc_budgetservice.controllers;

import jakarta.validation.Valid;
import org.alcantud.springcloud.msvc.budgetservice.msvc_budgetservice.models.entity.Budget;
import org.alcantud.springcloud.msvc.budgetservice.msvc_budgetservice.services.BudgetService;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class BudgetController {

    @Autowired
    private BudgetService service;

    @GetMapping("/userBudgets/{id}")
    public ResponseEntity<List<Budget>> listar(@PathVariable Long id){
        return ResponseEntity.ok(service.listar(id));

    }

    @GetMapping("budgetDetail/{id}")
    public ResponseEntity<Budget> detalle(@PathVariable Long id){
        Optional<Budget> o = service.porId(id);
        if(o.isPresent()){
            return ResponseEntity.ok(o.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/")
    public ResponseEntity<?> crear(@Valid @RequestBody Budget budget, BindingResult result){
        if (result.hasErrors()){
            return validate(result);
        }
        Budget budgetDb = service.guardar(budget);
        return ResponseEntity.status(HttpStatus.CREATED).body(budgetDb);
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody Budget budget, BindingResult result, @PathVariable Long id){
        if (result.hasErrors()){
            return validate(result);
        }

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


    private static @NonNull ResponseEntity<Map<String, String>> validate(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        //This is a lambda expresion
        result.getFieldErrors().forEach(error -> {
            errores.put(error.getField(), "The field " + error.getField() + " " + error.getDefaultMessage() );
        });

        return ResponseEntity.badRequest().body(errores);
    }

}
