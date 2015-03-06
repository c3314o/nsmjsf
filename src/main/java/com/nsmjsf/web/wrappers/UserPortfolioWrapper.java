package com.nsmjsf.web.wrappers;

import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.UserPortfolio;

public class UserPortfolioWrapper {

	private static final Log log = LogFactory
			.getLog(UserPortfolioWrapper.class);

	UserPortfolio userPortfolio;

	public UserPortfolioWrapper(UserPortfolio userPortfolio) {
		this.userPortfolio = userPortfolio;
	}

	public UserPortfolioWrapper() {

	}

	public UserPortfolio getUserPortfolio() {
		return userPortfolio;
	}

	public void setUserPortfolio(UserPortfolio userPortfolio) {
		this.userPortfolio = userPortfolio;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83 * hash
				+ Objects.hashCode(this.userPortfolio.getUserPortfolioId());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final UserPortfolioWrapper other = (UserPortfolioWrapper) obj;
		if (!Objects.equals(this.userPortfolio.getUserPortfolioId(), other
				.getUserPortfolio().getUserPortfolioId()))
			return false;
		return true;
	}

	public String getLabel() {
		return this.userPortfolio.toString();

	}

}
