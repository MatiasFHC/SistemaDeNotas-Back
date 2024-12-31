package pe.edu.upc.examenfinal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.edu.upc.examenfinal.entities.imagen;

import javax.persistence.Tuple;
import java.math.BigDecimal;
import java.util.List;

public interface imagenRepository extends JpaRepository<imagen, Long> {
    @Query(value="SELECT * from imagen where notaid = :notaid", nativeQuery= true)
    List<Tuple> imagenPorUsuario(@Param("notaid") BigDecimal notaid);
}
