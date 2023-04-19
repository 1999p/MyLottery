package cn.xyr.lottery.test.dao;

import cn.xyr.lottery.infrastructure.dao.IActivityDao;
import cn.xyr.lottery.infrastructure.po.Activity;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @description: TODO 测试活动表 DAO
 * @author: xyr
 * @date: 2023-04-18 23:10
 * @github: https://github.com/1999p
 * @Copyright: https://xxydnx.cn
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivityDaoTest {
    private Logger logger = LoggerFactory.getLogger(ActivityDaoTest.class);

    @Resource
    private IActivityDao activityDao;

    @Test
    public void test_queryActivityById() {
        Activity activity = activityDao.queryActivityById(100001L);
        logger.info("测试结果：{}", JSON.toJSONString(activity));
    }
}
