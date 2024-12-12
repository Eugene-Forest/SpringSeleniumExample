package org.tutor.demo.client.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.tutor.auth.units.CryptoUnits;
import org.tutor.demo.client.entity.SimpleHttpProxyDto;
import org.tutor.demo.client.entity.WebResult;
import org.tutor.demo.client.service.ClientDemoService;
import org.tutor.demo.client.units.HttpClientUtils;

/**
 * @author Eugene-Forest
 * {@code @date} 2024/11/29
 */
@Service
@Slf4j
public class ClientDemoServiceImpl implements ClientDemoService {


    @Override
    public WebResult proxyHttp(SimpleHttpProxyDto dto) {
        if(dto.getRequestType().equals("get")){
            try {
                String res = HttpClientUtils.get(dto.getUrl());
                log.info("请求原文：");
                log.info(res);
                String result = CryptoUnits.defaultDecrypt(res);
                log.info("解密：");
                log.info(result);
                return WebResult.success(result);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return WebResult.failure();
    }
}
