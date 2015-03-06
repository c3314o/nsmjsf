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

import com.nsmjsf.web.datamodels.UserEnergy;
import com.nsmjsf.web.sorters.UserEnergySorter;

public class LazyUserEnergyDataModel extends LazyDataModel<UserEnergy> {
	private static final Log log = LogFactory
			.getLog(LazyUserEnergyDataModel.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 8939496625458060791L;
	private List<UserEnergy> userEnergyList;

	public LazyUserEnergyDataModel(List<UserEnergy> userEnergyList) {
		this.userEnergyList = userEnergyList;
	}

	@Override
	public UserEnergy getRowData(String rowKey) {
		for (UserEnergy userEnergy : userEnergyList) {
			if (userEnergy.getUserEnergyId().toString()
					.equalsIgnoreCase(rowKey))
				return userEnergy;
		}

		return null;
	}

	@Override
	public Object getRowKey(UserEnergy object) {
		return object.getUserEnergyId();
	}

	@Override
	public List<UserEnergy> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		log.info("sortfield:" + sortField);
		List<UserEnergy> data = new ArrayList<UserEnergy>();

		// filter
		for (UserEnergy userEnergy : userEnergyList) {
			boolean match = true;

			if (filters != null) {
				for (Iterator<String> it = filters.keySet().iterator(); it
						.hasNext();) {
					try {
						String filterProperty = it.next();
						Object filterValue = filters.get(filterProperty);
						Field field = userEnergy.getClass().getDeclaredField(
								filterProperty);
						field.setAccessible(true);
						String fieldValue = String.valueOf(field
								.get(userEnergy));
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
				data.add(userEnergy);
			}
		}

		// sort
		if (sortField != null) {
			Collections.sort(data, new UserEnergySorter(sortField, sortOrder));
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
