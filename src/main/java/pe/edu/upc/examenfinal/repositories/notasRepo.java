package pe.edu.upc.examenfinal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.edu.upc.examenfinal.entities.notas;

import javax.persistence.Tuple;
import java.util.List;

public interface notasRepo extends JpaRepository<notas, Long> {
    @Query(value="SELECT * from notas where username = :username", nativeQuery= true)
    List<Tuple> notasPorUsuario(@Param("username") String username);

    @Query(value="SELECT * from notas where id =:id", nativeQuery= true)
    List<Tuple> idDeNota(@Param("id") Long id);
}
