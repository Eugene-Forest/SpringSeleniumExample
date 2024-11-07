package org.tutor.common.demos.service.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.tutor.common.demos.model.Depart;
import org.tutor.common.demos.service.DemoMysqlService;

import java.util.List;

/**
 * @author Eugene-Forest
 * {@code @date} 2024/11/7
 * {@code @project} TuTorSelenium
 */
@Service
public class DemoMysqlServiceImpl implements DemoMysqlService {

    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Depart> getDepartTable() {
        return List.of();
    }
}
