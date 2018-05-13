import generic.AlgorithmeAbstract;
import generic.Problem;
import generic.SolutionPartielle;
import algorithmes.AlgorithmeGreedy;
import algorithmes.RecuitSimule;
import taches.ProblemeTaches;
import taches.SolutionTaches;


public class Main {

	public static void main(String args [])
	{
		//creation du probleme
		Problem probleme=ProblemeTaches.initialiseProblemeComplexe();

		/*
		AlgorithmeGreedy greedy =new AlgorithmeGreedy(probleme);
		SolutionTaches resultat=(SolutionTaches) greedy.meilleureSolution();
		*/

		RecuitSimule recuitSimule = new RecuitSimule(probleme, 1000, 10000);
		SolutionTaches resultat = (SolutionTaches) recuitSimule.meilleureSolution();
		resultat.afficheDiagramme();
	}
}
