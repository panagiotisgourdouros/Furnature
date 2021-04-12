package team.groupproject.service;

import java.util.List;
import java.util.Optional;

import team.groupproject.entity.Style;

public interface StyleService {

    List<Style> findAll();

    Optional<Style> findByName(String name);

    Optional<Style> findById(Integer id);

    Style updateStyle(Integer id, String newName);

    void deleteStyle(Style style);

    Style saveStyle(Style style);

}
