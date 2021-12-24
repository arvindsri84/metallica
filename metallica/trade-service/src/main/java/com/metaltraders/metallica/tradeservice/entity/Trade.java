package com.metaltraders.metallica.tradeservice.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "trade")
public class Trade implements Serializable {

	public static final String DATE_FORMAT = "dd/MM/yy";
	
	/**
	 * default serial version uid
	 */
	private static final long serialVersionUID = -1304468917770164248L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="trade_seq")
	@SequenceGenerator(name = "trade_seq",sequenceName="trade_seq",allocationSize = 1,initialValue=500500)
	@Column(name = "trade_id")
	private Long tradeId;
	
	@Column(name = "trade_date")
	@NotNull
	@JsonFormat(pattern = DATE_FORMAT)
	private Date tradeDate;
	
	@Column(name = "quatity")
	@NotNull
	private BigDecimal quatity;
	
	@Column(name = "price")
	@NotNull
	private BigDecimal price;
	
	@Column(name = "side")
	@NotNull
	@Enumerated(value = EnumType.STRING)
	private Side side;
	
	@Column(name = "status")
	@Enumerated(value = EnumType.STRING)
	private TradeStatus status;
	
	
	@Column(name = "comodity")
	private String comodity;
	
	@Column(name = "counterparty")
	private String counterparty;
	
	@Column(name = "location")
	private String location;


	public Long getTradeId() {
		return tradeId;
	}

	public void setTradeId(Long tradeId) {
		this.tradeId = tradeId;
	}

	public Date getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}

	public BigDecimal getQuatity() {
		return quatity;
	}

	public void setQuatity(BigDecimal quatity) {
		this.quatity = quatity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Side getSide() {
		return side;
	}

	public void setSide(Side side) {
		this.side = side;
	}

	public TradeStatus getStatus() {
		return status;
	}

	public void setStatus(TradeStatus status) {
		this.status = status;
	}

	public String getComodity() {
		return comodity;
	}

	public void setComodity(String comodity) {
		this.comodity = comodity;
	}

	public String getCounterparty() {
		return counterparty;
	}

	public void setCounterparty(String counterparty) {
		this.counterparty = counterparty;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "TradeEntity [tradeId=" + tradeId + ", tradeDate=" + tradeDate
				+ ", quatity=" + quatity + ", price=" + price + ", side="
				+ side + ", status=" + status + ", comodity=" + comodity
				+ ", counterparty=" + counterparty + ", location=" + location + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((comodity == null) ? 0 : comodity.hashCode());
		result = prime * result
				+ ((counterparty == null) ? 0 : counterparty.hashCode());
		result = prime * result
				+ ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((quatity == null) ? 0 : quatity.hashCode());
		result = prime * result + ((side == null) ? 0 : side.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result
				+ ((tradeDate == null) ? 0 : tradeDate.hashCode());
		result = prime * result + ((tradeId == null) ? 0 : tradeId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Trade other = (Trade) obj;
		if (comodity == null) {
			if (other.comodity != null)
				return false;
		} else if (!comodity.equals(other.comodity))
			return false;
		if (counterparty == null) {
			if (other.counterparty != null)
				return false;
		} else if (!counterparty.equals(other.counterparty))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (quatity == null) {
			if (other.quatity != null)
				return false;
		} else if (!quatity.equals(other.quatity))
			return false;
		if (side != other.side)
			return false;
		if (status != other.status)
			return false;
		if (tradeDate == null) {
			if (other.tradeDate != null)
				return false;
		} else if (!tradeDate.equals(other.tradeDate))
			return false;
		if (tradeId == null) {
			if (other.tradeId != null)
				return false;
		} else if (!tradeId.equals(other.tradeId))
			return false;
		return true;
	}
	
	
}
