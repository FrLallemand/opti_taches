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
		SolutionPartielle enCours= this.problemeAResoudre.solutionInitiale();
		while(!enCours.estComplete()) {
			double best = -1;
			for (SolutionPartielle solutionPartielle : enCours.solutionsVoisines()) {
				double solution_value = this.problemeAResoudre.evaluer(solutionPartielle);
				if(this.problemeAResoudre.evaluer(solutionPartielle) >= best) {
					best = solution_value;
					enCours = solutionPartielle;
				}
				compteur++;
			}
		}
//		System.out.println(compteur);
		return enCours;
	}

}
