package org.tutor.botweb.demos;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tutor.botweb.demos.model.DemoUser;
import org.tutor.botweb.demos.model.Depart;
import org.tutor.botweb.demos.service.DemoMysqlService;

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

    @RequestMapping("/user")
    public List<DemoUser> getUserTable(){ return demoMysqlService.getDemoUserTable();}

    @PostMapping("/addUser")
    public boolean insertUser(@RequestBody DemoUser user){return demoMysqlService.insertDemoUser(user);}

    @PostMapping("/updateUser")
    public boolean updateUser(@RequestBody DemoUser user){return demoMysqlService.updateDemoUser(user);}

    @RequestMapping("/deleteUser")
    public boolean deleteUser(int id){return demoMysqlService.deleteDemoUser(id);}
}
