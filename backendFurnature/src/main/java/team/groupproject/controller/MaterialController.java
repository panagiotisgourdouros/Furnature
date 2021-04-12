package team.groupproject.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.groupproject.dto.MaterialDto;
import team.groupproject.entity.Material;
import team.groupproject.errorHandling.APIException;
import team.groupproject.repository.MaterialRepo;
import team.groupproject.service.MaterialService;

@RestController
@CrossOrigin
@RequestMapping("/api/admin/material")
public class MaterialController {

    @Autowired
    MaterialService materialService;

    @Autowired
    MaterialRepo materialRepo;

    private String referrer;

    @GetMapping
    ResponseEntity<List<MaterialDto>> getAllMaterials() {
        List<MaterialDto> list = materialRepo.findAll().stream().map(m -> new MaterialDto(m.getId(), m.getName()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @PostMapping("/{name}")
    public ResponseEntity<MaterialDto> createMaterial(@PathVariable String name) {

        Optional<Material> materialOpt = materialService.findByName(name);
        if (materialOpt.isPresent()) {
            throw new APIException(String.format("Material with Name : %s already exists", name));
        } else {
            Material m = materialService.saveMaterial(new Material(name));
            return ResponseEntity.ok(new MaterialDto(m.getId(), m.getName()));
        }

    }

    @PutMapping("/{id}/{newName}")
    public ResponseEntity<MaterialDto> updateMaterial(@PathVariable Integer id, @PathVariable String newName, HttpServletRequest httprequest) {
        referrer = httprequest.getHeader("referer");
        System.out.println(">>>>>>>>>>>>>REQUEST COMES FROM " + referrer);
        Material material = materialService.updateMaterial(id, newName);

        if (material != null) {
            return ResponseEntity.ok(new MaterialDto(material.getId(), material.getName()));
        } else {
            throw new APIException(String.format("Material with Id : %d does not exist", id));
        }

    }

}
