package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import algorithm.HeuristiqueAlgo;
import algorithm.HillClimbing;
import algorithm.TSPAlgo;
import datastorage.Data;
import parser.ParserTSP;

public class Main {
	public static void main(String [] args) throws IOException
	{
		ParserTSP parser = new ParserTSP();

		parser.openFile("src/kroA100.tsp");
		parser.readAndParseFile();

		Data.getInstance().addCost();
		//Data.getInstance().showCost();

		List<Integer> schemaVille = new ArrayList<Integer>();
		schemaVille.add(2);
		schemaVille.add(1);
		schemaVille.add(4);
		schemaVille.add(3);
		schemaVille.add(8);
		/*schemaVille.add(10);
		schemaVille.add(12);
*/
		TSPAlgo algo = new TSPAlgo(schemaVille);
		algo.tspAlgorithm(Data.getInstance().getCostMap());
		//algo.supressUselessMatrice();
		Data.getInstance().showDataHill();
		
		HillClimbing hill = new HillClimbing(schemaVille, Data.getInstance().getHillClimbingData());
		hill.swap();
//		hill.algoRound();
		/*List<Integer> schemaVilleRand = new ArrayList<Integer>();
		int sizeRandVille = ThreadLocalRandom.current().nextInt(2, 10 + 1);
		int y = 0;
		while (y < sizeRandVille)
		{
			int nbRand = ThreadLocalRandom.current().nextInt(1, 100 + 1);
			if (schemaVille.contains(nbRand))
				nbRand = ThreadLocalRandom.current().nextInt(1, 100 + 1);	
			else
			{
				schemaVilleRand.add(nbRand);
				y++;
			}
		}
		System.out.println("Taille de schema aléatoire => " + sizeRandVille);
		String displschema = "Schema aléatoire : ";
		for (Integer i : schemaVilleRand)
		{
			displschema += i + "->" + " ";
		}
		displschema += schemaVilleRand.get(0);
		System.out.println(displschema);
		TSPAlgo algo2 = new TSPAlgo(schemaVilleRand);
		algo2.tspAlgorithm(Data.getInstance().getCostMap());

		HeuristiqueAlgo algoH = new HeuristiqueAlgo(20);
		int result = algoH.tspHeurisAlgorithm(Data.getInstance().getCostMap());
		System.out.println("Done, result of Heuristic is  : " + result);	*/
	}
}
