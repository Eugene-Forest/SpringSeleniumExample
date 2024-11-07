package org.tutor.botweb.demos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tutor.botweb.demos.dao.DemoMysqlServiceMapper;
import org.tutor.botweb.demos.model.Depart;
import org.tutor.botweb.demos.service.DemoMysqlService;

import java.util.List;

/**
 * @author Eugene-Forest
 * {@code @date} 2024/11/7
 * {@code @project} TuTorSelenium
 */
@Service
public class DemoMysqlServiceImpl implements DemoMysqlService {

    @Autowired
    private DemoMysqlServiceMapper demoMysqlServiceMapper;

    @Override
    public List<Depart> getDepartTable() {
        List<Depart> departList = demoMysqlServiceMapper.queryDepart();
        return departList;
    }
}
