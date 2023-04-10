package cn.xyr.lottery.domain.award.service.factory;

import cn.xyr.lottery.common.Constants;
import cn.xyr.lottery.domain.award.service.goods.IDistributionGoods;
import cn.xyr.lottery.domain.award.service.goods.impl.CouponGoods;
import cn.xyr.lottery.domain.award.service.goods.impl.DescGoods;
import cn.xyr.lottery.domain.award.service.goods.impl.PhysicalGoods;
import cn.xyr.lottery.domain.award.service.goods.impl.RedeemCodeGoods;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: 各类发奖奖品配置类
 * @author: xyr
 * @date: 2023-04-10 15:59
 * @Copyright: https://xxydnx.cn
 */
public class GoodsConfig {

    /**
     * 奖品发放策略组
     */
    protected static Map<Integer, IDistributionGoods> goodsMap = new ConcurrentHashMap<>();

    @Resource
    private DescGoods descGoods;

    @Resource
    private RedeemCodeGoods redeemCodeGoods;

    @Resource
    private CouponGoods couponGoods;

    @Resource
    private PhysicalGoods physicalGoods;

    @PostConstruct
    public void init(){
        goodsMap.put(Constants.AwardType.DESC.getCode(),descGoods);
        goodsMap.put(Constants.AwardType.RedeemCodeGoods.getCode(),redeemCodeGoods);
        goodsMap.put(Constants.AwardType.CouponGoods.getCode(), couponGoods);
        goodsMap.put(Constants.AwardType.PhysicalGoods.getCode(), physicalGoods);
    }
}
