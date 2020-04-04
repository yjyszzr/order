/************************************************20170909  新增报表功能*************************************************/
#  sql script
--- 20180620huhedong增加出票失败退款字段 用于支持部分退款逻辑
-- 增加出票失败订单逻辑字段
alter table dl_order add print_lottery_status int(1) default 1 COMMENT '订单出票状态:1-待出票,2-部分出票失败已退款 3-全部出票失败已退款 4 出票成功 ' after order_status;
alter table dl_order add print_lottery_refund_amount decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '订单出票失败退款金额' after print_lottery_status;

-- 清洗历史数据  订单状态：订单状态:0-待付款,1-待出票,2-出票失败3-待开奖4-未中将5-已中奖6-派奖中7-已派奖8-支付失败
---- 矫正历史上出票失败的，矫正出票失败 为全部出票失败已退款，退款金额为订单实际支付金额
-- update dl_order set print_lottery_status=3,print_lottery_refund_amount = money_paid where  order_status=2
---- 矫正历史上待出票的数据为 待出票
-- update dl_order set print_lottery_status=1,print_lottery_refund_amount = 0 where  order_status=1
---- 矫正历史上其他数据为出票成功
-- update dl_order set print_lottery_status=4,print_lottery_refund_amount = 0 where  order_status>2
