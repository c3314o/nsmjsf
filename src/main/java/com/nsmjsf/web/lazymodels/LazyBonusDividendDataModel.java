

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

import com.nsmjsf.web.datamodels.BonusDividend;
import com.nsmjsf.web.sorters.BonusDividendSorter;

public class LazyBonusDividendDataModel extends LazyDataModel<BonusDividend> {
	private static final Log log = LogFactory
			.getLog(LazyBonusDividendDataModel.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 8939496625458060791L;
	private List<BonusDividend> bonusDividendList;

	public LazyBonusDividendDataModel(List<BonusDividend> bonusDividendList) {
		this.bonusDividendList = bonusDividendList;
	}

	@Override
	public BonusDividend getRowData(String rowKey) {
		for (BonusDividend bonusDividend : bonusDividendList) {
			if (bonusDividend.getBonusDividendId().toString().equalsIgnoreCase(rowKey))
				return bonusDividend;
		}

		return null;
	}

	@Override
	public Object getRowKey(BonusDividend object) {
		return object.getBonusDividendId();
	}

	@Override
	public List<BonusDividend> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		log.info("sortfield:" + sortField);
		List<BonusDividend> data = new ArrayList<BonusDividend>();

		// filter
		for (BonusDividend bonusDividend : bonusDividendList) {
			boolean match = true;

			if (filters != null) {
				for (Iterator<String> it = filters.keySet().iterator(); it
						.hasNext();) {
					try {
						String filterProperty = it.next();
						Object filterValue = filters.get(filterProperty);
						Field field = bonusDividend.getClass().getDeclaredField(
								filterProperty);
						field.setAccessible(true);
						String fieldValue = String
								.valueOf(field.get(bonusDividend));
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
				data.add(bonusDividend);
			}
		}

		// sort
		if (sortField != null) {
			Collections.sort(data, new BonusDividendSorter(sortField, sortOrder));
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

