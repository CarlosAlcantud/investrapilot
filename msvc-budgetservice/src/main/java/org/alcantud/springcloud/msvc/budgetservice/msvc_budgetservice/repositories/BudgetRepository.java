package org.alcantud.springcloud.msvc.budgetservice.msvc_budgetservice.repositories;

import org.alcantud.springcloud.msvc.budgetservice.msvc_budgetservice.entity.Budget;
import org.springframework.data.repository.CrudRepository;

public interface BudgetRepository extends CrudRepository<Budget, Long>{

}
