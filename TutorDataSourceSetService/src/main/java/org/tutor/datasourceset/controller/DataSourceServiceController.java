package org.tutor.datasourceset.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.tutor.auth.anno.EncryptRequest;
import org.tutor.auth.entity.WebResult;
import org.tutor.auth.enums.RequestEncryptType;
import org.tutor.auth.units.CryptoUnits;
import org.tutor.auth.units.SignKeyUnits;
import org.tutor.datasourceset.dto.Account;
import org.tutor.datasourceset.dto.DataSourceDto;
import org.tutor.datasourceset.service.DataSourceService;
import org.tutor.redis.RedisUtil;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Eugene-Forest
 * {@code @date} 2024/11/23
 */
@RequestMapping("/datasource")
@RestController
@EncryptRequest
public class DataSourceServiceController {

    @Autowired
    private DataSourceService dataSourceService;

    @Autowired
    private RedisUtil redisUtil;

    @EncryptRequest(encryptType = RequestEncryptType.RSA)
    @PostMapping("/login")
    public WebResult<String> login(@RequestBody Account account) {
        if(account.getPassword() == null || account.getPassword().isEmpty()) {
            return WebResult.error();
        }
        if(account.getName() == null || account.getName().isEmpty()) {
            return WebResult.error();
        }
        if(account.getPassword().equals("admin") && account.getName().equals("admin")) {
            String password = CryptoUnits.generatePassword();
            //how to Save password
            redisUtil.set("password", password);
            redisUtil.expire("password", 1, TimeUnit.MINUTES);

            String enCodePassword = SignKeyUnits.defaultEncryptMessage(password);
            return WebResult.success(enCodePassword);
        }
        return WebResult.error();
    }

    @GetMapping("/getDataSourceSets")
    public List<DataSourceDto> getDataSourceSets(){
        return dataSourceService.getDataSources();
    }
}
