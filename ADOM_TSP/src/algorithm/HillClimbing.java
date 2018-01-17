package algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HillClimbing {

	private List<Integer> originalscheme = new ArrayList<Integer>();
	private List<Integer> schema = new ArrayList<Integer>();
	private List<Integer> distanceRecord = new ArrayList<Integer>();
	// private List<List<Integer>> listSchema = new ArrayList<List<Integer>>();
	private HashMap<Integer, List<Integer>> originalData = new HashMap<Integer, List<Integer>>();
	// private HashMap<Integer, Integer> data = new HashMap<Integer, Integer>();

	private Integer swapIndex = 1;
	private Integer swapMovingIndex = swapIndex + 1;
	private Integer actualCity = 1;
	private Integer nextCity;

	public HillClimbing(List<Integer> originalscheme, HashMap<Integer, List<Integer>> data) {
		super();
		this.originalscheme = originalscheme;
		this.originalData = data;
		this.schema = new ArrayList<Integer>(originalscheme);
		System.out.println("ORIGINAL Schema is : " + this.schema);
	}

	public int algoRound() {
		int distanceFirst = 0;
		boolean firstcity = false;
		this.actualCity = schema.get(0);
		for (Integer schema : schema) {
			if (firstcity) {
				this.nextCity = schema;
				for (Map.Entry<Integer, List<Integer>> entry : originalData.entrySet()) {
					Integer key = entry.getKey();
					List<Integer> value = entry.getValue();
					if (key == actualCity) {
						if (nextCity == 0)
							nextCity = 1;
						// System.out.println(key + " nextCity => " + nextCity + " value = " + value + "
						// ");
						// System.out.println("dist de next city " + value.get(nextCity - 1));
						distanceFirst += value.get(nextCity - 1);
					}
				}
			}
			this.actualCity = schema;
			// System.out.println("Actual city : " + actualCity);
			firstcity = true;
		}
		distanceRecord.add(distanceFirst);
		System.out.println("With " + this.schema + " We have a Distance of " + distanceFirst);
		return distanceFirst;
	}

	public void swap() {
		System.out.println("ALGORITHM SWAP");
		algoRound();
		int nbRound = 1;
		while (nbRound != this.schema.size() - 1) {
			while (this.swapIndex != this.schema.size() - 1) {
				Collections.swap(this.schema, swapIndex, swapMovingIndex);
				this.swapIndex += 1;
				swapMovingIndex += 1;
				// System.out.println("Schema is : " + this.schema);
				algoRound();

			}
			nbRound++;
			System.out.println("Swap Done, returning to original scheme : " + this.originalscheme);
			this.schema = new ArrayList<Integer>(originalscheme);
			this.swapIndex = nbRound;
			this.swapMovingIndex = swapIndex + 1;
		}
		this.swapIndex = 1;
		this.swapMovingIndex = swapIndex + 1;
		System.out.println("The best and shortest distance of the algo is : " + Collections.min(distanceRecord));
	}

	public void twoOpt() {
		System.out.println("ALGORITHME TWO OPT");
		algoRound();
		int y = this.schema.size() - 2;
		Integer lastResult = null;
		Integer result = null;
		Integer finalResult = null;
		List<Integer> tmpSchema =  new ArrayList<Integer>(this.schema);
		List<Integer> lastSchema =  new ArrayList<Integer>(this.schema);
		boolean better = false;
		
		while (!better) {
			y = this.schema.size() - 2;
			for (int i = 1; i < this.schema.size() / 2; i++) {
				Collections.swap(this.schema, i, y);
				result = algoRound();
				if (lastResult == null)
					lastResult = result;
				if (result < lastResult)
				{
					lastResult = result;
					tmpSchema = new ArrayList<Integer>(this.schema);
					lastSchema = new ArrayList<Integer>(this.schema);
				}
				System.out.println("result is : " + result + "  lastresult : " + lastResult);
				y--;
			}
			if (finalResult == null)
				finalResult = lastResult;
			else
			{
				if (lastResult < finalResult)
				{
					finalResult = lastResult;
					this.schema = tmpSchema;
					System.out.println("Not over yet");
				}
				else
				{
					better = true;
				}
			}
		}
		System.out.println("Result of TwoOpt algo : distance of " +  finalResult + " for the schema " + lastSchema);
	}
}
