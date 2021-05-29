package programa1;

import java.io.FileWriter;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

public class Programa1 {

	public static void main(String[] args) {

		Beneficiario vetBen[] = new Beneficiario[2];
		int tam = 0;

		String nomeB = "vazio";

		while (!nomeB.equals("xxx") && !nomeB.equals("XXX") && tam < vetBen.length) {

			nomeB = JOptionPane.showInputDialog(null, "Nome: ");

			if (!nomeB.equals("xxx") && !nomeB.equals("XXX")) {
				
				int idadeB = Integer.parseInt(JOptionPane.showInputDialog(null, "Idade: "));
				int qnt = Integer.parseInt(JOptionPane.showInputDialog(null, "Quantidade de dependentes: "));
				double val = Double.parseDouble(JOptionPane.showInputDialog(null, "Valor do plano: "));
				
				Beneficiario b = new Beneficiario(nomeB, idadeB, qnt, val);
				
				if (qnt > 0) {
					for (int i = 0; i < qnt; i++) {
						String nomeD = JOptionPane.showInputDialog(null, "Nome Dependente " + (i + 1) + ": ");
						int idadeD = Integer
								.parseInt(JOptionPane.showInputDialog(null, "Idade Dependente " + (i + 1) + ": "));
						b.addDep(nomeD, idadeD);
					}
				}
				
				vetBen[tam] = b;
				tam++;
				
			} 
		}
		
		if(tam == vetBen.length) {
			JOptionPane.showMessageDialog(null, "Vetor cheio!");
		}
		
		try {
			PrintWriter out = new PrintWriter(new FileWriter("src/arquivo1.txt"));
			for (int i = 0; i < tam; i++) {
				out.print(vetBen[i].getNome() + "\n" + vetBen[i].getIdade() + "\n" + vetBen[i].getQnteDep() + "\n");
				for (int j = 0; j < vetBen[i].getQnteDep(); j++) {
					out.print(vetBen[i].getDeps()[j].getNome() + "\n" + vetBen[i].getDeps()[j].getIdade() + "\n");
				}
				out.print(vetBen[i].getValPlano() + "\n");
			}
			out.close();
		} catch (Exception e) {
			System.out.println("Erro na escrita do arquivo: " + e.getMessage());
		}

	}
}

 
