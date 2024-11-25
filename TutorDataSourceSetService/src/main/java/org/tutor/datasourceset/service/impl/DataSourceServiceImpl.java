package org.tutor.datasourceset.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tutor.datasourceset.dao.DataSourceMapper;
import org.tutor.datasourceset.dto.DataSourceDto;
import org.tutor.datasourceset.service.DataSourceService;

import java.util.List;

/**
 * @author Eugene-Forest
 * {@code @date} 2024/11/23
 */
@Service
public class DataSourceServiceImpl implements DataSourceService {

    @Autowired
    private DataSourceMapper dataSourceMapper;

    @Override
    public List<DataSourceDto> getDataSources() {
        List<DataSourceDto> res = dataSourceMapper.selectAllDataSources();
        return res;
    }
}
