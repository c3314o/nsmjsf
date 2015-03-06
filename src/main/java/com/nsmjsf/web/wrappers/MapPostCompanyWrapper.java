package com.nsmjsf.web.wrappers;

import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.MapPostCompany;

public class MapPostCompanyWrapper {

	private static final Log log = LogFactory
			.getLog(MapPostCompanyWrapper.class);

	MapPostCompany mapPostCompany;

	public MapPostCompanyWrapper(MapPostCompany mapPostCompany) {
		this.mapPostCompany = mapPostCompany;
	}

	public MapPostCompanyWrapper() {

	}

	public MapPostCompany getMapPostCompany() {
		return mapPostCompany;
	}

	public void setMapPostCompany(MapPostCompany mapPostCompany) {
		this.mapPostCompany = mapPostCompany;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83 * hash
				+ Objects.hashCode(this.mapPostCompany.getMapPostCompanyId());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final MapPostCompanyWrapper other = (MapPostCompanyWrapper) obj;
		if (!Objects.equals(this.mapPostCompany.getMapPostCompanyId(), other
				.getMapPostCompany().getMapPostCompanyId()))
			return false;
		return true;
	}

	public String getLabel() {
		return this.mapPostCompany.toString();

	}

}
