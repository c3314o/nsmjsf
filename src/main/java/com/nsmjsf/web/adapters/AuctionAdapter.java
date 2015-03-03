
package com.nsmjsf.web.adapters;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.Auction;
import com.nsmjsf.web.wrappers.AuctionWrapper;

public class AuctionAdapter {
private static final Log log = LogFactory
			.getLog(AuctionAdapter.class);
	
	public static List<AuctionWrapper> wrapAll(List<Auction> auctionList)
	{
		List<AuctionWrapper> auctionWrapperList=new ArrayList<AuctionWrapper>();
		for(Auction auction:auctionList)
		{
			AuctionWrapper auctionWrapper=new AuctionWrapper();
			auctionWrapper.setAuction(auction);
			auctionWrapperList.add(auctionWrapper);
		}
		return auctionWrapperList;
		
	}
	
	public static AuctionWrapper wrap(Auction auction)
	{
		AuctionWrapper auctionWrapper=new AuctionWrapper();
		auctionWrapper.setAuction(auction);
		return auctionWrapper;
		
	}

}

