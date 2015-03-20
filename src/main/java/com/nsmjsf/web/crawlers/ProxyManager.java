package com.nsmjsf.web.crawlers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.nsmjsf.web.datamodels.Proxy;
import com.nsmjsf.web.datasources.ProxyDataSource;

public class ProxyManager {

	List<Proxy> proxyPool;
	List<Proxy> alivePool;

	ProxyDataSource proxyDataSource;
	ProxyRefresher refresher;
	boolean refresherRunning = false;
	ExecutorService executorService = Executors.newFixedThreadPool(1);

	private Random randomGenerator;
	public static ProxyManager INSTANCE = new ProxyManager();

	public static ProxyManager getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ProxyManager();
		}
		return INSTANCE;
	}

	private ProxyManager() {
		this.proxyDataSource = new ProxyDataSource();
		this.proxyPool = proxyDataSource.getAll();
		randomGenerator = new Random();
		this.alivePool = new ArrayList<Proxy>();
		refresher = new ProxyRefresher();
		executorService.execute(refresher);

		// this.refreshPool();
	}

	public Proxy getProxy() {
		if (this.alivePool.size() > 0) {
			int index = randomGenerator.nextInt(alivePool.size());
			return alivePool.get(index);

		} else {
			int index = randomGenerator.nextInt(proxyPool.size());
			if (!refresherRunning) {
				// refresher.run();
				// refresherRunning=true;
			}
			return proxyPool.get(index);
		}

	}

	public List<Proxy> getAlivePool() {
		return alivePool;
	}

	public void setAlivePool(List<Proxy> alivePool) {
		this.alivePool = alivePool;
	}

	public void onProxySuccess(Proxy proxy) {
		proxy.setProxySuccess(proxy.getProxySuccess() + 1);
		if (!this.alivePool.contains(proxy)) {

			this.alivePool.add(proxy);
			System.out.println("Alive pool size" + this.alivePool.size());
		}

	}

	public void onProxyFailure(Proxy proxy) {
		proxy.setProxyFailed(proxy.getProxyFailed() + 1);
		if (this.alivePool.contains(proxy)) {
			this.alivePool.remove(proxy);
			System.out.println("Alive pool size" + this.alivePool.size());
		}
		this.proxyPool.remove(proxy);

	}

	private class ProxyRefresher implements Runnable {

		public ProxyRefresher() {
			// TODO Auto-generated constructor stub
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			refreshPool();

		}

		private void refreshPool() {
			do {

				for (Proxy proxy : proxyPool) {
					if (this.isAlive(proxy)) {
						ProxyManager.getInstance().onProxySuccess(proxy);

					}

				}

				System.out.println("Initialized alive pool with size"
						+ ProxyManager.this.alivePool.size());
				try {
					Thread.sleep(1000 * 60 * 10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} while (true);

		}

		private boolean isAlive(Proxy dbProxy) {
			HttpURLConnection uc = null;
			boolean isAlive = false;
			URL url = null;
			try {
				url = new URL("https://www.google.com");
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			java.net.Proxy proxy = new java.net.Proxy(java.net.Proxy.Type.HTTP,
					new InetSocketAddress(dbProxy.getProxyIp(),
							dbProxy.getProxyPort()));

			try {
				uc = (HttpURLConnection) url.openConnection(proxy);
				uc.setReadTimeout(50000);
				uc.setConnectTimeout(10000);
				System.out.println("Checking proxy " + dbProxy.getProxyIp()
						+ "with port" + dbProxy.getProxyPort());

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if ((uc != null)) {
				try {
					if (uc.getResponseCode() == 200) {
						isAlive = true;
						System.out.println("Found alive proxy"
								+ dbProxy.getProxyIp());

					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return isAlive;
		}

	}

}
