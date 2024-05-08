package com.agenceVoyage.backend.mapper;


import com.agenceVoyage.backend.dto.FacilityDto;
import com.agenceVoyage.backend.model.Facility;
import org.apache.catalina.util.CharsetMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface FacilityMapper {

    FacilityMapper INSTANCE = Mappers.getMapper(FacilityMapper.class);
    FacilityDto facilityToFacilityDto(Facility facility);
    Facility facilityDtoToFacility(FacilityDto facilityDto);
}
