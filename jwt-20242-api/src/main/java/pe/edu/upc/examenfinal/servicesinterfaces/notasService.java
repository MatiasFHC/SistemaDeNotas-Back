package pe.edu.upc.examenfinal.servicesinterfaces;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pe.edu.upc.examenfinal.dtos.borrarNotaDTO;
import pe.edu.upc.examenfinal.dtos.notasDTO;
import pe.edu.upc.examenfinal.entities.notas;
import pe.edu.upc.examenfinal.repositories.notasRepo;

import javax.persistence.Tuple;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class notasService {
    final notasRepo notasRepository;

    public notasService(notasRepo notasRepository) {
        this.notasRepository = notasRepository;
    }

    //registrar nota por usuario
    public notasDTO save(notasDTO notasDTO, String username) {
        ModelMapper modelMapper = new ModelMapper();

        notas notass = modelMapper.map(notasDTO, notas.class);

        notass.setUsername(username);
        notass = notasRepository.save(notass);

        return modelMapper.map(notass, notasDTO.class);
    }

    //obtener las notas de un usuario
    public List<notasDTO> notasPorUsuario(String username) {
        List<Tuple> tuplas = notasRepository.notasPorUsuario(username);
        List<notasDTO> listNotas = new ArrayList<>();
        notasDTO NOTAS;

        for (Tuple tuple : tuplas) {
            NOTAS = new notasDTO(
                    tuple.get("id", BigInteger.class),
                    tuple.get("titulo", String.class),
                    tuple.get("descripcion", String.class),
                    tuple.get("username", String.class)
                    );
            listNotas.add(NOTAS);
        }
        return listNotas;
    }

    //eliminar nota
    public borrarNotaDTO eliminarNota(Long id) {
        Optional<notas> nota = notasRepository.findById(id);

        if (nota.isPresent()) {
            notas notas = nota.get();
            notasRepository.delete(notas);
            notasRepository.delete(notas);
            return null;
        } else {
            throw new NoSuchElementException("No existe una nota con el ID: " + id);
        }
    }

    //obtener nota por id
    public List<notasDTO> idDeNota(Long id) {
        List<Tuple> tuplas = notasRepository.idDeNota(id);
        List<notasDTO> listNotas = new ArrayList<>();
        notasDTO NOTAS;

        for (Tuple tuple : tuplas) {
            NOTAS = new notasDTO(
                    tuple.get("id", BigInteger.class),
                    tuple.get("titulo", String.class),
                    tuple.get("descripcion", String.class),
                    tuple.get("username", String.class)
            );
            listNotas.add(NOTAS);
        }
        return listNotas;
    }
}
