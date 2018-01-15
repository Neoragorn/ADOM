package datastorage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Data {

	private static final Data instance = new Data();
	private HashMap<Integer, List<Integer>> dataMap = new HashMap<Integer, List<Integer>>();

	private HashMap<Integer, List<Integer>> costMap = new HashMap<Integer, List<Integer>>();

	
	public HashMap<Integer, List<Integer>> getCostMap() {
		return costMap;
	}

	public void setCostMap(HashMap<Integer, List<Integer>> costMap) {
		this.costMap = costMap;
	}

	public Data() {
		super();
	}

	public static Data createData() {
		return instance;
	}

	public static Data getInstance() {
		return instance;
	}

	public HashMap<Integer, List<Integer>> getDataMap() {
		return dataMap;
	}

	public void setDataMap(HashMap<Integer, List<Integer>> dataMap) {
		this.dataMap = dataMap;
	}

	public void addData(String[] datas)
	{
		List<Integer> list = new ArrayList<Integer>();
		list.add(Integer.valueOf(datas[1]));
		list.add(Integer.valueOf(datas[2]));
		dataMap.put(Integer.valueOf(datas[0]), list);
	}

	public void addCost()
	{
		for (Map.Entry<Integer, List<Integer>> entry : dataMap.entrySet()) {
			Integer key = entry.getKey();
			List<Integer> value = entry.getValue();
			List<Integer> costList = new ArrayList<Integer>();
			for (Map.Entry<Integer, List<Integer>> entry2 : dataMap.entrySet()) {
				List<Integer> value2 = entry2.getValue();
				int resultatCout = calculCost(value, value2);
				costList.add(resultatCout);				
				costMap.put(key, costList);
				//System.out.println(resultatCout);
			}
		}
	}

	public int calculCost(List<Integer> list1, List<Integer> list2)
	{
		return (int) Math.sqrt(Math.pow((list1.get(0) - list2.get(0)), 2.0) + Math.pow((list1.get(1) - list2.get(1)), 2.0));
	}

	public void showCost()
	{
		Iterator<?> it = this.costMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry)it.next();
			System.out.println(pair.getKey() + " = " + pair.getValue());
			//it.remove(); // avoids a ConcurrentModificationException
		}
	}

	public void showData()
	{
		Iterator<?> it = this.dataMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry)it.next();
			System.out.println(pair.getKey() + " = " + pair.getValue());
			//it.remove(); // avoids a ConcurrentModificationException
		}
	}


}
