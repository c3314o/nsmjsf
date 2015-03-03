
package com.nsmjsf.web.adapters;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.IndexType;
import com.nsmjsf.web.wrappers.IndexTypeWrapper;

public class IndexTypeAdapter {
private static final Log log = LogFactory
			.getLog(IndexTypeAdapter.class);
	
	public static List<IndexTypeWrapper> wrapAll(List<IndexType> indexTypeList)
	{
		List<IndexTypeWrapper> indexTypeWrapperList=new ArrayList<IndexTypeWrapper>();
		for(IndexType indexType:indexTypeList)
		{
			IndexTypeWrapper indexTypeWrapper=new IndexTypeWrapper();
			indexTypeWrapper.setIndexType(indexType);
			indexTypeWrapperList.add(indexTypeWrapper);
		}
		return indexTypeWrapperList;
		
	}
	
	public static IndexTypeWrapper wrap(IndexType indexType)
	{
		IndexTypeWrapper indexTypeWrapper=new IndexTypeWrapper();
		indexTypeWrapper.setIndexType(indexType);
		return indexTypeWrapper;
		
	}

}

