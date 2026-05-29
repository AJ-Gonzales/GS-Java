package Carlos_rm558073.urban_farm.services;

import Carlos_rm558073.urban_farm.DTO.FazendaDTO;
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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Random;

import java.util.List;

@Service
public class FazendaService {

    @Autowired
    private FazendaRepository fazendaRepository;

    @Autowired
    private SateliteRepository sateliteRepository;

    @Transactional(readOnly = true)
    public List<FazendaDTO> findAllFazendas(){
        return fazendaRepository.findAll()
                .stream().map(FazendaDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public FazendaDTO findFazendaById(Long id){
        Fazenda fazenda = fazendaRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado. ID:"+ id));
        return new FazendaDTO(fazenda);
    }

    @Transactional
    public FazendaDTO saveFazenda(FazendaDTO inputDto){
        Fazenda fazenda = new Fazenda();
        mapDtoToFazenda(inputDto, fazenda);
        List<Satelite> satelites = sateliteRepository.findAll();
        if(satelites.isEmpty()){
            throw new ResourceNotFoundException("Nenhum satélite cadastrado no sistema");
        }

        Random random = new Random();
        int i = random.nextInt(satelites.size());
        Satelite sateliteAleatorio = satelites.get(i);
        fazenda.setSatelite(sateliteAleatorio);
        fazenda = fazendaRepository.save(fazenda);
        return new FazendaDTO(fazenda);
    }

    @Transactional
    public FazendaDTO updateFazenda(Long id, FazendaDTO fazendaDTO){
        try {
            Fazenda fazenda = fazendaRepository.getReferenceById(id);
            mapDtoToFazenda(fazendaDTO,fazenda);
            fazenda = fazendaRepository.save(fazenda);
            return new FazendaDTO(fazenda);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Recurso não encontrado. ID:"+ id);
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void deleteFazendaById(Long id){
        if (!fazendaRepository.existsById(id)){
            throw new ResourceNotFoundException("Recurso não encontrado. ID:"+ id);
        }
        try {
            fazendaRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Não foi possível excluir Fazenda");
        }
    }

    private void mapDtoToFazenda(FazendaDTO inputDto, Fazenda fazenda) {
        fazenda.setNomeFazenda(inputDto.getNomeFazenda());
        fazenda.setRegiao(inputDto.getRegiao());
        fazenda.setAreaCultivada(inputDto.getAreaCultivada());
        fazenda.setUltimaIrrigacao(inputDto.getUltimaIrrigacao());
    }
}
