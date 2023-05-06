package cn.xyr.lottery.interfaces.assembler;

import cn.xyr.lottery.domain.strategy.model.vo.DrawAwardVO;
import cn.xyr.lottery.rpc.dto.AwardDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

/**
 * @description: TODO
 * @author: xyr
 * @date: 2023-05-06 10:28
 * @github: https://github.com/1999p
 * @Copyright: https://xxydnx.cn
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE,unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface AwardMapping extends IMapping<DrawAwardVO, AwardDTO> {

    @Mapping(target = "userId",source = "uId")
    @Override
    AwardDTO sourceToTarget(DrawAwardVO var1);


    @Override
    DrawAwardVO targetToSource(AwardDTO var1);
}
