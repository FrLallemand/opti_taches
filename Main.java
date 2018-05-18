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
		ProblemeTaches probleme_simple = (ProblemeTaches) ProblemeTaches.initialiseProblemeSimple();
		ProblemeTaches probleme_moinssimple = (ProblemeTaches) ProblemeTaches.initialiseProblemeMoinsSimple();
		ProblemeTaches probleme_complexe = (ProblemeTaches) ProblemeTaches.initialiseProblemeComplexe();


		// GREEDY
		AlgorithmeGreedy greedy_simple = new AlgorithmeGreedy(probleme_simple);
		double val_greedy_simple = probleme_simple.evaluer(greedy_simple.meilleureSolution());

		AlgorithmeGreedy greedy_moinssimple = new AlgorithmeGreedy(probleme_moinssimple);
		double val_greedy_moinssimple = probleme_moinssimple.evaluer(greedy_moinssimple.meilleureSolution());

		AlgorithmeGreedy greedy_complexe = new AlgorithmeGreedy(probleme_complexe);
		double val_greedy_complexe = probleme_complexe.evaluer(greedy_complexe.meilleureSolution());


		// Recuit simulé
		final int iters = 1000;
		double values_somme = 0;

		/*
		for(int i = 0; i < iters; i++){
			System.out.printf("%d ", i);
			RecuitSimule recuitSimule = new RecuitSimule(probleme_complexe, 1000, 1000);
			SolutionTaches s = (SolutionTaches) recuitSimule.meilleureSolution();
			values_somme += probleme_complexe.evaluer(s);
		}

		System.out.printf("\nMoyenne : %f\n", values_somme / iters);
		*/

		// ILS

		int nb_iters = 0;
		double value_obtained = val_greedy_moinssimple;

		SolutionTaches s = (SolutionTaches) probleme_moinssimple.solutionInitiale();

		while(value_obtained >= val_greedy_moinssimple){
			SolutionTaches s_copy = new SolutionTaches(s);
			s_copy.perturbationUniforme();
			probleme_moinssimple.setSolutionInitiale(s_copy);

			RecuitSimule recuitSimule = new RecuitSimule(probleme_moinssimple, 1000, 100);
			SolutionTaches resultat = (SolutionTaches) recuitSimule.meilleureSolution();

			if(probleme_moinssimple.evaluer(resultat) < probleme_moinssimple.evaluer(s)){
				System.out.printf("Step %d value %f\n",nb_iters,  value_obtained);
				s = resultat;
			}

			value_obtained = probleme_moinssimple.evaluer(s);
			nb_iters++;
		}

		System.out.printf("résultat : %f en %d étapes d'ILS\n", value_obtained, nb_iters);
		// s.afficheDiagramme();
		// s.afficheListe();
	}
}
