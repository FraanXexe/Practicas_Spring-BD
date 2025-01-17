package com.example.escuela.mappers;

import com.example.escuela.model.MaestroEntity;
import com.example.escuela.model.request.MaestroRequest;
import com.example.escuela.model.response.MaestroResponse;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
    public interface MaestroMapper {
        @Mappings({
                // En caso de ser diferentes aqui es donde mapeas el nombre de cada uno
        })
        MaestroEntity toMaestroEntity(MaestroRequest maestroRequest);

        @Mappings({

        })
        MaestroResponse toMaestroResponse(MaestroEntity maestroEntity);

        List<MaestroResponse> toMaaestroResponseList (List<MaestroEntity> maestroEntityList);

    }
