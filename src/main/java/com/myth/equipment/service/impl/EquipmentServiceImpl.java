package com.myth.equipment.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myth.equipment.entity.Equipment;
import com.myth.equipment.entity.ResultEqui;
import com.myth.equipment.entity.Series;
import com.myth.equipment.mapper.EquipmentMapper;
import com.myth.equipment.service.IEquipmentService;
import com.myth.equipment.service.ISeriesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author corningrey
 * @since 2020-03-27
 */
@Service
public class EquipmentServiceImpl extends ServiceImpl<EquipmentMapper, Equipment> implements IEquipmentService {
    @Resource
    private IEquipmentService equipmentService;
    @Resource
    private ISeriesService seriesService;
    private List<List<Equipment>> resultList;
    private double shuxing = 4014;
    private double morenshuqiang = 211;
    private double duli = 2819;

    public List<ResultEqui> permutateAll() {
        resultList = new ArrayList<>();
        List<List<Equipment>> originalList = new ArrayList<>();

        List<Equipment> list = equipmentService.list();
        originalList.add(list.stream().filter(t -> t.getLocation().equals("1")).collect(Collectors.toList()));
        originalList.add(list.stream().filter(t -> t.getLocation().equals("2")).collect(Collectors.toList()));
        originalList.add(list.stream().filter(t -> t.getLocation().equals("3")).collect(Collectors.toList()));
        originalList.add(list.stream().filter(t -> t.getLocation().equals("4")).collect(Collectors.toList()));
        originalList.add(list.stream().filter(t -> t.getLocation().equals("5")).collect(Collectors.toList()));
        originalList.add(list.stream().filter(t -> t.getLocation().equals("6")).collect(Collectors.toList()));
        originalList.add(list.stream().filter(t -> t.getLocation().equals("7")).collect(Collectors.toList()));
        originalList.add(list.stream().filter(t -> t.getLocation().equals("8")).collect(Collectors.toList()));
        originalList.add(list.stream().filter(t -> t.getLocation().equals("9")).collect(Collectors.toList()));
        originalList.add(list.stream().filter(t -> t.getLocation().equals("10")).collect(Collectors.toList()));
        originalList.add(list.stream().filter(t -> t.getLocation().equals("11")).collect(Collectors.toList()));
        originalList.add(list.stream().filter(t -> t.getLocation().equals("12")).collect(Collectors.toList()));
        permutate(originalList, new ArrayList<>());
        return calculateAndOrder(resultList);

    }

    private void permutate(List<List<Equipment>> originalList, List<Equipment> tempList) {
        int size = originalList.size();
        if (1 == size) {
            for (Equipment e : originalList.get(0)) {
                List<Equipment> elist = new ArrayList<>(tempList);
                elist.add(e);
                resultList.add(elist);
            }
        } else {
            List<Equipment> permu = new ArrayList<>(originalList.get(0));
            List<List<Equipment>> now = new ArrayList<>(originalList);
            now.remove(0);
            for (Equipment s : permu) {
                List<Equipment> elist = new ArrayList<>(tempList);
                elist.add(s);
                permutate(now, elist);
            }
        }
    }

    private List<ResultEqui> calculateAndOrder(List<List<Equipment>> resultList) {
        List<ResultEqui> list = new LinkedList<>();
        List<Series> slist = seriesService.list();
        for (List<Equipment> l : resultList) {
            if (l.stream().filter(t -> t.getLocation().equals("7")).findFirst().map(Equipment::getId).orElse(0) != 8 ||
                    l.stream().filter(t -> t.getLocation().equals("11")).findFirst().map(Equipment::getId).orElse(0) != 41) {
                list.add(calculate(l, slist));
            }
        }
        list = list.stream().sorted(Comparator.comparing(ResultEqui::getScore).reversed()).collect(Collectors.toList());
        return list;
    }

    @SuppressWarnings("DuplicatedCode")
    private ResultEqui calculate(List<Equipment> equis, List<Series> slist) {
        List<List<Equipment>> finalList = new ArrayList<>();
        // putong
        AtomicInteger shuqiang = new AtomicInteger(equis.stream().filter(t -> t.getShuqiang() != null).mapToInt(Equipment::getShuqiang).sum());
        AtomicReference<Double> sangongrate = new AtomicReference<>(equis.stream().filter(t -> t.getSangongRate() != null).mapToDouble(Equipment::getSangongRate).sum());
        AtomicReference<Double> zhongshangrate = new AtomicReference<>(equis.stream().filter(t -> t.getZhongshangRate() != null).mapToDouble(Equipment::getZhongshangRate).sum());
        AtomicReference<Double> huangzirate = new AtomicReference<>(equis.stream().filter(t -> t.getHuangziRate() != null).mapToDouble(Equipment::getHuangziRate).sum());
        AtomicReference<Double> baizirate = new AtomicReference<>(equis.stream().filter(t -> t.getBaiziRate() != null).mapToDouble(Equipment::getBaiziRate).sum());
        AtomicReference<Double> baoshangrate = new AtomicReference<>(equis.stream().filter(t -> t.getBaoshangRate() != null).mapToDouble(Equipment::getBaoshangRate).sum());
        AtomicReference<Double> lizhirate = new AtomicReference<>(equis.stream().filter(t -> t.getLizhiRate() != null).mapToDouble(Equipment::getLizhiRate).sum());
        AtomicReference<Double> skilldrate = new AtomicReference<>(equis.stream().filter(t -> t.getSkillRate() != null).mapToDouble(t -> (1 + t.getSkillRate())).reduce(1, (a, b) -> a * b));
        // series
        Map<String, Long> result1 = equis.stream().collect(Collectors.groupingBy(Equipment::getSeries, Collectors.counting()));
        result1 = result1.entrySet().stream().filter(t -> t.getValue() > 1).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        result1.forEach((key, value) -> slist.stream()
                .filter(s -> s.getCode().equals(key) && Long.valueOf(s.getCount()).equals(value))
                .findFirst().ifPresent(p -> {
                    if (p.getShuqiang() != null)
                        shuqiang.addAndGet(p.getShuqiang());
                    if (p.getSangongRate() != null)
                        sangongrate.updateAndGet(v -> v + p.getSangongRate());
                    if (p.getZhongshangRate() != null)
                        zhongshangrate.updateAndGet(v -> v + p.getZhongshangRate());
                    if (p.getHuangziRate() != null)
                        huangzirate.updateAndGet(v -> v + p.getHuangziRate());
                    if (p.getBaiziRate() != null)
                        baizirate.updateAndGet(v -> v + p.getBaiziRate());
                    if (p.getBaoshangRate() != null)
                        baoshangrate.updateAndGet(v -> v + p.getBaoshangRate());
                    if (p.getLizhiRate() != null)
                        lizhirate.updateAndGet(v -> v + p.getLizhiRate());
                    if (p.getSkillRate() != null)
                        skilldrate.set(skilldrate.get() * (1 + p.getSkillRate()));
                }));
        // 计算最终结果
        double shuqiangxishu = 1.05 + 0.0045 * (morenshuqiang + shuqiang.get());
        double lizhixishu = ((1 + lizhirate.get() + 0.13) * shuxing) / 250 + 1;
        double baiziF = baizirate.get() + 0.31;
        double huangziF = huangzirate.get();
        double sangongF = sangongrate.get();
        double baoshangF = baoshangrate.get();
        double zhongshangF = zhongshangrate.get();
        double skillF = skilldrate.get();

        Double result = lizhixishu * shuqiangxishu * duli
                * (1 + baiziF)
                * (1 + huangziF)
                * (1 + baoshangF)
                * (1 + zhongshangF)
                * (1 + sangongF)
                * skillF;

        ResultEqui re = new ResultEqui();
        re.setShuqiang((int) (morenshuqiang + shuqiang.get()));
        re.setEquiList(equis);
        re.setScore(result);
        re.setBaiziRate(baiziF);
        re.setHuangziRate(huangziF);
        re.setBaoshangRate(baoshangF);
        re.setZhongshangRate(zhongshangF);
        re.setSangongRate(sangongF);
        re.setSkillRate(skillF);
        re.setLizhiRate(lizhirate.get() + 0.13);
        return re;
    }


}
