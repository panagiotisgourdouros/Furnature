package team.groupproject.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import team.groupproject.errorHandling.FileStorageException;

@Service
public class FileService {

    @Value("${app.upload.dir}")

    public String uploadDir;

    public void uploadFile(MultipartFile file) {

        try {
            Path copyLocation = Paths
                    .get(uploadDir + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));
            Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>COPYLOCATION:" + copyLocation);
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>COPYLOCATIONURI:" + copyLocation.toUri());
        } catch (Exception e) {
            e.printStackTrace();
            throw new FileStorageException("Could not store file " + file.getOriginalFilename()
                    + ". Please try again!");
        }
    }

    public boolean deleteFile(String path) {
        try {
            return Files.deleteIfExists(Paths.get(uploadDir, path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
