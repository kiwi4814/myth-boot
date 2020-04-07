package com.myth;

import com.myth.equipment.entity.ResultEqui;
import com.myth.equipment.service.IEquipmentService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author thyme
 * @ClassName MybatisTest
 * @Description TODO
 * @Date 2019/12/10 21:58
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class EquiTest {

    @Autowired
    private IEquipmentService equipmentService;

    @Test
    public void test() {
        List<ResultEqui> testList = equipmentService.permutateAll();
        System.out.println(testList.size());

        // 1：B
        
        // 2：A
        //
        //
        //
    }


}
