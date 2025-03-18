package kg.attractor.jobsearch.controlers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("images")
@RequiredArgsConstructor
public class ImageController {

    @GetMapping("{imageName}")
    public ResponseEntity<?> getImage(@PathVariable String imageName) {
//        return imageService.findByName(imageName);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public String uploadImage(MultipartFile file) {
//        return imageService.saveImage(file);
        return "";
    }
}
