package cn.xyr.lottery.test;

import cn.xyr.lottery.infrastructure.dao.IActivityDao;
import cn.xyr.lottery.infrastructure.po.Activity;
import cn.xyr.lottery.domain.strategy.model.req.DrawReq;
import cn.xyr.lottery.domain.strategy.service.draw.IDrawExec;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringRunnerTest {

    private Logger logger = LoggerFactory.getLogger(SpringRunnerTest.class);

    @Resource
    private IActivityDao activityDao;

    @Resource
    private IDrawExec drawExec;

    @Test
    public void test_drawExec() {
        drawExec.doDrawExec(new DrawReq("小邢", 10001L));
        drawExec.doDrawExec(new DrawReq("JJ", 10001L));
        drawExec.doDrawExec(new DrawReq("莉莉白", 10001L));
        drawExec.doDrawExec(new DrawReq("彗星", 10001L));
        drawExec.doDrawExec(new DrawReq("对角巷", 10001L));
    }

    @Test
    public void test_insert() {
        Activity activity = new Activity();
        activity.setActivityId(100001L);
        activity.setActivityName("测试活动");
        activity.setActivityDesc("仅用于插入数据测试");
        activity.setBeginDateTime(new Date());
        activity.setEndDateTime(new Date());
        activity.setStockCount(100);
        activity.setTakeCount(10);
        activity.setState(0);
        activity.setCreator("xyr");
        activityDao.insert(activity);
    }

    @Test
    public void test_select() {
        Activity activity = activityDao.queryActivityById(100001L);
        logger.info("测试结果：{}", JSON.toJSONString(activity));
    }

}