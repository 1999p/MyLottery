package cn.xyr.lottery.domain.support.ids;

import cn.xyr.lottery.common.Constants;
import cn.xyr.lottery.domain.support.ids.policy.RandomNumeric;
import cn.xyr.lottery.domain.support.ids.policy.ShortCode;
import cn.xyr.lottery.domain.support.ids.policy.SnowFlake;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: TODO
 * @author: xyr
 * @date: 2023-04-17 18:11
 * @github: https://github.com/1999p
 * @Copyright: https://xxydnx.cn
 */
@Configuration
public class IdContent {

    /**
     * 创建 ID 生成策略对象，属于策略设计模式的使用方式
     *
     * @param snowFlake     雪花算法，长码，大量
     * @param shortCode     日期算法，短码，少量，全局唯一需要自己保证
     * @param randomNumeric 随机算法，短码，大量，全局唯一需要自己保证
     * @return IIdGenerator 实现类
     */
    @Bean
    public Map<Constants.Ids, IIdGenerator> idGeneratorMap(SnowFlake snowFlake, ShortCode shortCode, RandomNumeric randomNumeric) {
        HashMap<Constants.Ids, IIdGenerator> idGeneratorMap = new HashMap<>(8);
        idGeneratorMap.put(Constants.Ids.SnowFlake, snowFlake);
        idGeneratorMap.put(Constants.Ids.ShortCode, shortCode);
        idGeneratorMap.put(Constants.Ids.RandomNumeric, randomNumeric);

        return idGeneratorMap;
    }
}
