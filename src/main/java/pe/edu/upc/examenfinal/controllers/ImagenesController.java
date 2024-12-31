package pe.edu.upc.examenfinal.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pe.edu.upc.examenfinal.dtos.borrarNotaDTO;
import pe.edu.upc.examenfinal.dtos.imagenDTO;
import pe.edu.upc.examenfinal.servicesinterfaces.imagenService;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping
public class ImagenesController {
    final imagenService ImagenService;
    public ImagenesController(imagenService imagenService) {
        this.ImagenService = imagenService;
    }

    // Directorio donde se almacenarán las imágenes
    private static final String UPLOAD_DIR = "uploads/";

    static {
        // Crear el directorio de carga si no existe
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
    }

    @PostMapping("api/upload")
    public ResponseEntity<?> uploadImage(
            @RequestParam("image") MultipartFile file,
            @RequestParam("notaid") BigDecimal notaid) {

        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("No file uploaded.");
        }

        try {
            // Generar un nombre único para el archivo
            String fileName = UUID.randomUUID().toString() + "-" + StringUtils.cleanPath(file.getOriginalFilename());
            if (!fileName.matches("[\\w\\-. ]+")) {
                throw new IllegalArgumentException("Invalid file name: " + fileName);
            }
            Path targetLocation = Paths.get(UPLOAD_DIR + fileName);

            // Guardar el archivo en el servidor
            Files.copy(file.getInputStream(), targetLocation);

            // Generar la URL de la imagen cargada
            String imageUrl = "/uploads/" + fileName;

            // Crear y llenar el DTO dentro del controlador
            imagenDTO imagenDTO = new imagenDTO();
            imagenDTO.setDireccion(imageUrl); // Asignar la URL al DTO
            imagenDTO.setNotaid(notaid);        // Asignar el username al DTO

            // Registrar la imagen en el sistema
            imagenDTO responseDTO = ImagenService.save(imagenDTO, imageUrl, notaid);

            // Retornar la respuesta con la información registrada
            return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("File upload failed: " + e.getMessage());
        }
    }


    @GetMapping("api/VerImagen")
    public ResponseEntity<List<imagenDTO>> ImagenCargada(@RequestParam("notaid") BigDecimal notaid) {
        List<imagenDTO> imagenUrls = ImagenService.imagenPorUsuario(notaid);
        return ResponseEntity.ok(imagenUrls);
    }

    @DeleteMapping("api/BorrarImagen")
    public ResponseEntity<imagenDTO> eliminarImagen(@RequestParam("id") Long id) {
        imagenDTO deleteImagen = ImagenService.eliminarImagen(id);
        return ResponseEntity.ok(deleteImagen);
    }
}