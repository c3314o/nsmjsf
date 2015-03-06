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

import com.nsmjsf.web.datamodels.Agm;
import com.nsmjsf.web.sorters.AgmSorter;

public class LazyAgmDataModel extends LazyDataModel<Agm> {
	private static final Log log = LogFactory.getLog(LazyAgmDataModel.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 8939496625458060791L;
	private List<Agm> agmList;

	public LazyAgmDataModel(List<Agm> agmList) {
		this.agmList = agmList;
	}

	@Override
	public Agm getRowData(String rowKey) {
		for (Agm agm : agmList) {
			if (agm.getAgmId().toString().equalsIgnoreCase(rowKey))
				return agm;
		}

		return null;
	}

	@Override
	public Object getRowKey(Agm object) {
		return object.getAgmId();
	}

	@Override
	public List<Agm> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		log.info("sortfield:" + sortField);
		List<Agm> data = new ArrayList<Agm>();

		// filter
		for (Agm agm : agmList) {
			boolean match = true;

			if (filters != null) {
				for (Iterator<String> it = filters.keySet().iterator(); it
						.hasNext();) {
					try {
						String filterProperty = it.next();
						Object filterValue = filters.get(filterProperty);
						Field field = agm.getClass().getDeclaredField(
								filterProperty);
						field.setAccessible(true);
						String fieldValue = String.valueOf(field.get(agm));
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
				data.add(agm);
			}
		}

		// sort
		if (sortField != null) {
			Collections.sort(data, new AgmSorter(sortField, sortOrder));
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
