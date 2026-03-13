package com.deploy.praktikum2crudktp.service;

import com.deploy.praktikum2crudktp.model.dto.KtpAddRequest;
import com.deploy.praktikum2crudktp.model.dto.KtpDto;

import java.util.List;

public interface KtpService {
    KtpDto AddKtp(KtpAddRequest request);
    List<KtpDto> getAllKtp();
    KtpDto getKtpByID(Integer id);
    KtpDto UpdateKtp(Integer id, KtpAddRequest request);
    void DeleteKtp(Integer id);
}
