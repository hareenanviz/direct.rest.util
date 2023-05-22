package com.anvizent.rest.util;

import java.util.List;

import flexjson.JSONSerializer;

/**
 * @author Hareen Bejjanki
 * @author Venkateswararao K
 *
 */
public class  DataResponseGroup<T extends Object> {
	private String name;
	private String displayName;
	private Integer sequence;
	private Integer currentPage;
	private Integer pageSize;
	private Integer totalSize;
	private T data;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(Integer totalSize) {
		this.totalSize = totalSize;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public String toJSON(List<String> excludes) {
		JSONSerializer jsonSerializer = new JSONSerializer().exclude("*.class");

		if (excludes != null && !excludes.isEmpty()) {
			jsonSerializer.exclude(excludes.toArray(new String[] {}));
		}

		return jsonSerializer.include("*.*").serialize(this);
	}

}
