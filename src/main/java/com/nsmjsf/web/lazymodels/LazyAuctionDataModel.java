package com.nsmjsf.web.lazymodels;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.nsmjsf.web.datamodels.Auction;
import com.nsmjsf.web.sorters.AuctionSorter;

public class LazyAuctionDataModel extends LazyDataModel<Auction> {
	private static final Log log = LogFactory
			.getLog(LazyAuctionDataModel.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 8939496625458060791L;
	private List<Auction> auctionList;

	public LazyAuctionDataModel(List<Auction> auctionList) {
		this.auctionList = auctionList;
	}

	@Override
	public Auction getRowData(String rowKey) {
		for (Auction auction : auctionList) {
			if (auction.getAuctionId().toString().equalsIgnoreCase(rowKey))
				return auction;
		}

		return null;
	}

	@Override
	public Object getRowKey(Auction object) {
		return object.getAuctionId();
	}

	@Override
	public List<Auction> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		log.info("sortfield:" + sortField);
		List<Auction> data = new ArrayList<Auction>();

		// filter
		for (Auction auction : auctionList) {
			boolean match = true;

			if (filters != null) {
				for (Iterator<String> it = filters.keySet().iterator(); it
						.hasNext();) {
					try {
						String filterProperty = it.next();
						Object filterValue = filters.get(filterProperty);
						Field field = auction.getClass().getDeclaredField(
								filterProperty);
						field.setAccessible(true);
						String fieldValue = String.valueOf(field.get(auction));
						log.info("filterField:" + filterProperty);
						log.info("filterValue:" + fieldValue);

						if (filterValue == null
								|| fieldValue
										.startsWith(filterValue.toString())) {
							match = true;
						} else {
							match = false;
							break;
						}
					} catch (Exception e) {
						match = false;
					}
				}
			}

			if (match) {
				data.add(auction);
			}
		}

		// sort
		if (sortField != null) {
			Collections.sort(data, new AuctionSorter(sortField, sortOrder));
		}

		// rowCount
		int dataSize = data.size();
		this.setRowCount(dataSize);

		// paginate
		if (dataSize > pageSize) {
			try {
				return data.subList(first, first + pageSize);
			} catch (IndexOutOfBoundsException e) {
				return data.subList(first, first + (dataSize % pageSize));
			}
		} else {
			return data;
		}
	}

}
