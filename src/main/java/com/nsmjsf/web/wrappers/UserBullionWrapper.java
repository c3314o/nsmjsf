package com.nsmjsf.web.wrappers;

import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.UserBullion;

public class UserBullionWrapper {

	private static final Log log = LogFactory.getLog(UserBullionWrapper.class);

	UserBullion userBullion;

	public UserBullionWrapper(UserBullion userBullion) {
		this.userBullion = userBullion;
	}

	public UserBullionWrapper() {

	}

	public UserBullion getUserBullion() {
		return userBullion;
	}

	public void setUserBullion(UserBullion userBullion) {
		this.userBullion = userBullion;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83 * hash
				+ Objects.hashCode(this.userBullion.getUserBullionId());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final UserBullionWrapper other = (UserBullionWrapper) obj;
		if (!Objects.equals(this.userBullion.getUserBullionId(), other
				.getUserBullion().getUserBullionId()))
			return false;
		return true;
	}

	public String getLabel() {
		return this.userBullion.toString();

	}

}
