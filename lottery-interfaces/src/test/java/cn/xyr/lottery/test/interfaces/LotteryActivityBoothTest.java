package cn.xyr.lottery.test.interfaces;

import cn.xyr.lottery.rpc.ILotteryActivityBooth;
import cn.xyr.lottery.rpc.ILotteryActivityBooth;
import cn.xyr.lottery.rpc.req.DrawReq;
import cn.xyr.lottery.rpc.req.QuantificationDrawReq;
import cn.xyr.lottery.rpc.res.DrawRes;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @description: 抽奖活动展台测试
 * @author: xyr
 * @date: 2023-05-06 15:19
 * @github: https://github.com/1999p
 * @Copyright: https://xxydnx.cn
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LotteryActivityBoothTest {
    private Logger logger = LoggerFactory.getLogger(LotteryActivityBoothTest.class);

    @Resource
    private ILotteryActivityBooth lotteryActivityBooth;

    @Test
    public void test_doDraw() {
        DrawReq drawReq = new DrawReq();
        drawReq.setuId("xiaofuge");
        drawReq.setActivityId(100001L);
        DrawRes drawRes = lotteryActivityBooth.doDraw(drawReq);
        logger.info("请求参数：{}", JSON.toJSONString(drawReq));
        logger.info("测试结果：{}", JSON.toJSONString(drawRes));

    }
    @Test
    public void test_doQuantificationDraw() {
        // 创建量化人群抽奖请求x对象
        QuantificationDrawReq req = new QuantificationDrawReq();
        req.setuId("王一分");
        req.setTreeId(2110081902L);
        req.setValMap(new HashMap<String, Object>() {{
            put("gender", "man");
            put("age", "8");
        }});

        DrawRes drawRes = lotteryActivityBooth.doQuantificationDraw(req);
        logger.info("请求参数：{}", JSON.toJSONString(req));
        logger.info("测试结果：{}", JSON.toJSONString(drawRes));

    }
}
