package com.myth.equipment.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myth.equipment.entity.Equipment;
import com.myth.equipment.entity.ResultEqui;
import com.myth.equipment.entity.Roles;
import com.myth.equipment.entity.Series;
import com.myth.equipment.mapper.EquipmentMapper;
import com.myth.equipment.service.IEquipmentService;
import com.myth.equipment.service.IRoleEquiService;
import com.myth.equipment.service.IRolesService;
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
    @Resource
    private IRolesService iRolesService;
    @Resource
    private IRoleEquiService roleEquiService;
    private List<List<Equipment>> resultList;
    private Roles roles;

    public List<ResultEqui> permutateAll() {
        resultList = new ArrayList<>();
        List<List<Equipment>> originalList = new ArrayList<>();
        roles = iRolesService.getById(1);
        List<Equipment> list = roleEquiService.selectEquiListByRoleId(1);
        // 取出各个位置的数据分别放入到12个集合中
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

        // 列出所有符合条件的排列组合
        permutate(originalList, new ArrayList<>());
        // 特殊标记位只能同时有一个
        resultList = resultList.stream()
                .filter(t -> t.stream().filter(p -> "1".equals(p.getShenhua())).count() <= 1)
                .collect(Collectors.toList());
        // 计算，排序
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
            List<Equipment> perms = new ArrayList<>(originalList.get(0));
            List<List<Equipment>> now = new ArrayList<>(originalList);
            now.remove(0);
            for (Equipment s : perms) {
                List<Equipment> enlist = new ArrayList<>(tempList);
                enlist.add(s);
                permutate(now, enlist);
            }
        }
    }

    private List<ResultEqui> calculateAndOrder(List<List<Equipment>> resultList) {
        List<ResultEqui> list = new LinkedList<>();

        List<Series> seriesList = seriesService.list();
        ResultEqui re = new ResultEqui();
        for (List<Equipment> l : resultList) {
            list.add(calculate(l, seriesList, re));
        }
        list = list.stream().sorted(Comparator.comparing(ResultEqui::getScore).reversed()).collect(Collectors.toList());
        return list;
    }

    @SuppressWarnings("DuplicatedCode")
    private ResultEqui calculate(List<Equipment> equis, List<Series> slist, ResultEqui re) {
        AtomicInteger shuqiang = new AtomicInteger(equis.stream().filter(t -> t.getShuqiang() != null).mapToInt(Equipment::getShuqiang).sum());
        AtomicReference<Double> sangongRate = new AtomicReference<>(equis.stream().filter(t -> t.getSangongRate() != null).mapToDouble(Equipment::getSangongRate).sum());
        AtomicReference<Double> zhongshangRate = new AtomicReference<>(equis.stream().filter(t -> t.getZhongshangRate() != null).mapToDouble(Equipment::getZhongshangRate).sum());
        AtomicReference<Double> huangziRate = new AtomicReference<>(equis.stream().filter(t -> t.getHuangziRate() != null).mapToDouble(Equipment::getHuangziRate).sum());
        AtomicReference<Double> baiziRate = new AtomicReference<>(equis.stream().filter(t -> t.getBaiziRate() != null).mapToDouble(Equipment::getBaiziRate).sum());
        AtomicReference<Double> baoshangRate = new AtomicReference<>(equis.stream().filter(t -> t.getBaoshangRate() != null).mapToDouble(Equipment::getBaoshangRate).sum());
        AtomicReference<Double> lizhiRate = new AtomicReference<>(equis.stream().filter(t -> t.getLizhiRate() != null).mapToDouble(Equipment::getLizhiRate).sum());
        AtomicReference<Double> skillRate = new AtomicReference<>(equis.stream().filter(t -> t.getSkillRate() != null).mapToDouble(t -> (1 + t.getSkillRate())).reduce(1, (a, b) -> a * b));
        // series
        Map<String, Long> result1 = equis.stream().collect(Collectors.groupingBy(Equipment::getSeries, Collectors.counting()));
        result1 = result1.entrySet().stream().filter(t -> t.getValue() > 1).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        result1.forEach((key, value) -> slist.stream()
                .filter(s -> s.getCode().equals(key) && Long.valueOf(s.getCount()).equals(value))
                .findFirst().ifPresent(p -> {
                    if (p.getShuqiang() != null)
                        shuqiang.addAndGet(p.getShuqiang());
                    if (p.getSangongRate() != null)
                        sangongRate.updateAndGet(v -> v + p.getSangongRate());
                    if (p.getZhongshangRate() != null)
                        zhongshangRate.updateAndGet(v -> v + p.getZhongshangRate());
                    if (p.getHuangziRate() != null)
                        huangziRate.updateAndGet(v -> v + p.getHuangziRate());
                    if (p.getBaiziRate() != null)
                        baiziRate.updateAndGet(v -> v + p.getBaiziRate());
                    if (p.getBaoshangRate() != null)
                        baoshangRate.updateAndGet(v -> v + p.getBaoshangRate());
                    if (p.getLizhiRate() != null)
                        lizhiRate.updateAndGet(v -> v + p.getLizhiRate());
                    if (p.getSkillRate() != null)
                        skillRate.set(skillRate.get() * (1 + p.getSkillRate()));
                }));
        // 模拟实体
        Equipment e = Equipment.builder().shuqiang(shuqiang.get()).sangongRate(sangongRate.get()).baiziRate(baiziRate.get()).huangziRate(huangziRate.get())
                .baoshangRate(baoshangRate.get()).zhongshangRate(zhongshangRate.get()).lizhiRate(lizhiRate.get()).skillRate(skillRate.get() - 1).build();
        // 计算最终结果
        re = new ResultEqui(roles, e);
        re.setEquiList(equis);
        return re;
    }


}
