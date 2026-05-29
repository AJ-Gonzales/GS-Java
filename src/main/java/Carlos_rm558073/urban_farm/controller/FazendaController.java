package Carlos_rm558073.urban_farm.controller;

import Carlos_rm558073.urban_farm.DTO.FazendaDTO;
import Carlos_rm558073.urban_farm.services.FazendaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/fazendas")
public class FazendaController {

    @Autowired
    private FazendaService fazendaService;

    @GetMapping
    public ResponseEntity<List<FazendaDTO>> getAll(){
        List<FazendaDTO> dto = fazendaService.findAllFazendas();
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FazendaDTO> findById(@PathVariable Long id){
        FazendaDTO dto = fazendaService.findFazendaById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<FazendaDTO> save(@Valid @RequestBody FazendaDTO dto){
        dto = fazendaService.saveFazenda(dto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(dto.getId())
                .toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FazendaDTO> update(@PathVariable Long id, @Valid @RequestBody FazendaDTO dto){
        dto = fazendaService.updateFazenda(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FazendaDTO> delete(@PathVariable Long id){
        fazendaService.deleteFazendaById(id);
        return ResponseEntity.noContent().build();
    }
}
