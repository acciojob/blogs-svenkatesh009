package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog

        Optional<Blog> blog=blogRepository2.findById(blogId);
        Image image=new Image();
        image.setDescription(description);
        image.setDimensions(dimensions);
        blog.get().getImageList().add(image);
        blogRepository2.save(blog.get());
        return image;
    }

    public void deleteImage(Integer id){
        blogRepository2.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        Optional<Image> image = imageRepository2.findById(id);
        if(!image.isPresent())return 0;
        String str = image.get().getDimensions();
        String arr1[] = str.split("X");
        String arr2[] = screenDimensions.split("X");
        return (Integer.valueOf(arr2[0])/Integer.valueOf(arr1[0])) * (Integer.valueOf(arr2[1])/Integer.valueOf(arr1[1])) ;
    }

    ///
}
