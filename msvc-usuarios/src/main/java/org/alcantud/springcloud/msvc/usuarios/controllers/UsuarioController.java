package org.alcantud.springcloud.msvc.usuarios.controllers;

import org.alcantud.springcloud.msvc.usuarios.models.entity.Usuario;
import org.alcantud.springcloud.msvc.usuarios.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//RestController sirve para convertir las respuestas en formato JSON.
@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    //GetMappin se utiliza para la ruta que tiene el metodo en este caso el path vacio /
    @GetMapping
    public List<Usuario> listar(){
        return service.listar();
    }

    @GetMapping("/{id}")
    //Ponemos el ResponseEntity para que el tipo de respuesta pueda ser un status en caso de fallo.
    public ResponseEntity<?> detalle(@PathVariable Long id){

        Optional<Usuario> usuarioOptional = service.porId(id);
        if (usuarioOptional.isPresent()) {
            return ResponseEntity.ok().body( usuarioOptional.get());

        }
        //El .build() sirve para generar la respuesta en http, que no lo ha encontrado.
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    //El ResponseStatus sirve para indicar la respuesta correcta que devuelva el metodo, es decir en este caso el CREATED
    //No el ok.
    @ResponseStatus(HttpStatus.CREATED) //Tambien se puede hacer como en el metodo anterior ResponseEntity
    //El RequestBody lo que hace es coger el body y automaticamente hacerlo del tipo Usuario inyectandolo.
    public Usuario crear(@RequestBody Usuario usuario){
        return service.guardar(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@RequestBody Usuario usuario, @PathVariable Long id){

        Optional<Usuario> o = service.porId(id);
        if(o.isPresent()){
            Usuario usuarioDb = o.get();
            if(usuario.getNombre() !=null) usuarioDb.setNombre(usuario.getNombre());
            if(usuario.getEmail() !=null) usuarioDb.setEmail(usuario.getEmail());
            if(usuario.getPassword() !=null) usuarioDb.setPassword(usuario.getPassword());

            return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(usuarioDb));
        }

        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){


        Optional<Usuario> udb = service.porId(id);

        if(udb.isPresent()){
            service.eliminar(id);
            //NoContent es un OK pero sin contenido ya que el metodo no devuelve nada, simplemente va o no va.
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

}
