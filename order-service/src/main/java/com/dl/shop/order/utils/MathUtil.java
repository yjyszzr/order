package com.dl.shop.order.utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class MathUtil {


	public static void main(String[] args) {
//		System.out.println(getCombinationCount(4,2));
	}
	 
	/**
	 * 
	 * @描述:求复式注数（大乐透）
	 * @param r 红球
	 * @param b 蓝球
	 * @return
	 */
	final static public int getCathecticsCount(int r,int b){
		int betNumR = getCombinationCount(r, 5);
		int betNumB = getCombinationCount(b, 2);
		return betNumR*betNumB;
	}
	/**
	 * 
	 * @描述:求胆拖注数（大乐透）
	 * @param rt 红拖
	 * @param rd 红胆
	 * @param bt 蓝拖
	 * @param bd 蓝胆
	 * @return
	 */
	final static public int getDanTuoCathecticsCount(int rt,int rd,int bt,int bd){
		int betNumR = getCombinationCount(rt, 5-rd);
		int betNumB = getCombinationCount(bt, 2-bd);
		return betNumR*betNumB;
	}
	
	/**
	 * @作者:andy
	 * @描述:求组合C(n,r)
	 * @param n
	 * @param r
	 * @return
	 */
	final static private int getCombinationCount(int n,int r){
		if(r > n) return 0;
		if(r < 0 || n < 0) return 0;
		return getFactorial(n).divide(getFactorial(r),BigDecimal.ROUND_HALF_DOWN).divide(getFactorial((n-r)),BigDecimal.ROUND_HALF_DOWN).intValue();
	}
	
	 
	
	/**
	 * @作者:andy
	 * @描述:求n的阶乘
	 * @param n
	 * @return
	 */
	final static private BigDecimal getFactorial(int num) {
        BigDecimal sum = new BigDecimal(1.0);
        for(int i = 1; i <= num; i++)
        {
        	BigDecimal a = new BigDecimal(new BigInteger(i+""));
            sum =sum.multiply(a);
        }
        return sum;
    }

	 
	
}
