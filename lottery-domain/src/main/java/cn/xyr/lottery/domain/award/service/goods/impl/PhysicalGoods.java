package cn.xyr.lottery.domain.award.service.goods.impl;

import cn.xyr.lottery.common.Constants;
import cn.xyr.lottery.domain.award.model.req.GoodsReq;
import cn.xyr.lottery.domain.award.model.res.DistributionRes;
import cn.xyr.lottery.domain.award.service.goods.DistributionBase;
import cn.xyr.lottery.domain.award.service.goods.IDistributionGoods;

/**
 * @description: 实物发货类商品
 * @author: xyr
 * @date: 2023-04-10 15:31
 * @Copyright: https://xxydnx.cn
 */
public class PhysicalGoods extends DistributionBase implements IDistributionGoods {
    /**
     * 奖品配送接口，奖品类型（1:文字描述、2:兑换码、3:优惠券、4:实物奖品）
     *
     * @param req 物品信息
     * @return 配送结果
     */
    @Override
    public DistributionRes doDistribution(GoodsReq req) {
        super.updateUserAwardState(req.getuId(),req.getOrderId(),req.getAwardId(), Constants.AwardState.SUCCESS.getCode(), Constants.AwardState.SUCCESS.getInfo());

        return new DistributionRes(req.getuId(),Constants.AwardState.SUCCESS.getCode(),Constants.AwardState.SUCCESS.getInfo());
    }

    @Override
    public Integer getDistributionGoodsName() {
        return Constants.AwardType.PhysicalGoods.getCode();
    }
}
