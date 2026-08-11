package org.alcantud.springcloud.msvc.budgetservice.msvc_budgetservice.services;

import org.alcantud.springcloud.msvc.budgetservice.msvc_budgetservice.models.entity.Budget;

import java.util.List;
import java.util.Optional;

public interface BudgetService {

    List<Budget> listar();
    Optional<Budget> porId(Long id);
    Budget guardar(Budget budget);
    void eliminar(Long id);


}
