package org.tutor.botweb.demos.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.tutor.botweb.demos.dao.DemoMysqlServiceMapper;
import org.tutor.botweb.demos.model.DemoUser;
import org.tutor.botweb.demos.model.Depart;
import org.tutor.botweb.demos.service.DemoMysqlService;
import org.tutor.common.unit.TutorTransactionManager;
import org.tutor.redis.RedisUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
    private RedisUtil tutorRedisUtil;

    @Autowired
    private DataSourceTransactionManager dataSourceTransactionManager;

    @Override
    public Map<String, String> getDepartTable() {
        String redisKey = "DepartTable";
        if (tutorRedisUtil.hasKey(redisKey)) {
            log.warn("Redis Key " + redisKey + " is exist");
            return tutorRedisUtil.hGetAll(redisKey);
        }
        List<Depart> departList = demoMysqlServiceMapper.queryDepart();
        int redisDataBaseName = tutorRedisUtil.getDataBase();

        log.warn("Redis DataBase is " + redisDataBaseName);
        Map<String, String> map = new HashMap<>();

        departList.forEach(depart -> {
            map.put(depart.getDept_no(), depart.getDept_name());
//            if(!tutorRedisUtil.hasKey(depart.getDept_no())){
//                tutorRedisUtil.set(depart.getDept_no(), depart.toString());
//                tutorRedisUtil.expire(depart.getDept_no(), 60, TimeUnit.SECONDS);
//                log.warn("Add Redis Key " + depart.getDept_no());
//            } else {
//                log.warn("Redis Key " + depart.getDept_no() + " is exist");
//            }
        });
        tutorRedisUtil.hPutAll(redisKey, map);
        log.warn("Add Redis Key " + redisKey);
        tutorRedisUtil.expire(redisKey, 1, TimeUnit.MINUTES);
        return map;
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

            if (targetDeposit != 0) {
                TutorTransactionManager.rollback(transactionStatus, dataSourceTransactionManager);
                log.warn("账户销毁失败！ 余额：" + targetDeposit);
                return false;
            } else {
                // 提交事务
                TutorTransactionManager.commit(transactionStatus, dataSourceTransactionManager);
                log.warn("账户销毁成功！");
                return status;
            }
        } catch (Exception e) {
            e.printStackTrace();
            TutorTransactionManager.rollback(transactionStatus, dataSourceTransactionManager);
        }
        return false;
    }

    @Override
    public List<DemoUser> getDemoUserTable() {
        return demoMysqlServiceMapper.queryDemoUser();
    }

    @Override
    public Integer getDemoUserDeposit(Integer userId) {
        return demoMysqlServiceMapper.getDemoUserDeposit(userId);
    }

    @Override
    public boolean updateDemoUserDeposit(Integer userId, Integer amount) {
        return demoMysqlServiceMapper.updateDemoUserDeposit(userId, amount);
    }

    @Transactional
    @Override
    public boolean getMoneyFromDeposit(Integer userId, Integer amount) {
        try {
            Integer myDeposit = getDemoUserDeposit(userId);
            myDeposit -= amount;
            if (myDeposit < 0) {
                throw new Exception("余额不足");
            }
            boolean status = updateDemoUserDeposit(userId, myDeposit);
            if (userId == 3) {
                throw new Exception("userId is 3, rollback.");
            }
            return status;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean addMoneyToDeposit(Integer userId, Integer amount) {
        Integer myDeposit = getDemoUserDeposit(userId);
        myDeposit += amount;
        return demoMysqlServiceMapper.updateDemoUserDeposit(userId, myDeposit);
    }
}
