
package  com.nsmjsf.web.wrappers;


import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.IndexType;

public class IndexTypeWrapper {

private static final Log log = LogFactory
			.getLog(IndexTypeWrapper.class);


	IndexType indexType;

	public IndexTypeWrapper(IndexType indexType) {
		this.indexType = indexType;
	}

	public IndexTypeWrapper() {

	}

	public IndexType getIndexType() {
		return indexType;
	}

	public void setIndexType(IndexType indexType) {
		this.indexType = indexType;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83 * hash + Objects.hashCode(this.indexType.getIndexTypeId());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final IndexTypeWrapper other = (IndexTypeWrapper) obj;
		if (!Objects.equals(this.indexType.getIndexTypeId(), other.getIndexType().getIndexTypeId()))
			return false;
		return true;
	}

	public String getLabel() {
		return this.indexType.toString();

	}

}

