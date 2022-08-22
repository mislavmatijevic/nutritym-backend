package com.mislavmatijevic.nutritym.backend.dto;

public class PhotoDto
{
    private String name;
    private String jpgFileBase64;

    public String getName()
    {
        return name;
    }

    public void setName(final String name)
    {
        this.name = name;
    }

    public String getJpgFileBase64()
    {
        return jpgFileBase64;
    }

    public void setJpgFileBase64(final String jpgFileBase64)
    {
        this.jpgFileBase64 = jpgFileBase64;
    }
}
