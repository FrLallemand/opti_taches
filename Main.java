import generic.AlgorithmeAbstract;
import generic.Problem;
import generic.SolutionPartielle;
import algorithmes.AlgorithmeGreedy;
import taches.ProblemeTaches;
import taches.SolutionTaches;


public class Main {

	public static void main(String args [])
	{
		//creation du probleme
		Problem probleme=ProblemeTaches.initialiseProblemeSimple();

		AlgorithmeGreedy greedy =new AlgorithmeGreedy(probleme);
		SolutionTaches resultat=(SolutionTaches) greedy.meilleureSolution();
		resultat.afficheListe();
	}
}
