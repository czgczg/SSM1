package com.cskaoyan.test;

import com.cskaoyan.service.Impl.optionServiceImpl;
import com.cskaoyan.service.optionService;
import com.cskaoyan.vo.Listone;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class listTest {

    @Autowired
    optionService optionService;


    @Test
    public void test() {

        List<Listone> nation = optionService.getNation();

        for (Listone listone : nation) {

            System.out.println(listone);

        }


    }


}
