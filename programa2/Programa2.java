package programa2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import javax.swing.JOptionPane;
import programa1.Beneficiario;

public class Programa2 {

	private static List<Beneficiario> listaOrd = new ArrayList<Beneficiario>();

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
				int n = Integer.parseInt(JOptionPane.showInputDialog(null, "Posição"));
				listaOrd.remove(n);
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

		try {
			BufferedReader in = new BufferedReader(new FileReader("src/arquivo1.txt"));
			String linha = in.readLine();
			while (linha != null) {

				Beneficiario b = new Beneficiario();

				listaOrd.add(b);

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
			for (int i = 0; i < listaOrd.size(); i++) {
				out.print(listaOrd.get(i).getNome() + "\n" + listaOrd.get(i).getIdade() + "\n"
						+ listaOrd.get(i).getQnteDep() + "\n");
				for (int j = 0; listaOrd.get(i).getDeps() != null && j < listaOrd.get(i).getQnteDep(); j++) {
					out.print(listaOrd.get(i).getDeps()[j].getNome() + "\n" + listaOrd.get(i).getDeps()[j].getIdade()
							+ "\n");
					System.out.println(listaOrd.get(i).getDeps()[j].getNome() + "\n"
							+ listaOrd.get(i).getDeps()[j].getIdade() + "\n");
				}
				out.print(listaOrd.get(i).getValPlano() + "\n");
			}
			out.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	private static void addben() {

		String nomeB = JOptionPane.showInputDialog(null, "Nome: ");

		int idadeB = Integer.parseInt(JOptionPane.showInputDialog(null, "Idade: "));
		int qnt = Integer.parseInt(JOptionPane.showInputDialog(null, "Quantidade de dependentes: "));
		double val = Double.parseDouble(JOptionPane.showInputDialog(null, "Valor do plano: "));

		Beneficiario b = new Beneficiario(nomeB, idadeB, qnt, val);

		for (int i = 0; i < qnt; i++) {

			String nomeD = JOptionPane.showInputDialog(null, "Nome Dependente " + (i + 1) + ": ");
			int idadeD = Integer.parseInt(JOptionPane.showInputDialog(null, "Idade Dependente " + (i + 1) + ": "));

			b.addDep(nomeD, idadeD);

		}

		listaOrd.add(b);

	}

	private static void mediaIdades() {

		System.out.println(
				"Empregados com a maior e a menor média de idades entre seus integrantes (beneficiário e dependentes)");

		int maior = listaOrd.get(0).getIdade();
		String maiorNome = listaOrd.get(0).getNome();
		int menor = listaOrd.get(0).getIdade();
		String menorNome = listaOrd.get(0).getNome();

		for (int i = 0; i < listaOrd.size(); i++) {
			if (listaOrd.get(i).getIdade() > maior) {
				maior = listaOrd.get(i).getIdade();
				maiorNome = listaOrd.get(i).getNome();
			}
			if (listaOrd.get(i).getIdade() < menor) {
				menor = listaOrd.get(i).getIdade();
				menorNome = listaOrd.get(i).getNome();
			}
			for (int j = 0; j < listaOrd.get(i).getQnteDep(); j++) {

				if (listaOrd.get(i).getDeps()[j].getIdade() > maior) {
					maior = listaOrd.get(i).getDeps()[j].getIdade();
					maiorNome = listaOrd.get(i).getDeps()[j].getNome();
				}
				if (listaOrd.get(i).getDeps()[j].getIdade() < menor) {
					menor = listaOrd.get(i).getDeps()[j].getIdade();
					menorNome = listaOrd.get(i).getDeps()[j].getNome();
				}
			}
		}

		System.out.println("Mais velho: " + maiorNome + ", Mais novo: " + menorNome);

	}

	private static void att() {

		System.out.println("Atualizar todos os beneficiários com aumento de 5% no valor do plano.");

		for (int i = 0; i < listaOrd.size(); i++)
			listaOrd.get(i).setValPlano(listaOrd.get(i).getValPlano() * 1.05);

	}

	private static void acima40() {

		System.out.println("Beneficiários e dependentes que possuem idades acima de 40 anos.");

		for (int i = 0; i < listaOrd.size(); i++) {
			if (listaOrd.get(i).getIdade() > 40) {
				System.out.println(listaOrd.get(i).getNome());
			}
			for (int j = 0; j < listaOrd.get(i).getQnteDep(); j++) {

				if (listaOrd.get(i).getDeps() != null && listaOrd.get(i).getDeps()[j].getIdade() > 40) {
					System.out.println(listaOrd.get(i).getDeps()[j].getNome());
				}
			}
		}
	}

	private static void relatorio() {
		try {
			Formatter output = new Formatter("src/arquivo2.txt");
			output.format("%s\t%s\n", "Nome do Beneficiário", "Valor do plano");
			for (int i = 0; i < listaOrd.size(); i++) {
				output.format("%s\t\t\t\t\t%.2f\n", listaOrd.get(i).getNome(), listaOrd.get(i).getValPlano());
			}
			output.flush();
			output.close();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}

	}

}
