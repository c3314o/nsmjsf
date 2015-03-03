package com.nsmjsf.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;




import com.nsmjsf.web.adapters.AuctionAdapter;
import com.nsmjsf.web.datasources.AuctionDataSource;
import com.nsmjsf.web.datamodels.Auction;
import com.nsmjsf.web.wrappers.AuctionWrapper;


@FacesConverter("auctionWrapperConverter")
public class AuctionWrapperConverter implements Converter{
	private static final Log log = LogFactory.getLog(AuctionWrapperConverter.class);

	@Override
	public Object getAsObject(FacesContext fc, UIComponent arg1, String value) {
		if (value != null && value.trim().length() > 0) {
			AuctionDataSource auctionDataSource = new AuctionDataSource();
			Auction auction = auctionDataSource.get(Integer.parseInt(value));
			AuctionWrapper auctionWrapper=AuctionAdapter.wrap(auction);
			return auctionWrapper;
		} else {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent arg1, Object object) {
		if (object != null) {
			return String.valueOf(((AuctionWrapper) object).getAuction().getAuctionId());
		} else {
			return null;
		}
	}

	
	
	
	
	
	
	
	
}

