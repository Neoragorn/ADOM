package algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
				if (key == 0)
					result += value.get(nextCity);
				else
					result += value.get(nextCity - 1);
			}
		}
		System.out.println("Result of TSP Algo is " + result);
		return result;
	}
}
