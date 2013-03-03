package domain;

import java.util.List;

public class ListHolderImpl implements ListHolder{

	private List<Integer> list;
	private int numOfElements;
	private long sum = 0;
	public void iterate() {
		for (Integer i : list)
		{
			sum += i;
		}
		
	}
	
	public void setNumOfElements(int numOfElements) {
		this.numOfElements = numOfElements;	
	}

	public void setList(List<Integer> list) {
		this.list = list;
		
	}

	public void init()
	{
		for (int i = 0; i < numOfElements; i++)
		{
			list.add(new Integer(1));
		}
	}

}
