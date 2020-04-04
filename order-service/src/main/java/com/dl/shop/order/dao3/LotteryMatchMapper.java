package com.dl.shop.order.dao3;


import org.apache.ibatis.annotations.Param;

import com.dl.base.mapper.Mapper;
import com.dl.shop.order.model.LotteryMatch;

public interface LotteryMatchMapper extends Mapper<LotteryMatch> {

	public LotteryMatch getByMatchId(@Param("matchId")Integer matchId);

}