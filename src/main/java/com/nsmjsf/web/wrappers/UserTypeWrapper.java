package com.nsmjsf.web.wrappers;

import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.UserType;

public class UserTypeWrapper {

	private static final Log log = LogFactory.getLog(UserTypeWrapper.class);

	UserType userType;

	public UserTypeWrapper(UserType userType) {
		this.userType = userType;
	}

	public UserTypeWrapper() {

	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83 * hash + Objects.hashCode(this.userType.getUserTypeId());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final UserTypeWrapper other = (UserTypeWrapper) obj;
		if (!Objects.equals(this.userType.getUserTypeId(), other.getUserType()
				.getUserTypeId()))
			return false;
		return true;
	}

	public String getLabel() {
		return this.userType.getUserTypeLabel();

	}

}
