package com.nsmjsf.web.wrappers;

import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.Sector;

public class SectorWrapper {

	private static final Log log = LogFactory.getLog(SectorWrapper.class);

	Sector sector;

	public SectorWrapper(Sector sector) {
		this.sector = sector;
	}

	public SectorWrapper() {

	}

	public Sector getSector() {
		return sector;
	}

	public void setSector(Sector sector) {
		this.sector = sector;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83 * hash + Objects.hashCode(this.sector.getSectorId());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final SectorWrapper other = (SectorWrapper) obj;
		if (!Objects.equals(this.sector.getSectorId(), other.getSector()
				.getSectorId()))
			return false;
		return true;
	}

	public String getLabel() {
		return this.sector.toString();

	}

}
