package com.nsmjsf.web.wrappers;

import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.KeyRatio;

public class KeyRatioWrapper {

	private static final Log log = LogFactory.getLog(KeyRatioWrapper.class);

	KeyRatio keyRatio;

	public KeyRatioWrapper(KeyRatio keyRatio) {
		this.keyRatio = keyRatio;
	}

	public KeyRatioWrapper() {

	}

	public KeyRatio getKeyRatio() {
		return keyRatio;
	}

	public void setKeyRatio(KeyRatio keyRatio) {
		this.keyRatio = keyRatio;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83 * hash + Objects.hashCode(this.keyRatio.getKeyRatioId());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final KeyRatioWrapper other = (KeyRatioWrapper) obj;
		if (!Objects.equals(this.keyRatio.getKeyRatioId(), other.getKeyRatio()
				.getKeyRatioId()))
			return false;
		return true;
	}

	public String getLabel() {
		return this.keyRatio.toString();

	}

}
