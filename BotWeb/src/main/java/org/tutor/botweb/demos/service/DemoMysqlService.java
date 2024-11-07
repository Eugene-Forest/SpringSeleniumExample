package org.tutor.botweb.demos.service;

import org.tutor.botweb.demos.model.Depart;

import java.util.List;

/**
 * @author Eugene-Forest
 * {@code @date} 2024/11/7
 * {@code @project} TuTorSelenium
 */
public interface DemoMysqlService {
    List<Depart> getDepartTable();
}
