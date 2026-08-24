package org.alcantud.springcloud.msvc.budgetservice.msvc_budgetservice.services;

import feign.FeignException;
import org.alcantud.springcloud.msvc.budgetservice.msvc_budgetservice.clients.UserClientRest;
import org.alcantud.springcloud.msvc.budgetservice.msvc_budgetservice.models.User;
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
    /*
    * Metodo para listar los budget de un usuario.
    * */
    public List<Budget> listar(Long id) {
        try {
            //Usamos el userClientRest para que valide si existe el usuario.
            userClientRest.detalle(id);
        } catch (FeignException e) {
            throw new RuntimeException("User not found with id: " +id);

        }

        return repository.findByUserId(id);
    }



    @Override
    @Transactional(readOnly = true)
    public Optional<Budget> porId(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Budget guardar(Budget budget) {
        try {
            //Usamos el userClientRest para que valide si existe el usuario.
            userClientRest.detalle(budget.getUserId());
        } catch (FeignException e) {
            throw new RuntimeException("User not found with id: " + budget.getUserId());

        }
        return repository.save(budget);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

}
