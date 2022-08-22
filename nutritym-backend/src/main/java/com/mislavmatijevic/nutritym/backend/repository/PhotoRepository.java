package com.mislavmatijevic.nutritym.backend.repository;

import com.mislavmatijevic.nutritym.backend.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long>
{
    ArrayList<Photo> getPhotosByTimeAddedAfterOrderByTimeAddedDesc(final long timeAdded);
}
