package algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HillClimbing {

	private List<Integer> originalscheme = new ArrayList<Integer>();
	private List<Integer> schema = new ArrayList<Integer>();
	private List<Integer> distanceRecord = new ArrayList();
	private List<List<Integer>> listSchema = new ArrayList();
	private HashMap<Integer, List<Integer>> originalData = new HashMap(); 
	private HashMap<Integer, Integer> data = new HashMap();

	private Integer swapIndex = 1;
	private Integer swapMovingIndex = swapIndex + 1;
	private Integer actualCity = 1;
	private Integer nextCity;

	public HillClimbing(List<Integer> originalscheme, HashMap<Integer, List<Integer>> data) {
		super();
		this.originalscheme = originalscheme;
		this.originalData = data;
		this.schema = new ArrayList(originalscheme);
		System.out.println("ORIGINAL Schema is : " + this.schema);
	}

	public void algoRound()
	{
		int distanceFirst = 0;
		boolean firstcity = false;
		this.actualCity = schema.get(0);
		for (Integer schema : schema)
		{ 
			if (firstcity)
			{
				this.nextCity = schema;
				for (Map.Entry<Integer, List<Integer>> entry : originalData.entrySet()) {
					Integer key = entry.getKey();
					List<Integer> value = entry.getValue();
					if (key == actualCity)
					{
						if (nextCity == 0)
							nextCity = 1;
						//System.out.println(key +  "   nextCity =>  "  +  nextCity +  "  value = " + value +   "  ");
						//System.out.println("dist de next city " + value.get(nextCity - 1));
						distanceFirst += value.get(nextCity - 1);
					}
				}
			}
			this.actualCity = schema;
			//System.out.println("Actual city : " + actualCity);
			firstcity = true;
		}
		distanceRecord.add(distanceFirst);
		System.out.println("With " + this.schema + " We have a Distance of " + distanceFirst);
	}

	public void swap()
	{
		algoRound();
		int nbRound = 1;
		while (nbRound != this.schema.size() - 1)
		{
			while (this.swapIndex != this.schema.size() - 1)
			{
				Collections.swap(this.schema, swapIndex, swapMovingIndex);
				this.swapIndex += 1;
				swapMovingIndex += 1;
				//System.out.println("Schema is : " + this.schema);
				algoRound();
				
			}
			nbRound++;
			System.out.println("Swap Done, returning to original scheme : " + this.originalscheme);
			this.schema =  new ArrayList(originalscheme);
			this.swapIndex = nbRound;
			this.swapMovingIndex = swapIndex + 1;
		}
		System.out.println("The best and shortest distance of the algo is : " + Collections.min(distanceRecord));
	}
}
