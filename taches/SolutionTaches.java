package taches;
import generic.SolutionPartielle;
import generic.Problem;

import java.util.ArrayList;

public class SolutionTaches extends SolutionPartielle {
	boolean[][] affectationTaches;

	int nbPersonnes;
	int nbTaches;
	ProblemeTaches problemeEnCours;
	int tempsTotal;
	int[] tempsTotaux;
	int tempsMax;
	int plusLongueTache;
	boolean[] tachesAffectees;

	public SolutionTaches(ProblemeTaches pb) {
		this.problemeEnCours = pb;
		this.nbPersonnes = pb.tempsTaches.length;
		this.nbTaches = pb.tempsTaches[0].length;
		this.affectationTaches = new boolean[this.nbPersonnes][this.nbTaches];
		this.tempsTotaux = new int[this.nbPersonnes];
		this.tempsMax = 0;
		this.tachesAffectees = new boolean[this.nbTaches];
	}

	public SolutionTaches(SolutionTaches origine) {
		this.nbTaches = origine.nbTaches;
		this.nbPersonnes = origine.nbPersonnes;
		this.problemeEnCours = origine.problemeEnCours;
		this.tempsMax = origine.tempsMax;
		this.tempsTotaux = origine.tempsTotaux.clone();
		this.tachesAffectees = origine.tachesAffectees.clone();

		this.affectationTaches = new boolean[this.nbPersonnes][this.nbTaches];
		for(int i = 0; i < nbPersonnes; i++){
			for(int j = 0; j < nbTaches; j++){
				this.affectationTaches[i][j] = origine.affectationTaches[i][j];
			}
		}
	}

	@Override
	public ArrayList<SolutionPartielle> solutionsVoisines() {
		ArrayList<SolutionPartielle> resultat = new ArrayList<>();

		for (int j = 0; j < nbTaches; j++) {
			if(tachesAffectees[j]){
				for(int i = 0; i < nbPersonnes; i++){
					if(this.affectationTaches[i][j]){
						SolutionTaches pasPris = new SolutionTaches(this);
						pasPris.nePasPrendre(i, j);

						resultat.add(pasPris);
					}
				}
			}
			else{
				for(int i = 0; i < nbPersonnes; i++){
					SolutionTaches pris = new SolutionTaches(this);
					pris.prendre(i, j);

					resultat.add(pris);
				}
			}
		}
		/* OLD VERSION
		if (estComplete()) {
			SolutionPartielle[] resultat = new SolutionPartielle[1];

			SolutionTaches pasPris = new SolutionTaches(this);

			for(int i = 0; i < nbPersonnes; i++){
				if(this.affectationTaches[i][this.tacheAAffecter-1]){
					pasPris.nePasPrendre(i);
				}
			}

			resultat[0] = pasPris;

			return resultat;
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
		}

		if(this.tacheAAffecter > 0){
			for(int i = 0; i < nbPersonnes; i++){
				if(this.affectationTaches[i][this.tacheAAffecter-1]){
					SolutionTaches pasPris = new SolutionTaches(this);
					pasPris.nePasPrendre(i);
					resultat[nbPersonnes] = pasPris;
				}
			}
		}
		*/

		return resultat;
	}

	public void prendre(int personne, int tache){
		this.affectationTaches[personne][tache] = true;
		this.tempsTotaux[personne] += this.problemeEnCours.tempsTaches[personne][tache];
		this.calculeTempsMax();
		this.tachesAffectees[tache] = true;
	}


	public void nePasPrendre(int personne, int tache){
		this.affectationTaches[personne][tache] = false;

		this.tempsTotaux[personne] -= this.problemeEnCours.tempsTaches[personne][tache];
		this.calculeTempsMax();

		this.tachesAffectees[tache] = false;
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

	public int tempsMaxAffectable(){
		int[] tempsTotauxMax = this.tempsTotaux.clone();

		/*
		for(int tache = tacheAAffecter; tache < nbTaches; tache++){
			int tpsMax = -1;
			int personneMax = 0;

			for(int personne = 0; personne < nbPersonnes; personne++){
				if(this.problemeEnCours.tempsTaches[personne][tache] > tpsMax){
					tpsMax = this.problemeEnCours.tempsTaches[personne][tache];
					personneMax = personne;
				}
			}

			tempsTotauxMax[personneMax] += tpsMax;
		}
		*/
		for(int tache = 0; tache < this.nbTaches; tache++){
			if(!tachesAffectees[tache]){
				int tpsMax = -1;
				int personneMax = 0;

				for(int personne = 0; personne < nbPersonnes; personne++){
					if(this.problemeEnCours.tempsTaches[personne][tache] > tpsMax){
						tpsMax = this.problemeEnCours.tempsTaches[personne][tache];
						personneMax = personne;
					}
				}

				tempsTotauxMax[personneMax] += tpsMax;
			}
		}

		int max = 0;

		for (int i = 0; i < nbPersonnes;  i++) {
			int val = tempsTotauxMax[i];
			if(val > max){
				max = val;
			}
		}

		return max;
	}

	@Override
	public boolean invalide() {
		return false;
	}

	@Override
	public boolean estComplete() {
		boolean result = true;

		for(boolean tache : this.tachesAffectees){
			result &= tache;
		}

		return result;
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
	* permet d'afficher le tableau sous forme "personne" prend "liste d'objets"
	*/
	public void afficheListe(){
		for(int i = 0; i < this.nbPersonnes; i++){
			System.out.printf("La personne %d prend les tâches : ", i + 1);
			for(int j = 0; j < nbTaches; j++){
				if(affectationTaches[i][j]){
					System.out.printf("%d ", j + 1);
				}
			}
			System.out.println("");
		}

		System.out.println("");
	}

	public void afficheDiagramme(){
		for(int i = 0; i < this.nbPersonnes; i++){
			System.out.printf("La personne %d prend les tâches : ", i + 1);
			for(int j = 0; j < nbTaches; j++){
				if(affectationTaches[i][j]){
					for(int k = 0; k < this.problemeEnCours.tempsTaches[i][j]; k++){
						System.out.print("X");
					}
				}
			}
			System.out.println("");
		}

		System.out.println("");		
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
