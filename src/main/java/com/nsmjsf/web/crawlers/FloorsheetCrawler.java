package com.nsmjsf.web.crawlers;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.nsmjsf.web.Application;
import com.nsmjsf.web.datamodels.Floorsheet;
import com.nsmjsf.web.datamodels.FloorsheetDump;
import com.nsmjsf.web.livedata.FloorsheetLive;
import com.nsmjsf.web.utils.StringUtils;

/**
 * @author FirstName LastName
 *
 */
public class FloorsheetCrawler implements Runnable {
	private static final Log log = LogFactory.getLog(FloorsheetCrawler.class);
	private static String KEY_SERIAL_NO = "serial_no";
	private static String KEY_CONTRACT_NO = "contact_no";
	private static String KEY_BUYER_BROKER = "buyer";
	private static String KEY_SELLER_BROKER = "seller";
	private static String KEY_STOCK_SYMBOL = "symbol";
	private static String KEY_QUANTITY = "quantity";
	private static String KEY_RATE = "rate";
	private static String KEY_AMOUNT = "amount";

	private static String FLOORSHEET_URL = "http://www.nepalstock.com.np/floorsheet";
	private HashMap<String, String> params = new HashMap<String, String>();
	HashMap<String, Integer> headerMap = new HashMap<String, Integer>();
	private int rowCount = 0;

	FetchUrl fetcher;

	public FloorsheetCrawler() {
		// TODO Auto-generated constructor stub
		// contract-no=&stock-symbol=&buyer=&seller=&_limit=500
		params.put("contract-no", "");
		params.put("stock-symbol", "");
		params.put("buyer", "");
		params.put("seller", "");
		params.put("_limit", "10000");
	}

	@Override
	public void run() {
		do {
			this.fetcher = new FetchUrl(FLOORSHEET_URL);
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
		} while (FloorsheetLive.getInstance().isMarketOpen());
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
			Element table = doc.select("table.table.my-table").first();
			log.info("Selected table:" + table.toString());

			Element tableHeader = table.select("tr.unique").first();
			Elements headerTds = tableHeader.select("td");
			Integer index = 0;
			for (Element td : headerTds) {
				String header = td.text();
				if (StringUtils.isAlike(header, "%S.N.%")) {
					headerMap.put(KEY_SERIAL_NO, index);
					index++;

				} else if (StringUtils.isAlike(header, "%Contract No%")) {
					headerMap.put(KEY_CONTRACT_NO, index);
					index++;

				} else if (StringUtils.isAlike(header, "%Stock Symbol%")) {
					headerMap.put(KEY_STOCK_SYMBOL, index);
					index++;

				} else if (StringUtils.isAlike(header, "Buyer%")) {
					headerMap.put(KEY_BUYER_BROKER, index);
					index++;

				} else if (StringUtils.isAlike(header, "%Quantity%")) {
					headerMap.put(KEY_QUANTITY, index);
					index++;

				} else if (StringUtils.isAlike(header, "%Rate%")) {
					headerMap.put(KEY_RATE, index);
					index++;

				} else if (StringUtils.isAlike(header, "%Amount%")) {
					headerMap.put(KEY_AMOUNT, index);
					index++;

				} else if (StringUtils.isAlike(header, "Seller%")) {
					headerMap.put(KEY_SELLER_BROKER, index);
					index++;

				}
			}

			Elements tableRows = table.select("tr");
			for (int i = 2; i < tableRows.size() - 3; i++) {
				// skip header

				Elements tableColumns = tableRows.get(i).select("td");
				log.info("Row data:" + tableColumns.toString());

				HashMap<String, String> dataMap = this
						.columnsToHashMap(tableColumns);
				FloorsheetDump dump = this.hashMapToDump(dataMap);
				this.saveResult(dump);

			}

		}

	}

	private HashMap<String, String> columnsToHashMap(Elements tableColumns) {
		HashMap<String, String> dataMap = new HashMap<String, String>();
		dataMap.put(KEY_AMOUNT, tableColumns.get(headerMap.get(KEY_AMOUNT))
				.text());
		dataMap.put(KEY_BUYER_BROKER,
				tableColumns.get(headerMap.get(KEY_BUYER_BROKER)).text());
		dataMap.put(KEY_CONTRACT_NO,
				tableColumns.get(headerMap.get(KEY_CONTRACT_NO)).text());
		dataMap.put(KEY_QUANTITY, tableColumns.get(headerMap.get(KEY_QUANTITY))
				.text());
		dataMap.put(KEY_RATE, tableColumns.get(headerMap.get(KEY_RATE)).text());
		dataMap.put(KEY_SELLER_BROKER,
				tableColumns.get(headerMap.get(KEY_SELLER_BROKER)).text());
		dataMap.put(KEY_SERIAL_NO,
				tableColumns.get(headerMap.get(KEY_SERIAL_NO)).text());
		dataMap.put(KEY_STOCK_SYMBOL,
				tableColumns.get(headerMap.get(KEY_STOCK_SYMBOL)).text());
		return dataMap;

	}

	private FloorsheetDump hashMapToDump(HashMap<String, String> map) {
		FloorsheetDump dump = null;
		if (map != null && map.size() > 0) {
			dump = new FloorsheetDump();

			float floorsheetAmount = Float.parseFloat(map.get(KEY_AMOUNT));
			log.info("floorsheetAmount" + floorsheetAmount);
			dump.setFloorsheetAmount(floorsheetAmount);

			int floorsheetBuyerBroker = Integer.parseInt(map
					.get(KEY_BUYER_BROKER));
			log.info("floorsheetBuyerBroker:" + floorsheetBuyerBroker);
			dump.setFloorsheetBuyerBroker(floorsheetBuyerBroker);

			int floorsheetQuantity = Integer.parseInt(map.get(KEY_QUANTITY));
			log.info("floorsheetQuantity" + floorsheetQuantity);
			dump.setFloorsheetQuantity(floorsheetQuantity);

			float floorsheetRate = Float.parseFloat(map.get(KEY_RATE));
			log.info("floorsheetRate" + floorsheetRate);
			dump.setFloorsheetRate(floorsheetRate);

			int floorsheetSellerBroker = Integer.parseInt(map
					.get(KEY_SELLER_BROKER));
			log.info("floorsheetSellerBroker" + floorsheetSellerBroker);
			dump.setFloorsheetSellerBroker(floorsheetSellerBroker);

			String floorsheetStockSymbol = map.get(KEY_STOCK_SYMBOL);
			log.info("floorsheetStockSymbol" + floorsheetStockSymbol);
			dump.setFloorsheetStockSymbol(floorsheetStockSymbol);

			long floorsheetTransactionNo = Long.parseLong(map
					.get(KEY_CONTRACT_NO));
			log.info("floorsheetTransactionNo" + floorsheetTransactionNo);
			dump.setFloorsheetTransactionNo(floorsheetTransactionNo);

		}
		return dump;

	}

	private void saveResult(FloorsheetDump dump) {
		rowCount++;
		System.out.println("Row count:" + rowCount + dump.toString());
		FloorsheetLive.getInstance().updateData(dump);

	}

}
