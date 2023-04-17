package cn.xyr.lottery.domain.support.ids.policy;

import cn.xyr.lottery.domain.support.ids.IIdGenerator;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

/**
 * @description: TODO 工具类生成 org.apache.commons.lang3.RandomStringUtils
 * @author: xyr
 * @date: 2023-04-17 18:08
 * @github: https://github.com/1999p
 * @Copyright: https://xxydnx.cn
 */
@Component
public class RandomNumeric implements IIdGenerator {
    @Override
    public long nextId() {
        return Long.parseLong(RandomStringUtils.randomNumeric(11));
    }
}
