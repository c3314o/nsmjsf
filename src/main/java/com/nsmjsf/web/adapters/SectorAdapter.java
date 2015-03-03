
package com.nsmjsf.web.adapters;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.Sector;
import com.nsmjsf.web.wrappers.SectorWrapper;

public class SectorAdapter {
private static final Log log = LogFactory
			.getLog(SectorAdapter.class);
	
	public static List<SectorWrapper> wrapAll(List<Sector> sectorList)
	{
		List<SectorWrapper> sectorWrapperList=new ArrayList<SectorWrapper>();
		for(Sector sector:sectorList)
		{
			SectorWrapper sectorWrapper=new SectorWrapper();
			sectorWrapper.setSector(sector);
			sectorWrapperList.add(sectorWrapper);
		}
		return sectorWrapperList;
		
	}
	
	public static SectorWrapper wrap(Sector sector)
	{
		SectorWrapper sectorWrapper=new SectorWrapper();
		sectorWrapper.setSector(sector);
		return sectorWrapper;
		
	}

}

