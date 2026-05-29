package Carlos_rm558073.urban_farm.DTO;

import Carlos_rm558073.urban_farm.entities.Fazenda;
import Carlos_rm558073.urban_farm.entities.Satelite;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SateliteDTO {

    private Long id;

    @NotBlank(message = "O nome do satélite é requerido")
    @Size(min = 2,max = 100,message = "O nome do satélite deve ter entre 2 e 100 caracteres")
    private String nomeSatelite;

    @NotNull(message = "A data da captura é requerida")
    @PastOrPresent(message = "A data deve ser até a data presente")
    private LocalDate dataCaptura;

    @NotNull(message = "A temperatura detectada é requerida")
    private Double temperaturaDetectada;

    @NotNull(message = "O nível de poluição local é requerido")
    @PositiveOrZero(message = "O nível de poluição local deve ser positivo ou zero")
    private Double nivelPoluicao;

    private List<FazendaDTO> fazendas = new ArrayList<>();


    public SateliteDTO(Satelite satelite) {
        id = satelite.getId();
        nomeSatelite = satelite.getNomeSatelite();
        dataCaptura = satelite.getDataCaptura();
        temperaturaDetectada = satelite.getTemperaturaDetectada();
        nivelPoluicao = satelite.getNivelPoluicao();
        for(Fazenda fazenda : satelite.getFazendas()){

            fazendas.add(new FazendaDTO(fazenda));
        }
    }
}
