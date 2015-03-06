package com.nsmjsf.web.wrappers;

import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.User;

public class UserWrapper {

	private static final Log log = LogFactory.getLog(UserWrapper.class);

	User user;

	public UserWrapper(User user) {
		this.user = user;
	}

	public UserWrapper() {

	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83 * hash + Objects.hashCode(this.user.getUserId());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final UserWrapper other = (UserWrapper) obj;
		if (!Objects.equals(this.user.getUserId(), other.getUser().getUserId()))
			return false;
		return true;
	}

	public String getLabel() {
		return this.user.toString();

	}

}
