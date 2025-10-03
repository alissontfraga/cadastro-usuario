package com.alissontfraga.cadastro_usuario.business;

import com.alissontfraga.cadastro_usuario.infrastructure.entities.Usuario;
import com.alissontfraga.cadastro_usuario.infrastructure.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;


    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public void salvarUsuario(Usuario usuario){
        repository.saveAndFlush(usuario);
    }

    public Usuario buscarUsuarioPorEmail(String email){

        return repository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("Email não encontrado")
        );
    }

    public void deletarUsuarioPorEmail(String email){
        repository.deleteByEmail(email);
    }

    public void atualizarUsuarioPorId(Integer id, Usuario usuario){
        Usuario usuarioEntity = repository.findById(id).orElseThrow(
                () -> new RuntimeException("Usuario não encontrado"));

        Usuario usuarioAtualizado = Usuario.builder()
        .email(usuario.getEmail() != null ? usuario.getEmail() : usuarioEntity.getEmail())

        .nome(usuario.getNome() != null ? usuario.getNome() : usuarioEntity.getNome())

        /*   (quero alterar só o email? ) Posso passar só o email e o nome pego da entity,
                ou vice versa  */

        .id(usuarioEntity.getId())

        .build();

        repository.saveAndFlush(usuarioAtualizado);
    }


}
