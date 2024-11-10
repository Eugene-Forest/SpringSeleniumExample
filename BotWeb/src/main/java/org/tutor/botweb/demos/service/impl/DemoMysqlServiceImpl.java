package org.tutor.botweb.demos.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.tutor.botweb.demos.dao.DemoMysqlServiceMapper;
import org.tutor.botweb.demos.model.DemoUser;
import org.tutor.botweb.demos.model.Depart;
import org.tutor.botweb.demos.service.DemoMysqlService;
import org.tutor.common.unit.TutorTransactionManager;

import java.util.List;

/**
 * @author Eugene-Forest
 * {@code @date} 2024/11/7
 * {@code @project} TuTorSelenium
 */
@Slf4j
@Service
public class DemoMysqlServiceImpl implements DemoMysqlService {

    @Autowired
    private DemoMysqlServiceMapper demoMysqlServiceMapper;

    @Autowired
    private DataSourceTransactionManager dataSourceTransactionManager;

    @Override
    public List<Depart> getDepartTable() {
        List<Depart> departList = demoMysqlServiceMapper.queryDepart();
        return departList;
    }

    @Override
    public boolean insertDemoUser(DemoUser user) {
        return demoMysqlServiceMapper.insertDemoUser(user);
    }

    @Override
    public boolean updateDemoUser(DemoUser user) {
        return demoMysqlServiceMapper.updateDemoUser(user);
    }

    @Override
    public boolean deleteDemoUser(Integer userId) {
        TransactionStatus transactionStatus = TutorTransactionManager.getTransaction(dataSourceTransactionManager);
        try {

            int targetDeposit = demoMysqlServiceMapper.getDemoUserDeposit(userId);

            // 故意先删除再检查
            boolean status = demoMysqlServiceMapper.deleteDemoUser(userId);

            if(targetDeposit != 0){
                TutorTransactionManager.rollback(transactionStatus, dataSourceTransactionManager);
                log.warn("账户销毁失败！ 余额：" + targetDeposit);
                return false;
            } else {
                // 提交事务
                TutorTransactionManager.commit(transactionStatus, dataSourceTransactionManager);
                log.warn("账户销毁成功！");
                return status;
            }
        }catch (Exception e) {
            e.printStackTrace();
            TutorTransactionManager.rollback(transactionStatus, dataSourceTransactionManager);
        }
        return false;
    }

    @Override
    public List<DemoUser> getDemoUserTable() {
        return  demoMysqlServiceMapper.queryDemoUser();
    }

    @Override
    public Integer getDemoUserDeposit(Integer userId) {
        return demoMysqlServiceMapper.getDemoUserDeposit(userId);
    }

    @Override
    public boolean updateDemoUserDeposit(Integer userId, Integer amount) {
        return demoMysqlServiceMapper.updateDemoUserDeposit(userId,amount);
    }

    @Transactional
    @Override
    public boolean getMoneyFromDeposit(Integer userId, Integer amount) {
        try {
            Integer myDeposit = getDemoUserDeposit(userId);
            myDeposit -= amount;
            if(myDeposit < 0) {
                throw new Exception("余额不足");
            }
            boolean status = updateDemoUserDeposit(userId, myDeposit);
            if(userId == 3){
                throw new Exception("userId is 3, rollback.");
            }
            return status;
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean addMoneyToDeposit(Integer userId, Integer amount) {
        Integer myDeposit = getDemoUserDeposit(userId);
        myDeposit += amount;
        return demoMysqlServiceMapper.updateDemoUserDeposit(userId,myDeposit);
    }
}
