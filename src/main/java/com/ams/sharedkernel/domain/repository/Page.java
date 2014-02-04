package com.ams.sharedkernel.domain.repository;

import java.util.ArrayList;
import java.util.List;

public class Page<T>
{
	private int		noOfRecordsPerFetch;
	private int		currentIndex	= 0;
	private int		nextIndex		= this.noOfRecordsPerFetch;
	private List<T>	pageDataList	= new ArrayList<T>();

	public Page()
	{}

	public Page(int startIndex, int nextIndex)
	{
		this.currentIndex = startIndex;
		this.nextIndex = nextIndex;

	}

	public int nextIndexIs()
	{
		this.nextIndex = this.currentIndex + this.noOfRecordsPerFetch;
		return nextIndex;
	}

	public int getNoOfRecordsPerFetch()
	{
		return noOfRecordsPerFetch;
	}

	public void setNoOfRecordsPerFetch(int noOfRecordsPerFetch)
	{
		this.noOfRecordsPerFetch = noOfRecordsPerFetch;
	}

	public int getCurrentIndex()
	{
		return currentIndex;
	}

	public void setCurrentIndex(int currentIndex)
	{
		this.currentIndex = currentIndex;
	}

	public int getNextIndex()
	{
		return nextIndex;
	}

	public void setNextIndex(int nextIndex)
	{
		this.nextIndex = nextIndex;
	}

	public List<T> getPageDataList()
	{
		return pageDataList;
	}

	public void setPageDataList(List<T> pageDataList)
	{
		this.pageDataList = pageDataList;
	}

}
