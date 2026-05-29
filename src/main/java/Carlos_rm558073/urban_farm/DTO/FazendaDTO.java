package Carlos_rm558073.urban_farm.DTO;

import Carlos_rm558073.urban_farm.entities.Fazenda;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class FazendaDTO {

    private Long id;

    @NotBlank(message = "O nome da fazenda é requerido")
    @Size(min = 3,max = 100, message = "O nome da fazenda deve ter entre 2 e 100 caracteres")
    private String nomeFazenda;

    @NotBlank(message = "A regiao onde está a fazenda é requerida")
    @Size(min = 3,max = 80,message = "A regiao onde está a fazenda deve ter entre 3 e 80 caracteres")
    private String regiao;

    @NotNull(message = "A área cultivada é requerida")
    @Positive(message = "A área cultivada deve ser positiva")
    private Double areaCultivada;

    @NotNull(message = "A data da última irrigação é requerida")
    @PastOrPresent(message ="A data da última irrigação tem que ser passada ou atual")
    private LocalDate ultimaIrrigacao;

    public FazendaDTO(Fazenda fazenda){
        id = fazenda.getId();
        nomeFazenda = fazenda.getNomeFazenda();
        regiao = fazenda.getRegiao();
        areaCultivada = fazenda.getAreaCultivada();
        ultimaIrrigacao = fazenda.getUltimaIrrigacao();
    }
}
