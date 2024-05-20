package com.demo.unit.adaptor;

import com.demo.unit.adaptor.dto.RestUserPost;
import com.demo.unit.adaptor.impl.RestAdaptorImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;

@RestClientTest(RestAdaptorImpl.class)
public class RestAdaptorTests {

    @Autowired
    MockRestServiceServer server;

    @Autowired
    RestAdaptor adaptor;

    @Test
    void testRestAdaptor() throws IOException {
        Long id = 1l;
        ClassPathResource cpr = new ClassPathResource("adaptor/RestUserPost.json");
        byte[] bdata = FileCopyUtils.copyToByteArray(cpr.getInputStream());

        server
                .expect(MockRestRequestMatchers.requestTo("https://jsonplaceholder.typicode.com/posts/" + id))
                .andRespond(MockRestResponseCreators.withSuccess(bdata, MediaType.APPLICATION_JSON));

        RestUserPost sut = adaptor.findPostById(1l);

        Assertions.assertEquals(1, sut.getId());
        Assertions.assertEquals("dummy post title", sut.getTitle());
    }
}
