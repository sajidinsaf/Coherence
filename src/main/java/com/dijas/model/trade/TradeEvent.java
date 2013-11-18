package com.dijas.model.trade;

import java.sql.Time;

public interface TradeEvent<T> {

	String getId();
	
	Time getEventTime();
	
	String getEventType();
	
	String getEventDescription();
	
	T getTradeEntity();
	
}
