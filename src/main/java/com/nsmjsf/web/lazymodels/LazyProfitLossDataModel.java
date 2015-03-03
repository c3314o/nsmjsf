

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

import com.nsmjsf.web.datamodels.ProfitLoss;
import com.nsmjsf.web.sorters.ProfitLossSorter;

public class LazyProfitLossDataModel extends LazyDataModel<ProfitLoss> {
	private static final Log log = LogFactory
			.getLog(LazyProfitLossDataModel.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 8939496625458060791L;
	private List<ProfitLoss> profitLossList;

	public LazyProfitLossDataModel(List<ProfitLoss> profitLossList) {
		this.profitLossList = profitLossList;
	}

	@Override
	public ProfitLoss getRowData(String rowKey) {
		for (ProfitLoss profitLoss : profitLossList) {
			if (profitLoss.getProfitLossId().toString().equalsIgnoreCase(rowKey))
				return profitLoss;
		}

		return null;
	}

	@Override
	public Object getRowKey(ProfitLoss object) {
		return object.getProfitLossId();
	}

	@Override
	public List<ProfitLoss> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		log.info("sortfield:" + sortField);
		List<ProfitLoss> data = new ArrayList<ProfitLoss>();

		// filter
		for (ProfitLoss profitLoss : profitLossList) {
			boolean match = true;

			if (filters != null) {
				for (Iterator<String> it = filters.keySet().iterator(); it
						.hasNext();) {
					try {
						String filterProperty = it.next();
						Object filterValue = filters.get(filterProperty);
						Field field = profitLoss.getClass().getDeclaredField(
								filterProperty);
						field.setAccessible(true);
						String fieldValue = String
								.valueOf(field.get(profitLoss));
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
				data.add(profitLoss);
			}
		}

		// sort
		if (sortField != null) {
			Collections.sort(data, new ProfitLossSorter(sortField, sortOrder));
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

