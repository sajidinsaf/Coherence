package com.dijas.model.trade;

import java.sql.Time;
import java.util.Set;

public interface Trade {

	String getId();
	
	Time getValidFromTime();
	
	Time getValidToTime();
	
	Set<TradeEvent<?>> getTradeEvents();
	
	Set<Cashflow> getCashflows();
	
}
