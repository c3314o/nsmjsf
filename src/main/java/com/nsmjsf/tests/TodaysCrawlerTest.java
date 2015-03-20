package com.nsmjsf.tests;
import static org.junit.Assert.*;

import org.junit.Test;

import com.nsmjsf.web.crawlers.TodaysPriceCrawler;


public class TodaysCrawlerTest {

	@Test
	public void test() {
	
		TodaysPriceCrawler crawler=new TodaysPriceCrawler();
		crawler.run();
	}

}
