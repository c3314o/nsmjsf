package com.nsmjsf.web.crawlers;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.nsmjsf.web.Application;
import com.nsmjsf.web.datamodels.TodaysPrice;
import com.nsmjsf.web.datamodels.TodaysPriceDump;
import com.nsmjsf.web.livedata.TodaysPriceLive;
import com.nsmjsf.web.utils.StringUtils;

/**
 * @author FirstName LastName
 *
 */
public class TodaysPriceCrawler implements Runnable {
	private static final Log log = LogFactory.getLog(TodaysPriceCrawler.class);
	private static String KEY_SERIAL_NO = "serial_no";
	private static String KEY_TRADED_COMPANIES = "traded_companies";
	private static String KEY_NO_OF_TRANSACTIONS = "no_of_transactions";
	private static String KEY_MAX_PRICE = "max_price";
	private static String KEY_MIN_PRICE = "min_price";
	private static String KEY_CLOSING_PRICE = "closing_price";
	private static String KEY_TRADED_SHARES = "traded_shares";
	private static String KEY_AMOUNT = "amount";
	private static String KEY_PREVIOUS_CLOSE = "previous_close";
	private static String KEY_DIFF_AMOUNT = "diff_amount";

	private static String TODAYS_URL = "http://www.nepalstock.com.np/todaysprice";
	private HashMap<String, String> params = new HashMap<String, String>();
	HashMap<String, Integer> headerMap = new HashMap<String, Integer>();
	private int rowCount = 0;

	FetchUrl fetcher;

	public TodaysPriceCrawler() {
		// TODO Auto-generated constructor stub
		// startDate=&stock-symbol=&_limit=50
		params.put("startDate", "");
		params.put("stock-symbol", "");
		
		params.put("_limit", "10000");
	}

	@Override
	public void run() {
		do {
			this.fetcher = new FetchUrl(TODAYS_URL);
			this.rowCount=0;
			Document doc = this.fetchDoc();
			if (doc != null) {
				this.parseDoc(doc);
			}
			try {
				Thread.sleep(1000*60);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (TodaysPriceLive.getInstance().isMarketOpen());
	}

	private Document fetchDoc() {
		Document doc = null;
		try {
			doc = fetcher.fetchDoc(params);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return doc;

	}

	private void parseDoc(Document doc) {

		if (doc != null) {
			log.info("Document received:" + doc.toString());
			Element table = doc.select("table.table-condensed.table-hover").first();
			log.info("Selected table:" + table.toString());

			Element tableHeader = table.select("tr.unique").first();
			Elements headerTds = tableHeader.select("td");
			Integer index = 0;
			for (Element td : headerTds) {
				String header = td.text();
				if (StringUtils.isAlike(header, "%S.N.%")) {
					headerMap.put(KEY_SERIAL_NO, index);
					index++;

				} else if (StringUtils.isAlike(header, "%Companies%")) {
					headerMap.put(KEY_TRADED_COMPANIES, index);
					index++;

				} else if (StringUtils.isAlike(header, "%Transaction%")) {
					headerMap.put(KEY_NO_OF_TRANSACTIONS, index);
					index++;

				} else if (StringUtils.isAlike(header, "Max%")) {
					headerMap.put(KEY_MAX_PRICE, index);
					index++;

				} else if (StringUtils.isAlike(header, "%Min%")) {
					headerMap.put(KEY_MIN_PRICE, index);
					index++;

				} else if (StringUtils.isAlike(header, "%Closing Price%")) {
					headerMap.put(KEY_CLOSING_PRICE, index);
					index++;

				} else if (StringUtils.isAlike(header, "%Traded Shares%")) {
					headerMap.put(KEY_TRADED_SHARES, index);
					index++;

				} else if (StringUtils.isAlike(header, "%Amount%")) {
					headerMap.put(KEY_AMOUNT, index);
					index++;

				} else if (StringUtils.isAlike(header, "Previous%")) {
					headerMap.put(KEY_PREVIOUS_CLOSE, index);
					index++;

				}else if (StringUtils.isAlike(header, "Difference%")) {
					headerMap.put(KEY_DIFF_AMOUNT, index);
					index++;

				}
			}
			this.printHeaders();

			Elements tableRows = table.select("tr");
			for (int i = 2; i < tableRows.size() - 4; i++) {
				// skip header

				Elements tableColumns = tableRows.get(i).select("td");
				log.info("Row data:" + tableColumns.toString());

				HashMap<String, String> dataMap = this
						.columnsToHashMap(tableColumns);
				TodaysPriceDump dump = this.hashMapToDump(dataMap);
				this.saveResult(dump);

			}

		}

	}

	private HashMap<String, String> columnsToHashMap(Elements tableColumns) {
		HashMap<String, String> dataMap = new HashMap<String, String>();
		dataMap.put(KEY_AMOUNT, tableColumns.get(headerMap.get(KEY_AMOUNT))
				.text());
		dataMap.put(KEY_CLOSING_PRICE,
				tableColumns.get(headerMap.get(KEY_CLOSING_PRICE)).text());
		dataMap.put(KEY_DIFF_AMOUNT,
				tableColumns.get(headerMap.get(KEY_DIFF_AMOUNT)).text());
		dataMap.put(KEY_MAX_PRICE, tableColumns.get(headerMap.get(KEY_MAX_PRICE))
				.text());
		dataMap.put(KEY_MIN_PRICE, tableColumns.get(headerMap.get(KEY_MIN_PRICE)).text());
		dataMap.put(KEY_NO_OF_TRANSACTIONS,
				tableColumns.get(headerMap.get(KEY_NO_OF_TRANSACTIONS)).text());
		dataMap.put(KEY_PREVIOUS_CLOSE,
				tableColumns.get(headerMap.get(KEY_PREVIOUS_CLOSE)).text());
		dataMap.put(KEY_TRADED_COMPANIES,
				tableColumns.get(headerMap.get(KEY_TRADED_COMPANIES)).text());
		dataMap.put(KEY_TRADED_SHARES,
				tableColumns.get(headerMap.get(KEY_TRADED_SHARES)).text());
		
		return dataMap;

	}

	private TodaysPriceDump hashMapToDump(HashMap<String, String> map) {
		TodaysPriceDump dump = null;
		if (map != null && map.size() > 0) {
			dump = new TodaysPriceDump();

		float todaysPriceClose=Float.parseFloat(map.get(KEY_CLOSING_PRICE));
		dump.setTodaysPriceClose(todaysPriceClose);
		String todaysPriceCompanyName=map.get(KEY_TRADED_COMPANIES);
		dump.setTodaysPriceCompanyName(todaysPriceCompanyName);
		log.info("Diff price:"+map.get(KEY_DIFF_AMOUNT));
		float todaysPriceHigh=Float.parseFloat(map.get(KEY_MAX_PRICE));
		dump.setTodaysPriceHigh(todaysPriceHigh);
		float todaysPriceLow=Float.parseFloat(map.get(KEY_MIN_PRICE));
		dump.setTodaysPriceLow(todaysPriceLow);
		float todaysPricePrevious=Float.parseFloat(map.get(KEY_PREVIOUS_CLOSE));
		dump.setTodaysPricePrevious(todaysPricePrevious);
		float todaysPriceTradedAmount=Float.parseFloat(map.get(KEY_AMOUNT));
		dump.setTodaysPriceTradedAmount(todaysPriceTradedAmount);
		int todaysPriceTradedVolume=StringUtils.parseInt(map.get(KEY_TRADED_SHARES));
		dump.setTodaysPriceTradedVolume(todaysPriceTradedVolume);
		int todaysPriceTransactionCount=StringUtils.parseInt(map.get(KEY_NO_OF_TRANSACTIONS));
		dump.setTodaysPriceTransactionCount(todaysPriceTransactionCount);
		float todaysPriceDiff=(todaysPriceClose-todaysPricePrevious);
		dump.setTodaysPriceDiff(todaysPriceDiff);
	

		}
		return dump;

	}

	private void saveResult(TodaysPriceDump dump) {
		rowCount++;
		System.out.println("Row count:" + rowCount + dump.toString());
		TodaysPriceLive.getInstance().updateData(dump);

	}
	
	private void printHeaders(){
		Iterator<Entry<String,Integer>> it=this.headerMap.entrySet().iterator();
		while(it.hasNext()){
			Entry<String,Integer> entry=it.next();
			log.info("Header:"+entry.getKey());
			log.info("Index"+entry.getValue());
		}
		
	}

}
