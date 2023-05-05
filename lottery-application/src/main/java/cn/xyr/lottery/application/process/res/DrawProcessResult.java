package cn.xyr.lottery.application.process.res;

import cn.xyr.lottery.common.Result;
import cn.xyr.lottery.domain.strategy.model.vo.DrawAwardVO;

/**
 * @description: 抽奖结果
 * @author: xyr
 * @date: 2023-05-03 22:18
 * @github: https://github.com/1999p
 * @Copyright: https://xxydnx.cn
 */
public class DrawProcessResult extends Result {

    private DrawAwardVO drawAwardInfo;

    public DrawProcessResult(String code,String info) {
        super(code,info);
    }

    public DrawProcessResult(String code, String info, DrawAwardVO drawAwardInfo) {
        super(code, info);
        this.drawAwardInfo = drawAwardInfo;
    }

    public DrawAwardVO getDrawAwardInfo() {
        return drawAwardInfo;
    }

    public void setDrawAwardInfo(DrawAwardVO drawAwardInfo) {
        this.drawAwardInfo = drawAwardInfo;
    }
}
