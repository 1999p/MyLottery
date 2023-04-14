package cn.xyr.lottery.domain.award.service.goods.impl;

import cn.xyr.lottery.common.Constants;
import cn.xyr.lottery.domain.award.model.req.GoodsReq;
import cn.xyr.lottery.domain.award.model.res.DistributionRes;
import cn.xyr.lottery.domain.award.service.goods.DistributionBase;
import cn.xyr.lottery.domain.award.service.goods.IDistributionGoods;
import org.springframework.stereotype.Component;

/**
 * @description: 优惠券商品
 * @author: xyr
 * @date: 2023-04-10 11:34
 * @Copyright: https://xxydnx.cn
 */
@Component
public class CouponGoods extends DistributionBase implements IDistributionGoods {
    /**
     * 奖品配送接口，奖品类型（1:文字描述、2:兑换码、3:优惠券、4:实物奖品）
     *
     * @param req 物品信息
     * @return 配送结果
     */
    @Override
    public DistributionRes doDistribution(GoodsReq req) {
        // 模拟调用优惠券发放接口
        logger.info("模拟调用优惠券发放接口 uId：{} awardContent：{}", req.getuId(), req.getAwardContent());
        // 更新用户领奖结果
        super.updateUserAwardState(req.getuId(), req.getOrderId(), req.getAwardId(),
                Constants.AwardState.SUCCESS.getCode(), Constants.AwardState.SUCCESS.getInfo());
        return new DistributionRes(req.getuId(), Constants.AwardState.SUCCESS.getCode(), Constants.AwardState.SUCCESS.getInfo());
    }


}
