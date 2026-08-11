package org.alcantud.springcloud.msvc.budgetservice.msvc_budgetservice.services;

import org.alcantud.springcloud.msvc.budgetservice.msvc_budgetservice.clients.UserClientRest;
import org.alcantud.springcloud.msvc.budgetservice.msvc_budgetservice.models.entity.Budget;
import org.alcantud.springcloud.msvc.budgetservice.msvc_budgetservice.repositories.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BudgetServiceImpl implements BudgetService{

    @Autowired
    private BudgetRepository repository;

    @Autowired
    private UserClientRest userClientRest;

    @Override
    @Transactional(readOnly = true)
    public List<Budget> listar() {
        return (List<Budget>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Budget> porId(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Budget guardar(Budget budget) {
        return repository.save(budget);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

}
