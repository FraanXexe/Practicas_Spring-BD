package com.example.escuela.mappers;

import com.example.escuela.model.AlumnoEntity;
import com.example.escuela.model.request.AlumnoRequest;
import com.example.escuela.model.response.AlumnoResponse;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
    public interface AlumnoMapper {

    @Mappings({
    })
    AlumnoEntity toAlumnoEntity(AlumnoRequest alumnoRequest);

    @Mappings({
    })

    AlumnoResponse toAlumnoResponse(AlumnoEntity alumnoEntity);

    List<AlumnoResponse> toAlumnoResponseList(List<AlumnoEntity> alumnoEntityList);

}
