package com.example.escuela.mappers;

import com.example.escuela.model.MateriaEntity;
import com.example.escuela.model.request.MateriaRequest;
import com.example.escuela.model.response.MateriaResponse;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
    public interface MateriaMapper {
        @Mappings({
                // En caso de ser diferentes aqui es donde mapeas el nombre de cada uno
        })
        MateriaEntity toMateriaEntity(MateriaRequest materiaRequest);

        @Mappings({
                @Mapping(target="nombreMateria", source="nombre"),
                @Mapping(target="descripcionMateria", source="descripcion"),
        })
        MateriaResponse toMateriaResponse(MateriaEntity materiaEntity);

        List<MateriaResponse> toMateriaResponseList (List<MateriaEntity> materiaEntityList);
    }
