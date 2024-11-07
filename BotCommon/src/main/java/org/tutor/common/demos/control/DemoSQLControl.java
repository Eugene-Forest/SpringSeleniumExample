package org.tutor.common.demos.control;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tutor.common.demos.model.Depart;
import org.tutor.common.demos.service.DemoMysqlService;

import java.util.List;

/**
 * @author Eugene-Forest
 * {@code @date} 2024/11/7
 * {@code @project} TuTorSelenium
 */
@RestController
@RequestMapping("/demoSql")
@Slf4j
public class DemoSQLControl {

    @Autowired
    private DemoMysqlService demoMysqlService;

    @RequestMapping("/depart")
    public List<Depart> getDepartTable(){
        return demoMysqlService.getDepartTable();
    }
}
