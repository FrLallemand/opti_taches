package taches;
import generic.SolutionPartielle;
import generic.Problem;


public class ProblemeTaches extends Problem {

	boolean[][] affectationTaches;
	int[][] tempsTaches;

	private ProblemeTaches(int[][] tabTaches){
		this.tempsTaches = tabTaches;
		this.affectationTaches = new boolean[tempsTaches.length][tempsTaches[0].length];
	}

	static public Problem initialiseProblemeSimple() {
		int[][] tabSimple=
			{
				{2,1,3,1,4},
				{3,4,4,2,3},
				{6,2,5,2,5},
				{4,7,2,1,4}
			};
		ProblemeTaches probleme = new ProblemeTaches(tabSimple);
		return (probleme);
	}


	static public Problem initialiseProblemeMoinsSimple() {
		int[][] tabMoinsSimple=
			{
				{2,1,3,1,4,6,2,3,5,4},
				{3,4,4,2,3,4,2,6,2,6},
				{6,2,5,2,5,3,7,3,6,4},
				{4,7,2,1,4,8,6,7,3,8},
				{6,6,5,3,2,5,8,6,5,6},
				{5,4,3,2,3,5,8,3,4,4}
			};
		ProblemeTaches probleme = new ProblemeTaches(tabMoinsSimple);
		return (probleme);
	}

	static public Problem initialiseProblemeComplexe() {
		int[][] tabComplexe={
			{8,5,7,6,4,4,5,9,4,4,3,1,6,8,8,9,7,4,4,4},
			{2,5,8,2,5,1,7,8,9,2,5,3,7,6,6,1,4,7,5,7},
			{9,4,7,4,9,1,9,1,9,1,9,2,3,1,9,7,7,8,9,3},
			{6,4,8,2,7,8,3,5,6,8,4,3,5,8,2,9,5,7,7,6},
			{2,1,8,1,8,7,3,7,7,3,2,6,6,2,3,7,4,3,7,3},
			{4,7,7,1,6,8,3,1,9,8,3,3,3,2,2,2,3,9,2,6},
			{3,2,7,1,9,5,6,5,8,4,4,7,6,5,5,1,5,1,2,6},
			{9,7,9,1,9,8,9,4,1,5,6,2,4,9,9,7,1,4,5,2},
			{1,6,8,1,9,3,5,8,9,5,2,1,9,8,2,8,4,4,9,1},
			{8,8,7,1,2,6,4,6,7,6,7,1,2,5,2,4,4,9,5,7}
		};
		ProblemeTaches probleme = new ProblemeTaches(tabComplexe);
		return (probleme);
	}

	static public Problem initialiseProblemeAlea() {
		int[][]alea=getTab(10,20);
		ProblemeTaches probleme = new ProblemeTaches(alea);
		return (probleme);
	}

	/**
	 * creation tableau aleatoire
	 */
	public static int[][] getTab(int n,int m)
		{
			int[][] tab=new int[n][m];
			for (int i=0;i<n;i++)
				for (int j=0;j<m;j++)
					tab[i][j]=(int)(Math.random()*9)+1;
			return tab;
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
	public static String afficheJava(int[][] tab)
		{
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

	@Override
	public SolutionPartielle solutionInitiale() {
		return new SolutionTaches(this);
	}

	@Override
	public double evaluer(SolutionPartielle s) {
		// on retourne simplement la valeur stockee
		return (((SolutionTaches) (s)).valeurActuelle);
	}


}
