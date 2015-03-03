
package com.nsmjsf.web.adapters;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.FloorsheetDump;
import com.nsmjsf.web.wrappers.FloorsheetDumpWrapper;

public class FloorsheetDumpAdapter {
private static final Log log = LogFactory
			.getLog(FloorsheetDumpAdapter.class);
	
	public static List<FloorsheetDumpWrapper> wrapAll(List<FloorsheetDump> floorsheetDumpList)
	{
		List<FloorsheetDumpWrapper> floorsheetDumpWrapperList=new ArrayList<FloorsheetDumpWrapper>();
		for(FloorsheetDump floorsheetDump:floorsheetDumpList)
		{
			FloorsheetDumpWrapper floorsheetDumpWrapper=new FloorsheetDumpWrapper();
			floorsheetDumpWrapper.setFloorsheetDump(floorsheetDump);
			floorsheetDumpWrapperList.add(floorsheetDumpWrapper);
		}
		return floorsheetDumpWrapperList;
		
	}
	
	public static FloorsheetDumpWrapper wrap(FloorsheetDump floorsheetDump)
	{
		FloorsheetDumpWrapper floorsheetDumpWrapper=new FloorsheetDumpWrapper();
		floorsheetDumpWrapper.setFloorsheetDump(floorsheetDump);
		return floorsheetDumpWrapper;
		
	}

}

