package com.example.batis;

import com.example.batis.service.RegionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@MybatisTest
@Import(RegionService.class)
@Slf4j
public class CacheTest {
    private SqlSessionFactory factory;
    @Autowired
    private RegionService service;

    @Test
    public void get() {

        getAndPrint(1L);
        getAndPrint(1L);
        getAndPrint(1L);
    }

    private void getAndPrint(Long id) {
        log.info("region found: {}", service.findById(id));
    }
}