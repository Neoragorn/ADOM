package algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import datastorage.Data;

public class TSPAlgo {

	List<Integer> scheme = new ArrayList<Integer>();

	public TSPAlgo(List<Integer> scheme)
	{
		this.scheme = scheme;
	}

	public int tspAlgorithm(HashMap<Integer, List<Integer>> costMap)
	{
		int result = 0;
		int actualCity = scheme.get(0);
		int nextCity = scheme.get(1);
		int i = 1;
		while (i < scheme.size())
		{
			//			System.out.println(scheme.get(i));
			nextCity = scheme.get(i);
			//System.out.println("actual => " + actualCity +  " next => " + nextCity);
			for (Map.Entry<Integer, List<Integer>> entry : costMap.entrySet()) {
				Integer key = entry.getKey();				
				List<Integer> value = entry.getValue();
				//	System.out.println(key + " " + value);
				if (actualCity == key)
				{
					Data.getInstance().getHillClimbingData().put(key, value);
					if (key == 0)
						result += value.get(nextCity);
					else
						result += value.get(nextCity - 1);
				}
			}
			actualCity = nextCity;
			i++;
		}
		nextCity = scheme.get(0);
		for (Map.Entry<Integer, List<Integer>> entry : costMap.entrySet()) {
			Integer key = entry.getKey();				
			List<Integer> value = entry.getValue();
			if (actualCity == key)
			{
				Data.getInstance().getHillClimbingData().put(key, value);
				if (key == 0)
					result += value.get(nextCity);
				else
					result += value.get(nextCity - 1);
			}
		}
		System.out.println("Result of TSP Algo is " + result);
		return result;
	}
	
	public void supressUselessMatrice()
	{
		HashMap<Integer, List<Integer>> datas = Data.getInstance().getHillClimbingData();
		HashMap<Integer, List<Integer>> datastmp = new HashMap();
		List<Integer> tmp = new  ArrayList();
		boolean checkzero = false;
		
		for (Map.Entry<Integer, List<Integer>> entry : datas.entrySet()) {
			Integer keyTmp = entry.getKey();
			List<Integer> value = entry.getValue();
			tmp = new ArrayList();
			for (Integer i : value)
			{
				if (i == 0)
					checkzero = true;
				if (checkzero)
					tmp.add(i);
			}
			datastmp.put(keyTmp, tmp);
			checkzero = false;
		}
		Data.getInstance().setHillClimbingData(datastmp);
	}
}
