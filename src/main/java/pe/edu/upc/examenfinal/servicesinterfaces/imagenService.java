package pe.edu.upc.examenfinal.servicesinterfaces;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pe.edu.upc.examenfinal.dtos.imagenDTO;
import pe.edu.upc.examenfinal.entities.imagen;
import pe.edu.upc.examenfinal.repositories.imagenRepository;
import org.springframework.beans.factory.annotation.Value;


import javax.persistence.Tuple;
import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class imagenService {
    final imagenRepository ImagenRepository;

    public imagenService(imagenRepository ImagenRepository) {
        this.ImagenRepository = ImagenRepository;
    }

    //registrar imagen por nota de usuario
    public imagenDTO save(imagenDTO imagenDTO, String imageUrl, BigDecimal notaid) {
        ModelMapper modelMapper = new ModelMapper();

        imagen IMAGEN = modelMapper.map(imagenDTO, imagen.class);

        IMAGEN.setDireccion(imageUrl);
        IMAGEN.setNotaid(notaid);
        IMAGEN = ImagenRepository.save(IMAGEN);

        return modelMapper.map(IMAGEN, imagenDTO.class);
    }

    //obtener las notas de un usuario
    public List<imagenDTO> imagenPorUsuario(BigDecimal notaid) {
        List<Tuple> tuplas = ImagenRepository.imagenPorUsuario(notaid);
        List<imagenDTO> listImagen = new ArrayList<>();
        imagenDTO IMAGEN;

        for (Tuple tuple : tuplas) {
            IMAGEN = new imagenDTO(
                    tuple.get("id", BigInteger.class),
                    tuple.get("direccion", String.class),
                    tuple.get("notaid", BigDecimal.class)
            );
            listImagen.add(IMAGEN);
        }
        return listImagen;
    }


    //eliminar imagen
    public imagenDTO eliminarImagen(Long id) {
        // Buscar la imagen en la base de datos
        imagen imagen = ImagenRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("La imagen con ID " + id + " no existe."));

        // Obtener la dirección de la imagen sin "uploads/" y eliminar espacios en blanco
        String rutaCompleta = "uploads/" + imagen.getDireccion().replace("/uploads/", "").trim(); // Usamos trim() para eliminar espacios
        
        // Eliminar el archivo físico si existe
        File archivo = new File(rutaCompleta);
        if (archivo.exists()) {
            if (archivo.delete()) {
                System.out.println("Archivo eliminado correctamente: " + rutaCompleta);
            } else {
                throw new RuntimeException("No se pudo eliminar el archivo físico: " + rutaCompleta);
            }
        } else {
            System.out.println("El archivo físico no existe: " + rutaCompleta);
        }

        // Eliminar el registro de la imagen en la base de datos
        ImagenRepository.delete(imagen);
        return null;
    }
}
