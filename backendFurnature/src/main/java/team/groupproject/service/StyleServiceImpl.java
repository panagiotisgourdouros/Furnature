package team.groupproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team.groupproject.entity.Style;
import team.groupproject.repository.StyleRepo;

@Service
public class StyleServiceImpl implements StyleService {

    @Autowired
    StyleRepo styleRepo;

    public List<Style> findAll() {

        return styleRepo.findAll();

    }

    public Optional<Style> findById(Integer id) {

        return styleRepo.findById(id);

    }

    public Optional<Style> findByName(String name) {

        return styleRepo.findByName(name);

    }

    public Style updateStyle(Integer id, String newName) {

        Optional<Style> styleOpt = styleRepo.findById(id);

        if (styleOpt.isPresent()) {

            Style style = styleOpt.get();
            style.setName(newName);
            styleRepo.save(style);
            return style;

        } else {

            return null;

        }

    }

    public void deleteStyle(Style style) {

        styleRepo.delete(style);

    }

    public Style saveStyle(Style style) {

        return styleRepo.save(style);
    }

}
