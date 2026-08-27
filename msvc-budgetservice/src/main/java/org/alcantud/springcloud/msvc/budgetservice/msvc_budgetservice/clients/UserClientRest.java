package org.alcantud.springcloud.msvc.budgetservice.msvc_budgetservice.clients;

import org.alcantud.springcloud.msvc.budgetservice.msvc_budgetservice.models.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="msvc-usuarios",url="localhost:8001")
public interface UserClientRest {

    @GetMapping("/{id}")
    User detalle(@PathVariable Long id);


}
