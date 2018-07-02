package xin.purelove.cms.common.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import xin.purelove.cms.common.EventConsumerService;

import javax.annotation.Resource;

/**
 * Created by wow-lj on 2018/1/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:dip-context.xml" })
public class MainTest {

    @Resource(name="eventConsumerService")
    EventConsumerService eventConsumerService;

    @Test
    public void start(){
        System.out.println("启动了");
        eventConsumerService.consumer("consumer");
    }

}
