package com.example.escuela.mappers;

import com.example.escuela.constants.MensajesError;
import com.example.escuela.exceptions.ExcPersonalizada;
import com.example.escuela.model.AlumnoEntity;

import com.example.escuela.model.request.AlumnoRequest;
import com.example.escuela.model.response.AlumnoResponse;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;  // Agregamos este import
import java.time.format.DateTimeFormatter;
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
