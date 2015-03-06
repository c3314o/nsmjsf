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

import com.nsmjsf.web.datamodels.EnergyPrice;
import com.nsmjsf.web.sorters.EnergyPriceSorter;

public class LazyEnergyPriceDataModel extends LazyDataModel<EnergyPrice> {
	private static final Log log = LogFactory
			.getLog(LazyEnergyPriceDataModel.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 8939496625458060791L;
	private List<EnergyPrice> energyPriceList;

	public LazyEnergyPriceDataModel(List<EnergyPrice> energyPriceList) {
		this.energyPriceList = energyPriceList;
	}

	@Override
	public EnergyPrice getRowData(String rowKey) {
		for (EnergyPrice energyPrice : energyPriceList) {
			if (energyPrice.getEnergyPriceId().toString()
					.equalsIgnoreCase(rowKey))
				return energyPrice;
		}

		return null;
	}

	@Override
	public Object getRowKey(EnergyPrice object) {
		return object.getEnergyPriceId();
	}

	@Override
	public List<EnergyPrice> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		log.info("sortfield:" + sortField);
		List<EnergyPrice> data = new ArrayList<EnergyPrice>();

		// filter
		for (EnergyPrice energyPrice : energyPriceList) {
			boolean match = true;

			if (filters != null) {
				for (Iterator<String> it = filters.keySet().iterator(); it
						.hasNext();) {
					try {
						String filterProperty = it.next();
						Object filterValue = filters.get(filterProperty);
						Field field = energyPrice.getClass().getDeclaredField(
								filterProperty);
						field.setAccessible(true);
						String fieldValue = String.valueOf(field
								.get(energyPrice));
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
				data.add(energyPrice);
			}
		}

		// sort
		if (sortField != null) {
			Collections.sort(data, new EnergyPriceSorter(sortField, sortOrder));
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
