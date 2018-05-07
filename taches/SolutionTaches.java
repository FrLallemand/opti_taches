package taches;
import generic.SolutionPartielle;
import generic.Problem;

public class SolutionTaches extends SolutionPartielle {
	boolean[][] affectationTaches;

	ProblemeTaches problemeEnCours;
	int valeurActuelle;

	public SolutionTaches(ProblemeTaches pb) {
		this.problemeEnCours = pb;
		this.affectationTaches = new boolean[pb.tempsTaches.length][pb.tempsTaches[0].length];
	}

		@Override
	public SolutionPartielle[] solutionsVoisines() {
		return null;
	}

	@Override
	public boolean invalide() {
		return true;
	}

	@Override
	public boolean estComplete() {
		return true;
	}



}
