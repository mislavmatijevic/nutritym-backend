package com.mislavmatijevic.nutritym.backend.model;

import com.mislavmatijevic.nutritym.backend.exceptions.PhotoTooLargeException;

import javax.persistence.*;

@Entity
@Table(name = "PHOTOS")
public class Photo
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long photoId;

    private String name;

    private long timeAdded;

    private byte[] jpgFile;

    public void setPhotoId(final long photoId)
    {
        this.photoId = photoId;
    }

    public void setName(final String name)
    {
        this.name = name;
    }

    public void setTimeAdded(final long timeAdded)
    {
        this.timeAdded = timeAdded;
    }

    public void setPhotoBytes(final byte[] jpgFile)
    {
        if (jpgFile.length > 200_000)
        {
            throw new PhotoTooLargeException(jpgFile.length);
        }
        this.jpgFile = jpgFile;
    }
}
