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

import com.nsmjsf.web.datamodels.SemiYearAverage;
import com.nsmjsf.web.sorters.SemiYearAverageSorter;

public class LazySemiYearAverageDataModel extends
		LazyDataModel<SemiYearAverage> {
	private static final Log log = LogFactory
			.getLog(LazySemiYearAverageDataModel.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 8939496625458060791L;
	private List<SemiYearAverage> semiYearAverageList;

	public LazySemiYearAverageDataModel(
			List<SemiYearAverage> semiYearAverageList) {
		this.semiYearAverageList = semiYearAverageList;
	}

	@Override
	public SemiYearAverage getRowData(String rowKey) {
		for (SemiYearAverage semiYearAverage : semiYearAverageList) {
			if (semiYearAverage.getSemiYearAverageId().toString()
					.equalsIgnoreCase(rowKey))
				return semiYearAverage;
		}

		return null;
	}

	@Override
	public Object getRowKey(SemiYearAverage object) {
		return object.getSemiYearAverageId();
	}

	@Override
	public List<SemiYearAverage> load(int first, int pageSize,
			String sortField, SortOrder sortOrder, Map<String, Object> filters) {

		log.info("sortfield:" + sortField);
		List<SemiYearAverage> data = new ArrayList<SemiYearAverage>();

		// filter
		for (SemiYearAverage semiYearAverage : semiYearAverageList) {
			boolean match = true;

			if (filters != null) {
				for (Iterator<String> it = filters.keySet().iterator(); it
						.hasNext();) {
					try {
						String filterProperty = it.next();
						Object filterValue = filters.get(filterProperty);
						Field field = semiYearAverage.getClass()
								.getDeclaredField(filterProperty);
						field.setAccessible(true);
						String fieldValue = String.valueOf(field
								.get(semiYearAverage));
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
				data.add(semiYearAverage);
			}
		}

		// sort
		if (sortField != null) {
			Collections.sort(data, new SemiYearAverageSorter(sortField,
					sortOrder));
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
