package com.dl.shop.order.service.model;

import java.util.List;

import lombok.Data;

@Data
public class TicketPlayInfo {

	private Integer playType;
	private String fixedodds;
	private List<CellInfo> cellInfos;
}
