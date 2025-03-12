package com.fulo.targets.mappers;

import com.fulo.targets.domain.dto.HitListDto;
import com.fulo.targets.domain.entities.HitList;


public interface HitListMapper {

    HitList fromDto(HitListDto hitListDto);

    HitListDto toDto(HitList hitList);
}
