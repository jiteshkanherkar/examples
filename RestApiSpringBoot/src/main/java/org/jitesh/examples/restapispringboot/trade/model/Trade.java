package org.jitesh.examples.restapispringboot.trade.model;

public class Trade {

	private long tradeId;
	private String tradeName;
	public long getTradeId() {
		return tradeId;
	}
	public void setTradeId(long tradeId) {
		this.tradeId = tradeId;
	}
	public String getTradeName() {
		return tradeName;
	}
	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}
	@Override
	public String toString() {
		return "Trade [tradeId=" + tradeId + ", tradeName=" + tradeName + "]";
	}
	
	
}
