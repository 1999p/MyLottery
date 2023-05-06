package cn.xyr.lottery.rpc.res;

import cn.xyr.lottery.common.Result;
import cn.xyr.lottery.rpc.dto.AwardDTO;

import java.io.Serializable;

/**
 * @description: 抽奖结果
 * @author: xyr
 * @date: 2023-05-06 10:31
 * @github: https://github.com/1999p
 * @Copyright: https://xxydnx.cn
 */
public class DrawRes extends Result implements Serializable {

    /**
     *  奖品信息
     */
    private AwardDTO awardDTO;

    public DrawRes(String code, String info) {
        super(code, info);
    }

    public AwardDTO getAwardDTO() {
        return awardDTO;
    }

    public void setAwardDTO(AwardDTO awardDTO) {
        this.awardDTO = awardDTO;
    }
}
