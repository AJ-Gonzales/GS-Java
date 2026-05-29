package Carlos_rm558073.urban_farm.services;

import Carlos_rm558073.urban_farm.DTO.SateliteDTO;
import Carlos_rm558073.urban_farm.entities.Fazenda;
import Carlos_rm558073.urban_farm.entities.Satelite;
import Carlos_rm558073.urban_farm.exceptions.DatabaseException;
import Carlos_rm558073.urban_farm.exceptions.ResourceNotFoundException;
import Carlos_rm558073.urban_farm.repositories.FazendaRepository;
import Carlos_rm558073.urban_farm.repositories.SateliteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SateliteService {

    @Autowired
    private SateliteRepository sateliteRepository;

    @Autowired
    private FazendaRepository fazendaRepository;

    @Transactional(readOnly = true)
    public List<SateliteDTO> findAllSatelites(){
        return sateliteRepository.findAll().stream().map(SateliteDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public SateliteDTO findSatetiteById(Long id){
        Satelite satelite = sateliteRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado. ID:"+ id));
        return new SateliteDTO(satelite);
    }

    @Transactional
    public SateliteDTO saveSatelite(SateliteDTO inputDto){
        try {
            Satelite satelite = new Satelite();
            mapDtoToSatelite(inputDto,satelite);
            return new SateliteDTO(satelite);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Não foi possivel salvar satélite");
        }
    }

    @Transactional
    public SateliteDTO updateSatelite(Long id, SateliteDTO dto){
        try {
            Satelite satelite = sateliteRepository.getReferenceById(id);
            mapDtoToSatelite(dto, satelite);
            satelite = sateliteRepository.save(satelite);
            return new SateliteDTO(satelite);
        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Recurso não encontrado. ID: " + id);
        }
    }

    @Transactional
    public void deleteSateliteById(Long id){
        if (!sateliteRepository.existsById(id)){
            throw new ResourceNotFoundException("Recurso não encontrado. ID: " + id);
        }
        sateliteRepository.deleteById(id);
    }

    private void mapDtoToSatelite(SateliteDTO dto, Satelite satelite){
        satelite.setNomeSatelite(dto.getNomeSatelite());
        satelite.setDataCaptura(dto.getDataCaptura());
        satelite.setTemperaturaDetectada(dto.getTemperaturaDetectada());
        satelite.setNivelPoluicao(dto.getNivelPoluicao());
    }

}
