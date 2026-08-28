package org.alcantud.springcloud.msvc.budgetservice.msvc_budgetservice.repositories;

import org.alcantud.springcloud.msvc.budgetservice.msvc_budgetservice.models.entity.Budget;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BudgetRepository extends CrudRepository<Budget, Long>{

    List<Budget> findByUserId(Long userId);

}
