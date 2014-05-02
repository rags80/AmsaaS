package com.ams.sharedkernel.domain.repository;

import java.util.ArrayList;
import java.util.List;

public class Page<T>
{
	private int		noOfRecordsPerFetch;
	private int		currentIndex	= 0;
	private int		nextIndex;
	private List<T>	pageDataList	= new ArrayList<T>();

	public Page()
	{}

	public Page(int startIndex, int nextIndex)
	{
		this.currentIndex = startIndex;
		this.nextIndex = nextIndex;

	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj == null)
		{
			return false;
		}
		if (!(obj instanceof Page))
		{
			return false;
		}
		Page other = (Page) obj;
		if (this.currentIndex != other.currentIndex)
		{
			return false;
		}
		if (this.nextIndex != other.nextIndex)
		{
			return false;
		}
		if (this.noOfRecordsPerFetch != other.noOfRecordsPerFetch)
		{
			return false;
		}
		if (this.pageDataList == null)
		{
			if (other.pageDataList != null)
			{
				return false;
			}
		}
		else if (!this.pageDataList.equals(other.pageDataList))
		{
			return false;
		}
		return true;
	}

	public int getCurrentIndex()
	{
		return this.currentIndex;
	}

	public int getNextIndex()
	{
		return this.nextIndex;
	}

	public int getNoOfRecordsPerFetch()
	{
		return this.noOfRecordsPerFetch;
	}

	public List<T> getPageDataList()
	{
		return this.pageDataList;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = (prime * result) + this.currentIndex;
		result = (prime * result) + this.nextIndex;
		result = (prime * result) + this.noOfRecordsPerFetch;
		result = (prime * result) + ((this.pageDataList == null) ? 0 : this.pageDataList.hashCode());
		return result;
	}

	public int nextIndexIs()
	{
		this.nextIndex = this.currentIndex + this.noOfRecordsPerFetch;
		return this.nextIndex;
	}

	public void setCurrentIndex(int currentIndex)
	{
		this.currentIndex = currentIndex;
	}

	public void setNextIndex(int nextIndex)
	{
		this.nextIndex = nextIndex;
	}

	public void setNoOfRecordsPerFetch(int noOfRecordsPerFetch)
	{
		this.noOfRecordsPerFetch = noOfRecordsPerFetch;
	}

	public void setPageDataList(List<T> pageDataList)
	{
		this.pageDataList = pageDataList;
	}

}
