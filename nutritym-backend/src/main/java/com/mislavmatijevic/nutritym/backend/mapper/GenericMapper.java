package com.mislavmatijevic.nutritym.backend.mapper;

/**
 * Idea based on sassa400's: https://github.com/sassa400/WebshopBasics
 * This generic mapper allows users to implement all kinds of DTO-to-entity mappers.
 *
 * @param <D> Data Transfer Object (DTO) object.
 * @param <E> Entity (persistence) object.
 */
public interface GenericMapper<D, E>
{
    D mapDto(E entity);

    E map(D dto);
}
