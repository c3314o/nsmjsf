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

import com.nsmjsf.web.datamodels.CertificateDividendDistribution;
import com.nsmjsf.web.sorters.CertificateDividendDistributionSorter;

public class LazyCertificateDividendDistributionDataModel extends
		LazyDataModel<CertificateDividendDistribution> {
	private static final Log log = LogFactory
			.getLog(LazyCertificateDividendDistributionDataModel.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 8939496625458060791L;
	private List<CertificateDividendDistribution> certificateDividendDistributionList;

	public LazyCertificateDividendDistributionDataModel(
			List<CertificateDividendDistribution> certificateDividendDistributionList) {
		this.certificateDividendDistributionList = certificateDividendDistributionList;
	}

	@Override
	public CertificateDividendDistribution getRowData(String rowKey) {
		for (CertificateDividendDistribution certificateDividendDistribution : certificateDividendDistributionList) {
			if (certificateDividendDistribution
					.getCertificateDividendDistributionId().toString()
					.equalsIgnoreCase(rowKey))
				return certificateDividendDistribution;
		}

		return null;
	}

	@Override
	public Object getRowKey(CertificateDividendDistribution object) {
		return object.getCertificateDividendDistributionId();
	}

	@Override
	public List<CertificateDividendDistribution> load(int first, int pageSize,
			String sortField, SortOrder sortOrder, Map<String, Object> filters) {

		log.info("sortfield:" + sortField);
		List<CertificateDividendDistribution> data = new ArrayList<CertificateDividendDistribution>();

		// filter
		for (CertificateDividendDistribution certificateDividendDistribution : certificateDividendDistributionList) {
			boolean match = true;

			if (filters != null) {
				for (Iterator<String> it = filters.keySet().iterator(); it
						.hasNext();) {
					try {
						String filterProperty = it.next();
						Object filterValue = filters.get(filterProperty);
						Field field = certificateDividendDistribution
								.getClass().getDeclaredField(filterProperty);
						field.setAccessible(true);
						String fieldValue = String.valueOf(field
								.get(certificateDividendDistribution));
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
				data.add(certificateDividendDistribution);
			}
		}

		// sort
		if (sortField != null) {
			Collections.sort(data, new CertificateDividendDistributionSorter(
					sortField, sortOrder));
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
