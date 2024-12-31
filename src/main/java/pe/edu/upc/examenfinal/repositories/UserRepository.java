package pe.edu.upc.examenfinal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upc.examenfinal.entities.Users;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
	public Users findByUsername(String username);

	//BUSCAR POR NOMBRE
	@Query("select count(u.username) from Users u where u.username =:username")
	public int buscarUsername(@Param("username") String nombre);


	@Modifying
	@Transactional
	@Query(value = "INSERT INTO roles (rol, user_id) VALUES ('USER', :id_usuario)", nativeQuery = true)
	void insertUserRole(@Param("id_usuario") Long id_usuario);

}