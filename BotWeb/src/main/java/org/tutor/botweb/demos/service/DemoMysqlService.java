package org.tutor.botweb.demos.service;

import org.tutor.botweb.demos.model.DemoUser;
import org.tutor.botweb.demos.model.Depart;

import java.util.List;

/**
 * @author Eugene-Forest
 * {@code @date} 2024/11/7
 * {@code @project} TuTorSelenium
 */
public interface DemoMysqlService {
    List<Depart> getDepartTable();

    boolean insertDemoUser(DemoUser user);

    boolean updateDemoUser(DemoUser user);

    boolean deleteDemoUser(Integer userId);

    List<DemoUser> getDemoUserTable();

    Integer getDemoUserDeposit(Integer userId);

    boolean updateDemoUserDeposit(Integer userId, Integer amount);

    boolean getMoneyFromDeposit(Integer userId, Integer amount);

    boolean addMoneyToDeposit(Integer userId, Integer amount);
}
