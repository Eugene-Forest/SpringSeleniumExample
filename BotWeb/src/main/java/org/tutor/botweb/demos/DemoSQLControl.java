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
import java.util.Map;

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
    public Map<String,String> getDepartTable(){
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

    @RequestMapping("/updateDeposit")
    public boolean updateUserDeposit(int id, int amount){
        return demoMysqlService.updateDemoUserDeposit(id,amount);
    }

    @RequestMapping("/getDeposit")
    public Integer getUserDeposit(int id){
        return demoMysqlService.getDemoUserDeposit(id);
    }

    @RequestMapping("/getMoney")
    public boolean getMoneyFromDeposit(int id, int amount){
        return demoMysqlService.getMoneyFromDeposit(id,amount);
    }

    @RequestMapping("/addMoney")
    public boolean addMoneyFromDeposit(int id, int amount){
        return demoMysqlService.addMoneyToDeposit(id,amount);
    }
}
