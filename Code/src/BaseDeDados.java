import java.util.ArrayList;
import java.util.List;

public class BaseDeDados {

	private static List<Negacao> negacao = new ArrayList<Negacao>();
	private static List<Conjuncao> conjuncao = new ArrayList<Conjuncao>();
	private static List<Atomo> atomos = new ArrayList<Atomo>();
	private static ArrayList<String> base = new ArrayList<String>();
	
	public static void addBase(String s){
		base.add(s);
	}
	public static ArrayList<String> getBase(){
		return base; 
	}	
	public static void addNegacao(char v){
		Negacao n = new Negacao(v);
		negacao.add(n);
	}		
	public static void addConjuncao(char v1, char v2,boolean s1, boolean s2){
		Conjuncao c = new Conjuncao(v1, v2,s1,s2);
		conjuncao.add(c);
	}	
	public static void addAtomo(char v){
		Atomo a = new Atomo(v);
		atomos.add(a);
	}	
	public static List<Atomo> getAtomos(){
		return atomos;
	}
	public static List<Negacao> getNegacao(){
		return negacao;
	}
	public static List<Conjuncao> getConjuncao(){
		return conjuncao;
	}
	
	public static boolean verificarDado(char v){
		int tamanho1,tamanho2;
		do{
			tamanho1 = atomos.size();
			tamanho2 = negacao.size();
			buscaPorAtomo();
			buscaPorNegacao();	
		}while((tamanho1 != atomos.size())||(tamanho2 != negacao.size()));
		int resp;
		resp = procuraAtomo(v);
		/*for(int i=0;i<atomos.size();i++){
			System.out.println("Átomo: "+ atomos.get(i).getVar());
		}for(int i=0;i<negacao.size();i++){
			System.out.println("Negação: " + negacao.get(i).getVar());
		}*/
		if(resp == 1) return true;
		else{
			resp = procuraNegacao(v);
			if(resp == 1) return false;
			else return true;
		}
	}
	
	public static int procuraAtomo(char v){
		if(atomos.size()!=0){
			for(int i=0;i<atomos.size();i++){
				if(atomos.get(i).getVar() == v) {
					return 1;
				}
			}						
		}
		return 0;
	}
	
	public static int procuraNegacao(char v){
		if(negacao.size()!=0){
			for(int i=0;i<negacao.size();i++){
				if(negacao.get(i).getVar() == v) {
					return 1;
				}
			}										
		}
		return 0;
		
	}
	
	public static void buscaPorNegacao(){
		if(negacao.size()>0){
			int i=0,t;
			t = negacao.size();
			while(i<t){
				procuraConjuncao(negacao.get(i).getVar(),false);
				t = negacao.size();
				i++;
			}			
		}
	}
	public static void buscaPorAtomo(){
		if(atomos.size()>0){
			int i=0,t;
			t = atomos.size();
			while(i<t){
				procuraConjuncao(atomos.get(i).getVar(),true);
				t = atomos.size();
				i++;
			}			
		}
	}
	
	public static void procuraConjuncao(char v, boolean sinal){
		if(conjuncao.size()>0){
			for(int i=0;i<conjuncao.size();i++){
				if(conjuncao.get(i).deriva(v)){
					if(conjuncao.get(i).meuSinal(v) != sinal){
						if(conjuncao.get(i).derivacaoSinal(v)==false){
							addNegacao(conjuncao.get(i).derivacao(v));
						}else{
							addAtomo(conjuncao.get(i).derivacao(v));
						}
					}
					conjuncao.remove(conjuncao.get(i));
				}
			}			
		}
	}
}

