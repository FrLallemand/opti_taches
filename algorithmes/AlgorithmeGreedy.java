package algorithmes;

import generic.AlgorithmeAbstract;
import generic.Problem;
import generic.SolutionPartielle;
import taches.SolutionTaches;

import java.io.Writer;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;

public class AlgorithmeGreedy extends AlgorithmeAbstract {

	private final static String OUTPUT_FILE = "greedy.csv";

	public AlgorithmeGreedy(Problem p) {
		super(p);
	}

	@Override
	public SolutionPartielle meilleureSolution() {
		int nb_eval = 0;
		int nb_step = 0;
		SolutionPartielle enCours= this.problemeAResoudre.solutionInitiale();

		writeStepToCSV(nb_step, this.problemeAResoudre.evaluer(enCours));

		while(!enCours.estComplete()) {
			nb_step++;
			
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

			writeStepToCSV(nb_step, best);

		}
		System.out.printf("Nombre d'évaluations : %d\n", nb_eval);
		return enCours;
	}


	public void writeStepToCSV(int step, double solution_value){
		BufferedWriter output = null;

		try {
			File file = new File(OUTPUT_FILE);
			output = new BufferedWriter(new FileWriter(file, true));
			// System.out.println("plop");
			String s = String.format("%d;%f\n", step, solution_value);
			output.write(s);

			output.close();
		}
		catch(Exception e){
			//
		}
	}
}
