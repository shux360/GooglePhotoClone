package com.saw.photozclone;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@RestController
public class PhotozController {

    private Map<String, Photo> db = new HashMap<>() {{
        put("1", new Photo("1","hello.jpg"));
    }};

    @GetMapping("/")
    public String hello(){
        return "Hello World!";
    }

    @GetMapping("/photoz")
    public Collection<Photo> get() {
        return db.values();
    }

    @GetMapping("/photoz/{id}")
    public Photo get(@PathVariable String id){
        Photo photo = db.get(id);
        if(photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return photo;
    }

    @DeleteMapping("/photoz/{id}")
    public void delete(String id) {
        Photo photo = db.remove(id);
        if(photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/photoz/")
    public Photo create(@RequestBody @Valid Photo photo) {
        photo.setId(UUID.randomUUID().toString());
        db.put(photo.getId(),photo);
        return photo;
       }

}
