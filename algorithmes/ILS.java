package algorithmes;

import java.util.Random;

import generic.AlgorithmeAbstract;
import generic.Problem;
import generic.SolutionPartielle;
import sacADos.SolutionSacADos;

public class ILS extends AlgorithmeAbstract {

	double alpha;

	public ILS(Problem p, double a) {
		super(p);
		alpha = a;
	}

	public SolutionPartielle algoLS(SolutionPartielle enCours) {
		while(!enCours.estComplete()) {
			SolutionPartielle[] N = enCours.solutionsVoisines();
			Random rand = new Random();
			double rnd = rand.nextDouble();
			if(rnd < this.alpha) {
				// recherche aléatoire
				int rndSol = rand.nextInt(N.length);
				enCours = N[rndSol];

			} else {
				// recherche gloutonne
				double best = -1;
				for (SolutionPartielle solutionPartielle : N) {
					double solution_value = this.problemeAResoudre.evaluer(solutionPartielle);
					if(this.problemeAResoudre.evaluer(solutionPartielle) > best) {
						best = solution_value;
						enCours = solutionPartielle;
					}
				}
			}
		}
		return enCours;
	}

	public SolutionPartielle meilleureSolution() {
		SolutionSacADos enCours= (SolutionSacADos) this.algoLS(this.problemeAResoudre.solutionInitiale());

			SolutionSacADos p = enCours.perturb();
			SolutionSacADos x = (SolutionSacADos) this.algoLS(p);
			enCours = this.accept(enCours, x);

		return enCours;
	}

	private SolutionSacADos accept(SolutionSacADos enCours, SolutionSacADos x) {
		if(this.problemeAResoudre.evaluer(enCours) > this.problemeAResoudre.evaluer(x)) {
			return enCours;
		}
		return x;
	}

}
