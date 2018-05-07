import generic.AlgorithmeAbstract;
import generic.Problem;
import generic.SolutionPartielle;
import taches.ProblemeTaches;


public class Main {

	public static void main(String args [])
	{


		//creation du probleme
		Problem probleme=ProblemeTaches.initialiseProblemeSimple();

		/*
		//creation algorithme
		AlgorithmeGreedy greedyAleatoire =new AlgorithmeGreedy(probleme, 0.1);
		ILS greedyILS =new ILS(probleme, 0.1);

		//resultat
		//SolutionPartielle resultat=greedyAleatoire.meilleureSolution();
		for(int i=0; i<100; i++) {
			SolutionPartielle resultat= greedyILS.meilleureSolution();
			if(i % 10 == 0) {
			System.out.println("Itérations : " + i + " Solution courante : " + resultat + " ");
		//System.out.println(resultat);
			}
		}
		*/
	}
}
