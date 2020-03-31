package com.myth;

import com.myth.equipment.entity.Equipment;
import com.myth.equipment.entity.ResultEqui;
import com.myth.equipment.service.IEquipmentService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
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
    public void insert() {
        List<Equipment> list = new ArrayList<>();
        list.add(Equipment.builder().name("A3").location("1").build());
        list.add(Equipment.builder().name("B3").location("2").build());
        list.add(Equipment.builder().name("C3").location("3").build());
        list.add(Equipment.builder().name("D3").location("4").build());
        list.add(Equipment.builder().name("E3").location("5").build());
        list.add(Equipment.builder().name("F3").location("6").build());
        list.add(Equipment.builder().name("G3").location("7").build());
        list.add(Equipment.builder().name("H3").location("8").build());
        list.add(Equipment.builder().name("I3").location("9").build());
        list.add(Equipment.builder().name("J3").location("10").build());
        list.add(Equipment.builder().name("K3").location("11").build());
        list.add(Equipment.builder().name("L3").location("12").build());
        equipmentService.saveBatch(list);
    }

    @Test
    public void test() {
        List<ResultEqui> testList = equipmentService.permutateAll();
        System.out.println(testList.size());
    }


}
