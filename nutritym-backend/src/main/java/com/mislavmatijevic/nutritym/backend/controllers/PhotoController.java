package com.mislavmatijevic.nutritym.backend.controllers;

import com.mislavmatijevic.nutritym.backend.dto.PhotoDto;
import com.mislavmatijevic.nutritym.backend.exceptions.PhotoNotFoundException;
import com.mislavmatijevic.nutritym.backend.mapper.PhotoMapper;
import com.mislavmatijevic.nutritym.backend.model.Photo;
import com.mislavmatijevic.nutritym.backend.responses.ApiResponse;
import com.mislavmatijevic.nutritym.backend.services.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@RestController
@RequestMapping("/api/photos")
public class PhotoController
{
    private final PhotoService photoService;
    private final PhotoMapper photoMapper = new PhotoMapper();
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @Autowired
    public PhotoController(PhotoService photoService)
    {
        this.photoService = photoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhotoDto> getPhotoById(@PathVariable long id)
    {
        try
        {
            Photo storedPhoto = photoService.getById(id);
            PhotoDto photoDto = photoMapper.mapDto(storedPhoto);
            return ResponseEntity.status(HttpStatus.CREATED).body(photoDto);
        }
        catch (EntityNotFoundException ex)
        {
            ex.printStackTrace();
            throw new PhotoNotFoundException(id);
        }
    }

    @GetMapping
    public ResponseEntity<Object> getAllPhotosAfterDate(@RequestParam("after") String earliestDate)
    {
        Date date = null;
        try
        {
            date = simpleDateFormat.parse(earliestDate);
        }
        catch (ParseException ex)
        {
            ex.printStackTrace();
        }

        if (date != null)
        {
            ArrayList<Photo> storedPhotos = photoService.getAllPhotosAfterDate(date);
            return ResponseEntity.ok(storedPhotos);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(false, "Date was not passed in correct format. Correct format is dd/MM/yyyy as in 31/12/2022."));
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse> uploadPhoto(@RequestBody PhotoDto photo)
    {
        long newPhotoId = photoService.upload(photoMapper.map(photo));
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(String.valueOf(newPhotoId)));
    }
}
