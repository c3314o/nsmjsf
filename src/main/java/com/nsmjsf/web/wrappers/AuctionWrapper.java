package com.nsmjsf.web.wrappers;

import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.Auction;

public class AuctionWrapper {

	private static final Log log = LogFactory.getLog(AuctionWrapper.class);

	Auction auction;

	public AuctionWrapper(Auction auction) {
		this.auction = auction;
	}

	public AuctionWrapper() {

	}

	public Auction getAuction() {
		return auction;
	}

	public void setAuction(Auction auction) {
		this.auction = auction;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83 * hash + Objects.hashCode(this.auction.getAuctionId());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final AuctionWrapper other = (AuctionWrapper) obj;
		if (!Objects.equals(this.auction.getAuctionId(), other.getAuction()
				.getAuctionId()))
			return false;
		return true;
	}

	public String getLabel() {
		return this.auction.toString();

	}

}
