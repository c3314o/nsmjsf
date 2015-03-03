package com.nsmjsf.web.beans.views;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum ViewType {
	DATAGRID(1), DATATABLE(2), DATASCROLLER(3), DATATABLELIVE(4);

	private final int typeId;

	ViewType(int typeId) {
		this.typeId = typeId;
	}

	public int getPostCode() {
		return this.typeId;
	}

	private static final List<ViewType> VALUES = Collections
			.unmodifiableList(Arrays.asList(values()));
	private static final int SIZE = VALUES.size();
	private static final Random RANDOM = new Random();

	public static ViewType randomView() {
		return VALUES.get(RANDOM.nextInt(SIZE));
	}

}
