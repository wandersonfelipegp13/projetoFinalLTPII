package programa1;

public class Beneficiario implements Comparable<Beneficiario> {

	private String nome;
	private int idade;
	private int qnteDep;
	private Dependente deps[];
	private double valPlano;

	public Beneficiario() {
	}

	public Beneficiario(String nome, int idade, int qnteDep, double valPlano) {
		this.setNome(nome);
		this.setIdade(idade);
		this.setQnteDep(qnteDep);
		this.setValPlano(valPlano);
	}

	/**
	 * Metodo para adicionar Dependentes ao Beneficiario em questao
	 * 
	 * @param nome
	 * @param idade
	 */
	public void addDep(String nome, int idade) {

		// O vetor de Dependente eh inicializado com a quantidade de deps
		if (this.deps == null)
			this.deps = new Dependente[this.qnteDep];

		for (int j = 0; j < this.qnteDep; j++)
			if (this.deps[j] == null) {
				this.deps[j] = new Dependente(nome, idade);
				break;
			}
	}

	/**
	 * Metodo para mostrar os dependentes do Beneficiario em questao
	 */
	public String showDeps() {
		if (this.deps != null) {
			String resp = "[";
			int i;
			for (i = 0; i < this.qnteDep - 1; i++)
				resp += this.deps[i].toString() + ";";
			resp += this.deps[i] + "]";
			return resp;
		}
		return null;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Dependente[] getDeps() {
		return deps;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public int getQnteDep() {
		return qnteDep;
	}

	public void setQnteDep(int qnteDep) {
		this.qnteDep = qnteDep;
	}

	public double getValPlano() {
		return valPlano;
	}

	public void setValPlano(double valPlano) {
		this.valPlano = valPlano;
	}

	@Override
	public String toString() {
		return nome + "\t" + idade + "\t" + qnteDep + "\t" + this.showDeps() + "\t" + valPlano;
	}

	@Override
	public int compareTo(Beneficiario b) {
		return this.nome.compareTo(b.getNome());
	}

}
