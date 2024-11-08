package org.tutor.botweb.demos.dao;

import org.apache.ibatis.annotations.Mapper;
import org.tutor.botweb.demos.model.DemoUser;
import org.tutor.botweb.demos.model.Depart;

import java.util.List;

/**
 * @author qaz22
 * {@code @date} 2024/11/7
 * {@code @project} tutor-selenium
 */
@Mapper
public interface DemoMysqlServiceMapper {

    List<Depart> queryDepart();

    List<DemoUser> queryDemoUser();

    boolean insertDemoUser(DemoUser demoUser);

    boolean updateDemoUser(DemoUser demoUser);

    boolean deleteDemoUser(Integer id);

    boolean updateDemoUserDeposit(Integer id, Integer amount);

    Integer getDemoUserDeposit(Integer id);
}
