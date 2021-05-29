package programa1;

public class Dependente {

	private String nome;
	private int idade;
	
	public Dependente(String nome, int idade) {
		this.setNome(nome);
		this.setIdade(idade);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	@Override
	public String toString() {
		return nome + "," + idade;
	}

}
