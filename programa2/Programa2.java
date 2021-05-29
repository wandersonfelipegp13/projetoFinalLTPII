package programa2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Formatter;

import javax.swing.JOptionPane;

import programa1.Beneficiario;

public class Programa2 {

	private static Beneficiario vetBen[];
	// variavel para guardar a quantidade de Beneficiarios
	private static int tam = 0;

	public static void main(String[] args) {

		ler();

		String menu = "1 - Inserir\n2 - Remover\n3 - Acima dos 40\n4 - [+5%]\n5 - Elder and Yonger\n6 - Relatorio\n7 - Sair";
		int op = 1;

		while (op != 7) {

			op = Integer.parseInt(JOptionPane.showInputDialog(null, menu));

			switch (op) {
			case 1:
				addben();
				break;
			case 2:
				remove(3);
				break;
			case 3:
				acima40();
				break;
			case 4:
				att();
				break;
			case 5:
				mediaIdades();
				break;
			case 6:
				relatorio();
				break;
			}

		}

		grava();

	}

	private static void ler() {

		vetBen = new Beneficiario[5];

		try {
			BufferedReader in = new BufferedReader(new FileReader("src/arquivo1.txt"));
			String linha = in.readLine();
			while (linha != null) {
				
				/*
				Joao	25	2	"Ana,12,Marcos,11"	13230

				String s[] = linha.split("\t");
				s[0] = "Joao"
				s[1] = "25"
				s[2] = "2"
				s[3] = "[Ana,12,Marcos,11]"
				s[4] = "13230"
				
				 0   1   2    3    4    5 
				Ana,12,Marcos,11,SJSJS, 23 
				
				String deps[] = s[3].split(","); 
				for(2, i++)
					nomed = deps[i];
					idaded = deps[i+1];
					b.addDep(nomed, idaded);
					i++;
				 
				*/
				
				Beneficiario b = new Beneficiario();

				add(b);

				String nome = linha;
				b.setNome(nome);
				linha = in.readLine();
				int idade = Integer.parseInt(linha);
				b.setIdade(idade);
				linha = in.readLine();
				int qnt = Integer.parseInt(linha);
				b.setQnteDep(qnt);
				linha = in.readLine();

				for (int i = 0; i < qnt; i++) {
					String nomed = linha;
					linha = in.readLine();
					int idaded = Integer.parseInt(linha);
					linha = in.readLine();

					b.addDep(nomed, idaded);
				}

				Double val = Double.parseDouble(linha);
				b.setValPlano(val);
				linha = in.readLine();

			}
			in.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void grava() {
		try {
			PrintWriter out = new PrintWriter(new FileWriter("src/arquivo1.txt"));
			
			/*
			while(i < tam)
				[A, null, C, null, null]
				if(vet[i] != null)
					// operaçoes
					i++;
			*/
			
			for (int i = 0; vetBen[i] != null && i < vetBen.length; i++) {
				out.print(vetBen[i].getNome() + "\n" + vetBen[i].getIdade() + "\n" + vetBen[i].getQnteDep() + "\n");
				for (int j = 0; vetBen[i].getDeps() != null && j < vetBen[i].getQnteDep(); j++) {
					out.print(vetBen[i].getDeps()[j].getNome() + "\n" + vetBen[i].getDeps()[j].getIdade() + "\n");
				}
				out.print(vetBen[i].getValPlano() + "\n");
			}
			out.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	private static void remove(int MCposi) {

		vetBen[MCposi] = null;
		tam--;

	}

	private static void addben() {
		String nomeB = "vazio";

		while (!nomeB.equals("xxx") && !nomeB.equals("XXX") && vetBen.length > tam) {

			nomeB = JOptionPane.showInputDialog(null, "Nome: ");

			if (!nomeB.equals("xxx") && !nomeB.equals("XXX")) {

				int idadeB = Integer.parseInt(JOptionPane.showInputDialog(null, "Idade: "));
				int qnt = Integer.parseInt(JOptionPane.showInputDialog(null, "Quantidade de dependentes: "));
				double val = Double.parseDouble(JOptionPane.showInputDialog(null, "Valor do plano: "));

				Beneficiario b = new Beneficiario(nomeB, idadeB, qnt, val);

				for (int i = 0; i < qnt; i++) {

					String nomeD = JOptionPane.showInputDialog(null, "Nome Dependente " + (i + 1) + ": ");
					int idadeD = Integer
							.parseInt(JOptionPane.showInputDialog(null, "Idade Dependente " + (i + 1) + ": "));

					b.addDep(nomeD, idadeD);

				}

				// O for abaixo garante que um Beneficiario novo seja alocado na primeira
				// posicao nulla do vetBen
				add(b);

			}

		}

	}

	private static void mediaIdades() {

		System.out.println(
				"Empregados com a maior e a menor média de idades entre seus integrantes (beneficiário e dependentes)");

		int maior = vetBen[0].getIdade();
		String maiorNome = vetBen[0].getNome();
		int menor = vetBen[0].getIdade();
		String menorNome = vetBen[0].getNome();

		for (int i = 0; i < vetBen.length; i++) {
			if (vetBen[i] != null && vetBen[i].getIdade() > maior) {
				maior = vetBen[i].getIdade();
				maiorNome = vetBen[i].getNome();
			}
			if (vetBen[i] != null && vetBen[i].getIdade() < menor) {
				menor = vetBen[i].getIdade();
				menorNome = vetBen[i].getNome();
			}
			for (int j = 0; vetBen[i] != null && j < vetBen[i].getQnteDep(); j++) {

				if (vetBen[i] != null && vetBen[i].getDeps()[j].getIdade() > maior) {
					maior = vetBen[i].getDeps()[j].getIdade();
					maiorNome = vetBen[i].getDeps()[j].getNome();
				}
				if (vetBen[i] != null && vetBen[i].getDeps()[j].getIdade() < menor) {
					menor = vetBen[i].getDeps()[j].getIdade();
					menorNome = vetBen[i].getDeps()[j].getNome();
				}
			}
		}

		System.out.println("Mais velho: " + maiorNome + ", Mais novo: " + menorNome);

	}

	private static void att() {

		System.out.println("Atualizar todos os beneficiários com aumento de 5% no valor do plano.");

		for (int i = 0; i < vetBen.length; i++) {
			if (vetBen[i] != null)
				vetBen[i].setValPlano(vetBen[i].getValPlano() * 1.05);
		}

	}

	private static void acima40() {

		System.out.println("Beneficiários e dependentes que possuem idades acima de 40 anos.");

		for (int i = 0; i < vetBen.length; i++) {
			if (vetBen[i] != null && vetBen[i].getIdade() > 40) {
				System.out.println(vetBen[i].getNome());
			}
			for (int j = 0; vetBen[i] != null && j < vetBen[i].getQnteDep(); j++) {

				if (vetBen[i].getDeps() != null && vetBen[i].getDeps()[j].getIdade() > 40) {
					System.out.println(vetBen[i].getDeps()[j].getNome());
				}
			}
		}
	}

	private static void relatorio() {
		try {
			Formatter output = new Formatter("src/arquivo2.txt");
			output.format("%s\t%s\n", "Nome do Beneficiário", "Valor do plano");
			for (int i = 0; i < vetBen.length; i++) {
				if (vetBen[i] != null) {
					output.format("%s\t\t\t\t\t%.2f\n", vetBen[i].getNome(), vetBen[i].getValPlano());
				}
			}
			output.flush();
			output.close();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * Metodo para adicionar um Beneficiario b no vetBen
	 * 
	 * @param b
	 */
	private static void add(Beneficiario b) {
		// acho uma posicao nulla no vetor e coloco esse novo Beneficiario
		for (int i = 0; i < vetBen.length; i++) {
			if (vetBen[i] == null) {
				vetBen[i] = b;
				tam++;
				break;
			}
		}
	}

}


