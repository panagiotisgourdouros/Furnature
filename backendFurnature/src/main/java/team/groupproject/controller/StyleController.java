package team.groupproject.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.groupproject.entity.Style;
import team.groupproject.errorHandling.APIException;
import team.groupproject.repository.StyleRepo;
import team.groupproject.service.StyleService;

@RestController
@CrossOrigin
@RequestMapping("/api/admin/style")
public class StyleController {

    @Autowired
    StyleService styleService;

    @Autowired
    StyleRepo styleRepo;

    @GetMapping
    public ResponseEntity<List<Style>> getAllStyles() {
        return ResponseEntity.ok(styleService.findAll());
    }

    @PostMapping("/{name}")
    public ResponseEntity<Style> createStyle(@PathVariable String name) {
        Optional<Style> styleOpt = styleService.findByName(name);
        
        if (styleOpt.isPresent()) {
            throw new APIException(String.format("Style with Name : %s already exists", name));

        } else {
            Style style = styleService.saveStyle(new Style(name));
            return ResponseEntity.ok(style);
        }

    }

    @PutMapping("/{id}/{newName}")
    public ResponseEntity<Style> updateCategory(@PathVariable Integer id, @PathVariable String newName) {

        Style style = styleService.updateStyle(id, newName);

        if (style != null) {
            return ResponseEntity.ok(style);
        } else {
            throw new APIException(String.format("Style with Id : %d does not exist", id));
        }

    }

}
