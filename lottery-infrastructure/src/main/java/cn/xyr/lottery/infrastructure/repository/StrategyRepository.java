package cn.xyr.lottery.infrastructure.repository;

import cn.xyr.lottery.domain.strategy.model.aggregates.StrategyRich;
import cn.xyr.lottery.domain.strategy.model.vo.AwardBriefVO;
import cn.xyr.lottery.domain.strategy.model.vo.StrategyBriefVO;
import cn.xyr.lottery.domain.strategy.model.vo.StrategyDetailBriefVO;
import cn.xyr.lottery.domain.strategy.respository.IStrategyRepository;
import cn.xyr.lottery.infrastructure.dao.IAwardDao;
import cn.xyr.lottery.infrastructure.dao.IStrategyDao;
import cn.xyr.lottery.infrastructure.dao.IStrategyDetailDao;
import cn.xyr.lottery.infrastructure.po.Award;
import cn.xyr.lottery.infrastructure.po.Strategy;
import cn.xyr.lottery.infrastructure.po.StrategyDetail;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: TODO 策略表仓储服务
 * @author: xyr
 * @date: 2023-04-14 10:37
 * @github: https://github.com/1999p
 * @Copyright: https://xxydnx.cn
 */
@Component
public class StrategyRepository implements IStrategyRepository {

    @Resource
    private IStrategyDao strategyDao;

    @Resource
    private IStrategyDetailDao strategyDetailDao;
    @Resource
    private IAwardDao awardDao;

    @Override
    public StrategyRich queryStrategyRich(Long strategyId) {
        // 1.获取策略简要信息
        // 1.1查询当前策略
        Strategy strategy = strategyDao.queryStrategy(strategyId);
        // 1.2新建策略简要信息
        StrategyBriefVO strategyBriefVO = new StrategyBriefVO();
        // 1.3拷贝策略简要信息
        BeanUtils.copyProperties(strategy, strategyBriefVO);

        // 2.获取策略明细(StrategyDetailList)简要信息集合
        // 2.1查询策略表详细配置
        List<StrategyDetail> strategyDetailList = strategyDetailDao.queryStrategyDetailList(strategyId);
        // 2.2 新建策略明细简要信息集合，用于存放策略明细简要信息
        List<StrategyDetailBriefVO> strategyDetailBriefVOList = new ArrayList<>();
        for (StrategyDetail strategyDetail : strategyDetailList) {
            // 2.3 新建策略明细简要信息
            StrategyDetailBriefVO strategyDetailBriefVO = new StrategyDetailBriefVO();
            // 2.4 将策略明细信息拷贝至策略明细简要信息
            BeanUtils.copyProperties(strategyDetail, strategyDetailBriefVO);
            // 2.5添加至策略明细简要集合
            strategyDetailBriefVOList.add(strategyDetailBriefVO);
        }
        // 返回抽奖策略聚合对象
        return new StrategyRich(strategyId, strategyBriefVO, strategyDetailBriefVOList);
    }

    @Override
    public AwardBriefVO queryAwardInfo(String awardId) {
        Award award = awardDao.queryAwardInfo(awardId);

        AwardBriefVO awardBriefVO = new AwardBriefVO();
        // 可以使用 BeanUtils.copyProperties(award, awardBriefVO)、或者基于ASM实现的Bean-Mapping，
        // 但在效率上最好的依旧是硬编码
        // BeanUtils.copyProperties(awardBriefVO,awardBriefVO);

        awardBriefVO.setAwardId(award.getAwardId());
        awardBriefVO.setAwardType(award.getAwardType());
        awardBriefVO.setAwardName(award.getAwardName());
        awardBriefVO.setAwardContent(award.getAwardContent());
        return awardBriefVO;
    }

    @Override
    public List<String> queryNoStockStrategyAwardList(Long strategyId) {
        return strategyDetailDao.queryNoStockStrategyAwardList(strategyId);
    }

    @Override
    public boolean deductStock(Long strategyId, String awardId) {
        StrategyDetail req = new StrategyDetail();
        req.setStrategyId(strategyId);
        req.setAwardId(awardId);

        int count = strategyDetailDao.deductStock(req);

        return count == 1;
    }
}
