package taches;
import generic.SolutionPartielle;
import generic.Problem;

public class SolutionTaches extends SolutionPartielle {
	boolean[][] affectationTaches;

	int nbPersonnes;
	int nbTaches;
	ProblemeTaches problemeEnCours;
	int tempsTotal;
	int tacheAAffecter;
	int[] tempsTotaux;
	int tempsMax;


	public SolutionTaches(ProblemeTaches pb) {
		this.problemeEnCours = pb;
		this.nbPersonnes = pb.tempsTaches.length;
		this.nbTaches = pb.tempsTaches[0].length;
		this.affectationTaches = new boolean[this.nbPersonnes][this.nbTaches];
		this.tempsTotaux = new int[this.nbPersonnes];
		this.tempsMax = 0;
		this.tacheAAffecter = 0;
	}

	public SolutionTaches(SolutionTaches origine) {
		this.affectationTaches = origine.affectationTaches.clone();
		this.nbTaches = origine.nbTaches;
		this.nbPersonnes = origine.nbPersonnes;
		this.problemeEnCours = origine.problemeEnCours;
		this.tempsMax = origine.tempsMax;
		this.tempsTotaux = origine.tempsTotaux;
		this.tacheAAffecter = origine.tacheAAffecter;
	}

	@Override
	public SolutionPartielle[] solutionsVoisines() {
		if (estComplete()) {
			return (new SolutionPartielle[0]);
		}

		// construire les solutions
		SolutionPartielle[] resultat;
		if(this.tacheAAffecter > 0){
			resultat = new SolutionPartielle[nbPersonnes+1];
		} else {
			resultat = new SolutionPartielle[nbPersonnes];
		}

		for(int i=0; i<nbPersonnes; i++){
			SolutionTaches solutionPris = new SolutionTaches(this);
			solutionPris.prendre(i);
			resultat[i] = solutionPris;
			if(this.tacheAAffecter > 0){
				if(this.affectationTaches[i][this.tacheAAffecter-1]){
					SolutionTaches solutionNonPris = new SolutionTaches(this);
					solutionNonPris.nePasPrendre(i);
					resultat[nbPersonnes] = solutionNonPris;
				}
			}
		}

		return resultat;
	}

	private void prendre(int personne){
		this.affectationTaches[personne][this.tacheAAffecter] = true;
		this.tempsTotaux[personne] += this.problemeEnCours.tempsTaches[personne][this.tacheAAffecter];
		this.calculeTempsMax();
		this.tacheAAffecter++;
	}


	private void nePasPrendre(int personne){
		this.tacheAAffecter--;
		this.affectationTaches[personne][this.tacheAAffecter] = false;

		this.tempsTotaux[personne] += this.problemeEnCours.tempsTaches[personne][this.tacheAAffecter];
		this.calculeTempsMax();
	}

	private void calculeTempsMax(){
		int max = 0;
		for (int i = 0; i<this.tempsTotaux.length;  i++) {
			int val = this.tempsTotaux[i];
			if(val > max){
				max = val;
			}
		}

		this.tempsMax = max;
	}

	@Override
	public boolean invalide() {
		return false;
	}

	@Override
	public boolean estComplete() {
		return (this.tacheAAffecter == this.nbTaches);
	}


	/**
	 * permet d'afficher le tableau sous forme matricielle
	 */
	public static String affiche(int[][] tab)
		{
			String res="";
			for (int j=0;j<tab[0].length;j++) {
				for (int i=0;i<tab.length;i++) {
					res+=(tab[i][j]+" ");
				}
				res+="\n";
			}
			return res;
		}

	/**
	 * permet d'afficher le tableau sous forme afficheLatex
	 */
	public static String afficheLatex(int[][] tab)
		{
			String res="$$ \n\\begin{pmatrix}\n";
			for (int j=0;j<tab[0].length;j++) {
				res+=tab[0][j];
				for (int i=1;i<tab.length;i++) {
					res+=" & " + tab[i][j];
				}
				res+="\\\\\n";
			}
			res+="\\end{pmatrix} \n$$";
			return res;
		}

	/**
	 * permet d'afficher le tableau sous forme java
	 */
	public String afficheJava()
		{
			boolean tab[][] = this.affectationTaches;
			String res="int[][] tab={\n";
			for (int i=0;i<tab.length;i++) {
				res+="  {";
				res+=tab[i][0];
				for (int j=1;j<tab[0].length;j++) {
					res+=","+tab[i][j];
				}
				if (i!=tab.length-1)
					res+="},\n";
				else
					res+="}\n";
			}
			res+="}";
			return res;
		}

}
