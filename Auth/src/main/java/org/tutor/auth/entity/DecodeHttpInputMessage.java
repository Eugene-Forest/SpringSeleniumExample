package org.tutor.auth.entity;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.tutor.auth.enums.RequestEncryptType;
import org.tutor.auth.exception.AESException;
import org.tutor.auth.units.AESUnits;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * @author Eugene-Forest
 * {@code @date} 2024/12/9
 */
public class DecodeHttpInputMessage implements HttpInputMessage {
    private HttpHeaders headers;
    private InputStream body;

    public DecodeHttpInputMessage(HttpInputMessage inputMessage, RequestEncryptType type) throws IOException {
        this.headers = inputMessage.getHeaders();
        String content = IOUtils.toString(inputMessage.getBody(), StandardCharsets.UTF_8);
        try {
            //TODO: for type
            this.body = IOUtils.toInputStream(AESUnits.decryptAES(content));
        } catch (AESException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public InputStream getBody() throws IOException {
        return body;
    }

    @Override
    public HttpHeaders getHeaders() {
        return headers;
    }
}
