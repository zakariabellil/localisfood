package com.LocalisFood.LocalisFood.Controller;

import com.LocalisFood.LocalisFood.Model.ImageModel;
import com.LocalisFood.LocalisFood.Repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

import static com.LocalisFood.LocalisFood.Service.ImageUploadService.compressBytes;
import static com.LocalisFood.LocalisFood.Service.ImageService.decompressBytes;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "api/image")

public class ImageUploadController {
    @Autowired
    ImageRepository imageRepository;
    @PostMapping("/upload")
    public BodyBuilder uplaodImage(@RequestParam("imageFile") MultipartFile file) throws IOException {
        System.out.println("Original Image Byte Size - " + file.getBytes().length);
        ImageModel img = new ImageModel(file.getOriginalFilename(), file.getContentType(),compressBytes(file.getBytes()));
        imageRepository.save(img);
        return ResponseEntity.status(HttpStatus.OK);
    }

    @GetMapping(path = { "/get/{imageName}" })
    public ImageModel getImage(@PathVariable("imageName") String imageName) throws IOException {
        System.out.println("xxxxxx");
        final Optional<ImageModel> retrievedImage = imageRepository.findByName(imageName);
        ImageModel img = new ImageModel(retrievedImage.get().getName(), retrievedImage.get().getType(),
                decompressBytes(retrievedImage.get().getPicByte()));
        return img;
    }

    // compress the image bytes before storing it in the database

}
