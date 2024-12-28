package pe.edu.upc.examenfinal.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.examenfinal.dtos.borrarNotaDTO;
import pe.edu.upc.examenfinal.dtos.notasDTO;
import pe.edu.upc.examenfinal.servicesinterfaces.notasService;

import java.util.List;

@RestController
@RequestMapping
public class notasController {
    final notasService NotasService;

    public notasController(notasService notasService) {
        this.NotasService = notasService;
    }

    @PostMapping("api/RegistrarNota")
    public ResponseEntity<notasDTO> registrarNota(@RequestBody notasDTO NOTASDTO, @RequestParam("username") String username) {
        return new ResponseEntity<>(NotasService.save(NOTASDTO, username), HttpStatus.CREATED);
    }

    @GetMapping("api/VerNotas")
    public ResponseEntity<List<notasDTO>> ListaNotasTotal(@RequestParam("username") String username) {
        return ResponseEntity.ok(NotasService.notasPorUsuario(username));
    }

    @GetMapping("api/VerNotaPorID")
    public ResponseEntity<List<notasDTO>> ListaIdNota(@RequestParam("id") Long id) {
        return ResponseEntity.ok(NotasService.idDeNota(id));
    }

    @DeleteMapping("api/BorrarNota")
    public ResponseEntity<borrarNotaDTO> eliminarNota(@RequestParam("id") Long id) {
        borrarNotaDTO deleteNota = NotasService.eliminarNota(id);
        return ResponseEntity.ok(deleteNota);
    }
}

