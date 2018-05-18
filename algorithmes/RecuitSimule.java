package algorithmes;

import generic.AlgorithmeAbstract;
import generic.Problem;
import generic.SolutionPartielle;

import java.util.Random;
import java.lang.Math;
import java.util.ArrayList;

public class RecuitSimule extends AlgorithmeAbstract {
	public final double DELTA_TEMPERATURE = 0.99;

	double temperature;
	double current_value;
	int nb_iters;

	public RecuitSimule(Problem p, double temperature, int nb_iters) {
		super(p);
		this.temperature = temperature;
		this.current_value = p.evaluer(p.solutionInitiale());
		this.nb_iters = nb_iters;
	}

	/*
	* Fonctionnement du recuit simulé:
	*  À chaque itération on choisit un voisin au pif
	*  si la solution est meilleure que la solution actuelle
	*  on la prend, sinon on la prend avec une proba proportionelle
	*  à la température. La température décroit au cours du temps
	*/
	@Override
	public SolutionPartielle meilleureSolution() {
		int nb_eval = 0;
		SolutionPartielle s = problemeAResoudre.solutionInitiale();

		for(int i = 0; i < nb_iters; i++){
			SolutionPartielle s_random = this.solutionHasard(s);
			nb_eval++;
			double eval_srandom = problemeAResoudre.evaluer(s_random);

			if(estAcceptee(s_random, eval_srandom)){
				s = s_random;
				current_value = eval_srandom;
			}

			temperature = DELTA_TEMPERATURE * temperature;
		}

		return s;
	}

	public SolutionPartielle solutionHasard(SolutionPartielle s){
		ArrayList<SolutionPartielle> voisins = s.solutionsVoisines();

		Random rng = new Random();

		return voisins.get(rng.nextInt(voisins.size()));
	}

	public boolean estAcceptee(SolutionPartielle s, double value){
		if(value > current_value){
			return true;
		}
		else{
			Random rng = new Random();

			return probaMetropolis(value - current_value) > rng.nextDouble();
		}
	}

	private double probaMetropolis(double deltaValeur) {
		return Math.exp(-deltaValeur / this.temperature);
	}

}