package cn.xyr.lottery.domain.rule.service.engine;

import cn.xyr.lottery.domain.rule.service.logic.LogicFilter;
import cn.xyr.lottery.domain.rule.service.logic.impl.UserAgeFilter;
import cn.xyr.lottery.domain.rule.service.logic.impl.UserGenderFilter;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description:  规则配置
 * @author: xyr
 * @date: 2023-05-04 21:15
 * @github: https://github.com/1999p
 * @Copyright: https://xxydnx.cn
 */
public class EngineConfig {

    protected static Map<String, LogicFilter> logicFilterMap = new ConcurrentHashMap<>();

    @Resource
    private UserAgeFilter userAgeFilter;

    @Resource
    private UserGenderFilter userGenderFilter;

    @PostConstruct
    public void init(){
        //用户年龄过滤器
        logicFilterMap.put("userAge",userAgeFilter);
        //用户性别过滤器
        logicFilterMap.put("userGender",userGenderFilter);
    }
}
