package com.myth;

import java.util.ArrayList;
import java.util.List;

public class Permutate {

    // 保存生成的排列组合内容
    public List<String> Permutation = new ArrayList<>();

    /**
     * 递归的方式计算排列组合
     *
     * @param list   传入list.size()个集合
     * @param preStr 上一步递归中生成的排列组合
     */
    public void permutation(List<List<String>> list, String preStr) {
        int size = list.size();
        if (1 == size) {
            for (int i = 0; i < list.get(0).size(); i++) {
                Permutation.add(preStr + list.get(0).get(i));
            }
        } else {
            List<String> permu = new ArrayList<>(list.get(0));
            List<List<String>> now = new ArrayList<>(list);
            now.remove(0);
            for (String s : permu) {
                permutation(now, preStr + s);
            }
        }
    }

    public static void main(String[] args) {
        List<List<String>> list = new ArrayList<>();
        List<String> SET1 = new ArrayList<>();
        SET1.add("茄子");
        SET1.add("大白菜");
        List<String> SET2 = new ArrayList<>();
        SET2.add("牛肉");
        SET2.add("羊肉");
        List<String> SET3 = new ArrayList<>();
        SET3.add("桃酥");
        SET3.add("麻饼");
        list.add(SET1);
        list.add(SET2);
        list.add(SET3);

        Permutate permutate = new Permutate();
        permutate.permutation(list, "");
        System.out.println(permutate.Permutation);
    }

}

