package org.tutor.datasourceset.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tutor.auth.anno.EncryptRequest;
import org.tutor.datasourceset.dto.DataSourceDto;
import org.tutor.datasourceset.service.DataSourceService;
import org.tutor.auth.anno.RasCheck;

import java.util.List;

/**
 * @author Eugene-Forest
 * {@code @date} 2024/11/23
 */
@RequestMapping("/datasource")
@RestController
@RasCheck
@EncryptRequest
public class DataSourceServiceController {

    @Autowired
    private DataSourceService dataSourceService;

    @GetMapping("/getDataSourceSets")
    public List<DataSourceDto> getDataSourceSets(){
        return dataSourceService.getDataSources();
    }
}
