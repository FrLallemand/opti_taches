package algorithmes;

import generic.AlgorithmeAbstract;
import generic.Problem;
import generic.SolutionPartielle;

public class AlgorithmeGreedy extends AlgorithmeAbstract {

	public AlgorithmeGreedy(Problem p) {
		super(p);
	}

	@Override
	public SolutionPartielle meilleureSolution() {
		int nb_eval = 0;
		SolutionPartielle enCours= this.problemeAResoudre.solutionInitiale();
		while(!enCours.estComplete()) {
			double best = Double.MAX_VALUE;
			for (SolutionPartielle solutionPartielle : enCours.solutionsVoisines()) {
				double solution_value = this.problemeAResoudre.evaluer(solutionPartielle);
				nb_eval++;
				if(this.problemeAResoudre.evaluer(solutionPartielle) < best) {
					best = solution_value;
					enCours = solutionPartielle;
				}
				compteur++;
			}
		}
		System.out.printf("Nombre d'évaluations : %d\n", nb_eval);
		return enCours;
	}

}
