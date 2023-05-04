package cn.xyr.lottery.test.application;

import cn.xyr.lottery.application.process.IActivityProcess;
import cn.xyr.lottery.application.process.req.DrawProcessReq;
import cn.xyr.lottery.application.process.res.DrawProcessResult;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @description: TODO
 * @author: xyr
 * @date: 2023-05-03 22:04
 * @github: https://github.com/1999p
 * @Copyright: https://xxydnx.cn
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivityProcessTest {

    private Logger logger = LoggerFactory.getLogger(ActivityProcessTest.class);

    @Resource
    private IActivityProcess activityProcess;

    @Test
    public void test_doDrawProcess(){
        DrawProcessReq req = new DrawProcessReq();
        req.setuId("xyr");
        req.setActivityId(100001L);
        DrawProcessResult drawProcessResult = activityProcess.doDrawProcessResult(req);

        logger.info("请求入参：{}", JSON.toJSONString(req));
        logger.info("测试结果：{}", JSON.toJSONString(drawProcessResult));
    }
}
