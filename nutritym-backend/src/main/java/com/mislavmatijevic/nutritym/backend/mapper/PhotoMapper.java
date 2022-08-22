package com.mislavmatijevic.nutritym.backend.mapper;

import com.mislavmatijevic.nutritym.backend.dto.PhotoDto;
import com.mislavmatijevic.nutritym.backend.model.Photo;

import java.util.Base64;

public class PhotoMapper implements GenericMapper<PhotoDto, Photo>
{
    @Override
    public PhotoDto mapDto(final Photo entity)
    {
        PhotoDto photoDto = null;
        if (entity != null)
        {
            photoDto = new PhotoDto();
            photoDto.setName(entity.getName());
            photoDto.setJpgFileBase64(Base64.getEncoder().encodeToString(entity.getJpgFile()));
        }
        return photoDto;
    }

    @Override
    public Photo map(final PhotoDto dto)
    {
        Photo photo = null;
        if (dto != null)
        {
            photo = new Photo();
            photo.setName(dto.getName());
            photo.setJpgFile(Base64.getDecoder().decode(dto.getJpgFileBase64()));
        }
        return photo;
    }
}
