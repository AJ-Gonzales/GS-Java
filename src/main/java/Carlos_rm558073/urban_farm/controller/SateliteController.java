package Carlos_rm558073.urban_farm.controller;

import Carlos_rm558073.urban_farm.DTO.SateliteDTO;
import Carlos_rm558073.urban_farm.services.SateliteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/satelites")
public class SateliteController {

    @Autowired
    private SateliteService sateliteService;

    @GetMapping
    public ResponseEntity<List<SateliteDTO>> findAll(){
        List<SateliteDTO> dto = sateliteService.findAllSatelites();
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SateliteDTO> findById(@PathVariable Long id){
        SateliteDTO dto = sateliteService.findSatetiteById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<SateliteDTO> save(@Valid @RequestBody SateliteDTO dto){
        dto = sateliteService.saveSatelite(dto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(dto.getId())
                .toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SateliteDTO> update(@PathVariable Long id, @Valid @RequestBody SateliteDTO dto){
        dto = sateliteService.updateSatelite(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        sateliteService.deleteSateliteById(id);
        return ResponseEntity.noContent().build();
    }
}
