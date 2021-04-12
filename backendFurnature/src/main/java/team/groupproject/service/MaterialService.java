package team.groupproject.service;

import java.util.List;
import java.util.Optional;
import team.groupproject.entity.Material;

public interface MaterialService {

    List<Material> findAll();

    Optional<Material> findByName(String name);

    Optional<Material> findById(Integer id);

    Material updateMaterial(Integer id, String newName);

    void deleteMaterial(Material material);

    Material saveMaterial(Material material);

}
