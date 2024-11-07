package org.tutor.common.demos.model;

import lombok.Data;

/**
 * @author Eugene-Forest
 * {@code @date} 2024/11/7
 * {@code @project} TuTorSelenium
 */
@Data
public class Depart {
    //@Data 注解的主要作用是提高代码的简洁，使用这个注解可以省去代码中大量的get()、 set()、 toString()等方法；

    String dept_no;
    String dept_name;
}


