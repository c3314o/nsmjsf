
package com.nsmjsf.web.adapters;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.CrawlerSettings;
import com.nsmjsf.web.wrappers.CrawlerSettingsWrapper;

public class CrawlerSettingsAdapter {
private static final Log log = LogFactory
			.getLog(CrawlerSettingsAdapter.class);
	
	public static List<CrawlerSettingsWrapper> wrapAll(List<CrawlerSettings> crawlerSettingsList)
	{
		List<CrawlerSettingsWrapper> crawlerSettingsWrapperList=new ArrayList<CrawlerSettingsWrapper>();
		for(CrawlerSettings crawlerSettings:crawlerSettingsList)
		{
			CrawlerSettingsWrapper crawlerSettingsWrapper=new CrawlerSettingsWrapper();
			crawlerSettingsWrapper.setCrawlerSettings(crawlerSettings);
			crawlerSettingsWrapperList.add(crawlerSettingsWrapper);
		}
		return crawlerSettingsWrapperList;
		
	}
	
	public static CrawlerSettingsWrapper wrap(CrawlerSettings crawlerSettings)
	{
		CrawlerSettingsWrapper crawlerSettingsWrapper=new CrawlerSettingsWrapper();
		crawlerSettingsWrapper.setCrawlerSettings(crawlerSettings);
		return crawlerSettingsWrapper;
		
	}

}

