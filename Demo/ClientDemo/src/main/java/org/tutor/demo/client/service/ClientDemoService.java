package org.tutor.demo.client.service;

import org.tutor.demo.client.entity.SimpleHttpProxyDto;
import org.tutor.demo.client.entity.WebResult;

/**
 * @author Eugene-Forest
 * {@code @date} 2024/11/29
 */
public interface ClientDemoService {

    WebResult proxyHttp(SimpleHttpProxyDto dto);
}
