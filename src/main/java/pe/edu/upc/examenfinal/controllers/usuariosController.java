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
    public ResponseEntity<UsuarioDTO> create(@RequestBody UsuarioDTO usuarioDTO) {
        return new ResponseEntity<>(UsuariosService.save(usuarioDTO), HttpStatus.CREATED);
    }
}
