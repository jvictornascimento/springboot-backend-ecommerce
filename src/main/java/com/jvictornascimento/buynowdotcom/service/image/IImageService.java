package com.jvictornascimento.buynowdotcom.service.image;

import com.jvictornascimento.buynowdotcom.dtos.ImageDto;
import com.jvictornascimento.buynowdotcom.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IImageService {
    Image getImageById(Long imageId);
    void deleteImageById(Long imageId);
    void updateImage(MultipartFile file, Long iamgeId);
    List<ImageDto> saveImages(Long productId, List<MultipartFile> files);
}
