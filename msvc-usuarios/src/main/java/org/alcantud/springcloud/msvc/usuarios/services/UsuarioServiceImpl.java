package org.alcantud.springcloud.msvc.usuarios.services;

import org.alcantud.springcloud.msvc.usuarios.models.entity.Usuario;
import org.alcantud.springcloud.msvc.usuarios.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    @Autowired
    private UsuarioRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> listar() {
        return (List<Usuario>) repository.findAll() ;
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Usuario> porId(Long id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public Usuario guardar(Usuario usuario) {
        return repository.save(usuario);
    }

    @Transactional
    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
