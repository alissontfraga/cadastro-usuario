package com.alissontfraga.cadastro_usuario.controller;

import com.alissontfraga.cadastro_usuario.business.UsuarioService;
import com.alissontfraga.cadastro_usuario.infrastructure.entities.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController                     // Diz que essa classe é um controller REST (API)
@RequestMapping("/usuario")         // Define a rota base -> tudo aqui começa com /usuario
@RequiredArgsConstructor            // Gera um construtor automático para os atributos "final"

public class UsuarioController {

    private final UsuarioService usuarioService; // Injeta o serviço que lida com regras de negócio

    @PostMapping                                      // Diz que esse métod responde a requisições HTTP POST
    public ResponseEntity<Void> salvarUsuario(@RequestBody Usuario usuario){
        usuarioService.salvarUsuario(usuario);                      // chama o service para salvar o usuário
        return ResponseEntity.ok().build();                         // retorna resposta HTTP 200 (sucesso)
    }

    @GetMapping
    public ResponseEntity<Usuario> buscarUsuarioPorEmail(@RequestParam String email){
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email));
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarUsuarioPorEmail(@RequestParam String email){
        usuarioService.deletarUsuarioPorEmail(email);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> atualizarUsuarioPorId(@RequestParam Integer id, @RequestBody Usuario usuario){
        usuarioService.atualizarUsuarioPorId(id, usuario);
        return ResponseEntity.ok().build();
    }


}
