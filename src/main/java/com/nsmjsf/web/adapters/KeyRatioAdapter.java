package com.nsmjsf.web.adapters;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.KeyRatio;
import com.nsmjsf.web.wrappers.KeyRatioWrapper;

public class KeyRatioAdapter {
	private static final Log log = LogFactory.getLog(KeyRatioAdapter.class);

	public static List<KeyRatioWrapper> wrapAll(List<KeyRatio> keyRatioList) {
		List<KeyRatioWrapper> keyRatioWrapperList = new ArrayList<KeyRatioWrapper>();
		for (KeyRatio keyRatio : keyRatioList) {
			KeyRatioWrapper keyRatioWrapper = new KeyRatioWrapper();
			keyRatioWrapper.setKeyRatio(keyRatio);
			keyRatioWrapperList.add(keyRatioWrapper);
		}
		return keyRatioWrapperList;

	}

	public static KeyRatioWrapper wrap(KeyRatio keyRatio) {
		KeyRatioWrapper keyRatioWrapper = new KeyRatioWrapper();
		keyRatioWrapper.setKeyRatio(keyRatio);
		return keyRatioWrapper;

	}

}
