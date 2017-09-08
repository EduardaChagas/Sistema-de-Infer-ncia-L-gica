
public class Conjuncao {
	private Character var1,var2;
	private boolean v1, v2;
	
	public Conjuncao(char v1,char v2,boolean vv1,boolean vv2){
		this.var1 = v1;
		this.var2 = v2;	
		this.v1 = vv1;;
		this.v2 = vv2;
	}
	
	public boolean deriva(char v){
		if((var1 == v)||(var2 == v)) return true;
		else return false;
	}
	
	public boolean meuSinal(char v){
		if(var1 == v) return v1;
		else return v2;
	}
	
	public char derivacao(char v){
		if(var1 == v) return var2;
		else return var1;
	}
	
	public boolean derivacaoSinal(char v){
		if(var1 == v) return v2;
		else return v1;
	}
	
	public void setVar1(char v1){
		this.var1 = v1;
	}
	
	public void setVar2(char v2){
		this.var2 = v2;
	}
	
	public char getVar1(){
		return(this.var1);
	}
	
	public char getVar2(){
		return(this.var2);
	}
	
	public void setV1(boolean vv1){
		this.v1 = vv1;
	}
	
	public void setV2(boolean vv2){
		this.v2 = vv2;
	}
	
	public boolean getV1(){
		return(this.v1);
	}
	
	public boolean getV2(){
		return(this.v2);
	}

}
