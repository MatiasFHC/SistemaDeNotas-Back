package pe.edu.upc.examenfinal.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class borrarNotaDTO {
    private Long id;
    private String titulo;
    private String descripcion;
    private String username;
}
