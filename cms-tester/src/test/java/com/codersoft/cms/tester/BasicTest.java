package com.codersoft.cms.tester;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by wow-lj on 2018/9/2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-context.xml"})
public class BasicTest {

    @Before
    public void before(){
        System.out.println("=====================test begin=====================");
    }

    @After
    public void after(){
        System.out.println("=====================test end=====================");
    }


}
