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

import com.nsmjsf.web.datamodels.Bod;
import com.nsmjsf.web.sorters.BodSorter;

public class LazyBodDataModel extends LazyDataModel<Bod> {
	private static final Log log = LogFactory.getLog(LazyBodDataModel.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 8939496625458060791L;
	private List<Bod> bodList;

	public LazyBodDataModel(List<Bod> bodList) {
		this.bodList = bodList;
	}

	@Override
	public Bod getRowData(String rowKey) {
		for (Bod bod : bodList) {
			if (bod.getBodId().toString().equalsIgnoreCase(rowKey))
				return bod;
		}

		return null;
	}

	@Override
	public Object getRowKey(Bod object) {
		return object.getBodId();
	}

	@Override
	public List<Bod> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		log.info("sortfield:" + sortField);
		List<Bod> data = new ArrayList<Bod>();

		// filter
		for (Bod bod : bodList) {
			boolean match = true;

			if (filters != null) {
				for (Iterator<String> it = filters.keySet().iterator(); it
						.hasNext();) {
					try {
						String filterProperty = it.next();
						Object filterValue = filters.get(filterProperty);
						Field field = bod.getClass().getDeclaredField(
								filterProperty);
						field.setAccessible(true);
						String fieldValue = String.valueOf(field.get(bod));
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
				data.add(bod);
			}
		}

		// sort
		if (sortField != null) {
			Collections.sort(data, new BodSorter(sortField, sortOrder));
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
