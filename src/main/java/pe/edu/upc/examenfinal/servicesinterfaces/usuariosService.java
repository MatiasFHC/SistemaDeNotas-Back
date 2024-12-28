package pe.edu.upc.examenfinal.servicesinterfaces;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pe.edu.upc.examenfinal.dtos.UsuarioDTO;
import pe.edu.upc.examenfinal.entities.Users;
import pe.edu.upc.examenfinal.repositories.UserRepository;

@Service
public class usuariosService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository usuariosRepository;

    public  usuariosService(UserRepository usuariosRepository) {
        this.usuariosRepository = usuariosRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public UsuarioDTO save (UsuarioDTO usuarioDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Users usuarios = modelMapper.map(usuarioDTO, Users.class);

        // Encriptar la contraseña antes de guardar
        usuarios.setPassword(passwordEncoder.encode(usuarios.getPassword()));

        //Guardar el usuario
        usuarios = usuariosRepository.save(usuarios);

        return modelMapper.map(usuarios, UsuarioDTO.class);
    }
}
