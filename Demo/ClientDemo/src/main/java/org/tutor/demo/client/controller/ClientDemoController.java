package org.tutor.demo.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tutor.demo.client.entity.SimpleHttpProxyDto;
import org.tutor.demo.client.entity.WebResult;
import org.tutor.demo.client.service.ClientDemoService;

/**
 * @author Eugene-Forest
 * {@code @date} 2024/11/29
 */
@RequestMapping("/client")
@RestController
public class ClientDemoController {

    @Autowired
    private ClientDemoService clientDemoService;

    @PostMapping("/post")
    public WebResult proxyPostSignHttp(@RequestBody SimpleHttpProxyDto dto) {
        return clientDemoService.proxyHttp(dto);
    }
}
