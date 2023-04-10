package cn.xyr.lottery.domain.award.service.factory;

import cn.xyr.lottery.domain.award.service.goods.IDistributionGoods;
import org.springframework.stereotype.Service;

/**
 * @description: 配送商品简单工厂，提供获取配送服务
 * @author: xyr
 * @date: 2023-04-10 15:59
 * @Copyright: https://xxydnx.cn
 */
@Service
public class DistributionGoodsFactory extends GoodsConfig{
    public IDistributionGoods getDistributionGoodsService(Integer awardType){
        return goodsMap.get(awardType);
    }
}
