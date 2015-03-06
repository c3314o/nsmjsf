package com.nsmjsf.web.wrappers;

import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.NewLow;

public class NewLowWrapper {

	private static final Log log = LogFactory.getLog(NewLowWrapper.class);

	NewLow newLow;

	public NewLowWrapper(NewLow newLow) {
		this.newLow = newLow;
	}

	public NewLowWrapper() {

	}

	public NewLow getNewLow() {
		return newLow;
	}

	public void setNewLow(NewLow newLow) {
		this.newLow = newLow;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83 * hash + Objects.hashCode(this.newLow.getNewLowId());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final NewLowWrapper other = (NewLowWrapper) obj;
		if (!Objects.equals(this.newLow.getNewLowId(), other.getNewLow()
				.getNewLowId()))
			return false;
		return true;
	}

	public String getLabel() {
		return this.newLow.toString();

	}

}
