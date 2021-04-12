package team.groupproject.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.groupproject.entity.Material;
import team.groupproject.repository.MaterialRepo;

@Service
public class MaterialServiceImpl implements MaterialService {

    @Autowired
    MaterialRepo materialRepo;

    @Autowired
    MaterialService materialService;

    public List<Material> findAll() {
        return materialRepo.findAll();
    }

    public Optional<Material> findById(Integer id) {
        return materialRepo.findById(id);
    }

    public Optional<Material> findByName(String name) {
        return materialRepo.findByName(name);
    }

    public Material updateMaterial(Integer id, String newName) {
        Optional<Material> materialOpt = materialRepo.findById(id);

        if (materialOpt.isPresent()) {
            Material material = materialOpt.get();
            material.setName(newName);
            materialService.saveMaterial(material);
            return material;
        } else {
            return null;
        }

    }

    public void deleteMaterial(Material material) {
        materialRepo.delete(material);
    }

    public Material saveMaterial(Material material) {
        return materialRepo.save(material);
    }

}
