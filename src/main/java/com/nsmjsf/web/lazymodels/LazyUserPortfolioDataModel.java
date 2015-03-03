

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

import com.nsmjsf.web.datamodels.UserPortfolio;
import com.nsmjsf.web.sorters.UserPortfolioSorter;

public class LazyUserPortfolioDataModel extends LazyDataModel<UserPortfolio> {
	private static final Log log = LogFactory
			.getLog(LazyUserPortfolioDataModel.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 8939496625458060791L;
	private List<UserPortfolio> userPortfolioList;

	public LazyUserPortfolioDataModel(List<UserPortfolio> userPortfolioList) {
		this.userPortfolioList = userPortfolioList;
	}

	@Override
	public UserPortfolio getRowData(String rowKey) {
		for (UserPortfolio userPortfolio : userPortfolioList) {
			if (userPortfolio.getUserPortfolioId().toString().equalsIgnoreCase(rowKey))
				return userPortfolio;
		}

		return null;
	}

	@Override
	public Object getRowKey(UserPortfolio object) {
		return object.getUserPortfolioId();
	}

	@Override
	public List<UserPortfolio> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		log.info("sortfield:" + sortField);
		List<UserPortfolio> data = new ArrayList<UserPortfolio>();

		// filter
		for (UserPortfolio userPortfolio : userPortfolioList) {
			boolean match = true;

			if (filters != null) {
				for (Iterator<String> it = filters.keySet().iterator(); it
						.hasNext();) {
					try {
						String filterProperty = it.next();
						Object filterValue = filters.get(filterProperty);
						Field field = userPortfolio.getClass().getDeclaredField(
								filterProperty);
						field.setAccessible(true);
						String fieldValue = String
								.valueOf(field.get(userPortfolio));
						log.info("filterField:"+filterProperty);
						log.info("filterValue:"+fieldValue);

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
				data.add(userPortfolio);
			}
		}

		// sort
		if (sortField != null) {
			Collections.sort(data, new UserPortfolioSorter(sortField, sortOrder));
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

