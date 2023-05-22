package com.anvizent.rest.util;

import java.util.TreeMap;

/**
 * @author Hareen Bejjanki
 * @author Venkateswararao K
 *
 */
public class DataResponseTree<T> {
	private T data;
	private TreeMap<Integer, DataResponseTree<T>> childs = new TreeMap<Integer, DataResponseTree<T>>();

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public TreeMap<Integer, DataResponseTree<T>> getChilds() {
		return childs;
	}

	public void setChilds(TreeMap<Integer, DataResponseTree<T>> childs) {
		this.childs = childs;
	}

}
