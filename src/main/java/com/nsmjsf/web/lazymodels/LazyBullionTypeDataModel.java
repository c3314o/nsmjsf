

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

import com.nsmjsf.web.datamodels.BullionType;
import com.nsmjsf.web.sorters.BullionTypeSorter;

public class LazyBullionTypeDataModel extends LazyDataModel<BullionType> {
	private static final Log log = LogFactory
			.getLog(LazyBullionTypeDataModel.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 8939496625458060791L;
	private List<BullionType> bullionTypeList;

	public LazyBullionTypeDataModel(List<BullionType> bullionTypeList) {
		this.bullionTypeList = bullionTypeList;
	}

	@Override
	public BullionType getRowData(String rowKey) {
		for (BullionType bullionType : bullionTypeList) {
			if (bullionType.getBullionTypeId().toString().equalsIgnoreCase(rowKey))
				return bullionType;
		}

		return null;
	}

	@Override
	public Object getRowKey(BullionType object) {
		return object.getBullionTypeId();
	}

	@Override
	public List<BullionType> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		log.info("sortfield:" + sortField);
		List<BullionType> data = new ArrayList<BullionType>();

		// filter
		for (BullionType bullionType : bullionTypeList) {
			boolean match = true;

			if (filters != null) {
				for (Iterator<String> it = filters.keySet().iterator(); it
						.hasNext();) {
					try {
						String filterProperty = it.next();
						Object filterValue = filters.get(filterProperty);
						Field field = bullionType.getClass().getDeclaredField(
								filterProperty);
						field.setAccessible(true);
						String fieldValue = String
								.valueOf(field.get(bullionType));
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
				data.add(bullionType);
			}
		}

		// sort
		if (sortField != null) {
			Collections.sort(data, new BullionTypeSorter(sortField, sortOrder));
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

