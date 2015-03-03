
package  com.nsmjsf.web.wrappers;


import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.ContentSource;

public class ContentSourceWrapper {

private static final Log log = LogFactory
			.getLog(ContentSourceWrapper.class);


	ContentSource contentSource;

	public ContentSourceWrapper(ContentSource contentSource) {
		this.contentSource = contentSource;
	}

	public ContentSourceWrapper() {

	}

	public ContentSource getContentSource() {
		return contentSource;
	}

	public void setContentSource(ContentSource contentSource) {
		this.contentSource = contentSource;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83 * hash + Objects.hashCode(this.contentSource.getContentSourceId());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final ContentSourceWrapper other = (ContentSourceWrapper) obj;
		if (!Objects.equals(this.contentSource.getContentSourceId(), other.getContentSource().getContentSourceId()))
			return false;
		return true;
	}

	public String getLabel() {
		return this.contentSource.toString();

	}

}

