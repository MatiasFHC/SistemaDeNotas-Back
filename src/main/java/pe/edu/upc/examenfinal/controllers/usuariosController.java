package pe.edu.upc.examenfinal.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upc.examenfinal.dtos.UsuarioDTO;
import pe.edu.upc.examenfinal.servicesinterfaces.usuariosService;

@RestController
@RequestMapping
public class usuariosController {
    final usuariosService UsuariosService;

    public usuariosController(usuariosService usuariosServicce) {
        this.UsuariosService = usuariosServicce;
    }

    @PostMapping("api/RegistroDeNuevoUsuario")
    public ResponseEntity<?> register(@RequestBody UsuarioDTO userDTO) {
        String username = userDTO.getUsername();

        // Validar si el nombre de usuario ya existe
        if (UsuariosService.usernameExists(username)) {
            // Si existe, sugerir un nombre alternativo o devolver error
            return ResponseEntity.badRequest().body("El nombre de usuario ya está en uso");
        }

        // Si no existe, proceder con la creación del usuario
        return new ResponseEntity<>(UsuariosService.save(userDTO), HttpStatus.CREATED);
    }
}
