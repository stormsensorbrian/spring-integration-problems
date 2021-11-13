package com.example.springintegrationproblems;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.springintegrationproblems.service.UppercaseService;

@SpringBootTest
class TestUppercaseService {

    @Autowired
    UppercaseService service;
    
    @Test
    void testMakeItBig() {
        var in = "foo";
        
        assertThat(service.makeItBig(in)).isEqualTo("FOO");
    }
    
    @Test
    void testMakeItSmall() {
        var in = "FOO";
        
        assertThat(service.makeItSmall(in)).isEqualTo("foo");
    }
}
