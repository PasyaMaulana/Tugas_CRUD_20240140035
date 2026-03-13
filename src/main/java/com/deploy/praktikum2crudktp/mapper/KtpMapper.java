package com.deploy.praktikum2crudktp.mapper;

import com.deploy.praktikum2crudktp.model.dto.KtpDto;
import com.deploy.praktikum2crudktp.model.entity.Ktp;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper

public interface KtpMapper {
    KtpMapper MAPPER = Mappers.getMapper(KtpMapper.class);
    KtpDto toKtpDtoData(Ktp ktp);
}
