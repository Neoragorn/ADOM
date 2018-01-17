package algorithm;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HeuristiqueAlgo {

	private int firstVille;
	private int ville;
	private int index;
	private HashMap<Integer, Boolean> listCityVisited = new HashMap<Integer, Boolean>();

	public HeuristiqueAlgo(Integer firstVille)
	{
		this.firstVille = firstVille;
		this.ville = firstVille;
		int i = 1;
		while (i <= 100)
		{
			listCityVisited.put(i, false);
			i++;
		}
	}

	public int shortestDistance(List<Integer> cities)
	{
		int distmax;
		int dist = 0;
		int indexl = 0;

		distmax = Collections.max(cities);
		for (Integer i : cities)
		{
			Boolean bool = listCityVisited.get(indexl + 1);
			//System.out.println("city " + indexl + " => " + bool);
			if (i < distmax && i > 0 && !bool)
			{
				distmax = i;
				dist = i;
				this.index = indexl + 1;
			}
			indexl++;
		}
		return dist;
	}

	public boolean checkVisitedCities()
	{
		for (Map.Entry<Integer, Boolean> entry : listCityVisited.entrySet()) {
			boolean checking = entry.getValue();
			if (!checking)
				return false;
		}
		return true;
	}

	public boolean checkIfCityVisited(int index)
	{
		for (Map.Entry<Integer, Boolean> entry : listCityVisited.entrySet()) {

			if (entry.getKey() == index)
			{
				boolean checking = entry.getValue();
				return checking;
			}
		}
		return true;
	}

	public int tspHeurisAlgorithm(HashMap<Integer, List<Integer>> costMap)
	{
		int result = 0;
		int dist;

		while (!checkVisitedCities())
		{
			for (Map.Entry<Integer, List<Integer>> entry : costMap.entrySet()) {
				Integer key = entry.getKey();			
				if (key == ville && !checkIfCityVisited(key))
				{
					List<Integer> cities = entry.getValue();					
					dist = shortestDistance(cities);
					result += dist;
					//System.out.println("Shortest distance is between city N°" + ville + " and city N°" + this.index + " which is " + dist);
					this.listCityVisited.put(key, true);
				}
			}
			ville = this.index;
		}
		for (Map.Entry<Integer, List<Integer>> entry : costMap.entrySet()) {
			Integer key = entry.getKey();
			if (key == ville)
			{
				List<Integer> cities = entry.getValue();
				dist = cities.get(firstVille - 1);
				//System.out.println("Shortest distance is between city N°" + ville + " and city N°" + firstVille + " which is " + dist);
				result += dist;
			}
		}
		return result;
	}
}
