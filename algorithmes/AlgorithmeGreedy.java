package algorithmes;

import java.util.Random;

import generic.AlgorithmeAbstract;
import generic.Problem;
import generic.SolutionPartielle;
import sacADos.SolutionSacADos;

public class AlgorithmeGreedy extends AlgorithmeAbstract {

	double alpha;

	public AlgorithmeGreedy(Problem p, double a) {
		super(p);
		alpha = a;
	}

	@Override
	public SolutionPartielle meilleureSolution() {
		SolutionPartielle enCours=  this.problemeAResoudre.solutionInitiale();
		int t = 0;
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
			t++;
			if(t % 10 == 0) {
				System.out.println("Itérations : " + t + " Solution courante : " + enCours + " ");
			}
		}
		return enCours;
	}

	private SolutionSacADos accept(SolutionSacADos enCours, SolutionSacADos x) {
		if(! x.invalide()) {
			return x;
		}
		return enCours;
	}

}
