package eshopping.demo.image;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageService {

    private String uploadDir = "D:/DEV/Spring/ecommerce-Spring-main/ecommerce-Spring-main/demo/src/main/resources/static/app/imgs";

    public String saveImage(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        try (InputStream inputStream = file.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        }
        return fileName;
    }

    // public Resource getImage(String fileName) {
    //     try {
    //         Path filePath = Paths.get(uploadDir).resolve(fileName);
    //         Resource resource = new UrlResource(filePath.toUri());
    //         if (resource.exists() || resource.isReadable()) {
    //             return resource;
    //         } else {
    //             throw new RuntimeException("Failed to load image: " + fileName);
    //         }
    //     } catch (MalformedURLException e) {
    //         throw new RuntimeException("Failed to load image: " + fileName, e);
    //     }
    // }
}
