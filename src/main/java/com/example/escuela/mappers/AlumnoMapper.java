package com.example.escuela.mappers;

import com.example.escuela.model.AlumnoEntity;

import com.example.escuela.model.request.AlumnoRequest;
import com.example.escuela.model.response.AlumnoResponse;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
    public interface AlumnoMapper {

        @Mappings({
                // En caso de ser diferentes aqui es donde mapeas el nombre de cada uno
                @Mapping(target="nombre", source="nombre"),
                @Mapping(target="genero", source="genero"),
        })
        AlumnoEntity toAlumnoEntity(AlumnoRequest alumnoRequest);

        @Mappings({

        })
        AlumnoResponse toAlumnoResponse(AlumnoEntity alumnoEntity);

        List<AlumnoResponse> toAlumnoResponseList (List<AlumnoEntity> alumnoEntityList);
    }
