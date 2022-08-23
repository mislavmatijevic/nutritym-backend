package com.mislavmatijevic.nutritym.backend.services;

import com.mislavmatijevic.nutritym.backend.model.Photo;
import com.mislavmatijevic.nutritym.backend.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;

@Service
public class PhotoService
{
    private final PhotoRepository photoRepository;

    @Autowired
    public PhotoService(PhotoRepository photoRepository)
    {
        this.photoRepository = photoRepository;
    }

    @Transactional
    public long upload(final Photo photo)
    {
        return photoRepository.saveAndFlush(photo).getPhotoId();
    }

    @Transactional
    public Photo getById(final long id) throws EntityNotFoundException
    {
        return photoRepository.getReferenceById(id);
    }

    @Transactional
    public ArrayList<Photo> getAllPhotosAfterDate(final Date earliestDate)
    {
        return photoRepository.getPhotosByTimeAddedAfterOrderByTimeAddedDesc(earliestDate.getTime());
    }
}
