package algorithm;

import java.math.BigDecimal;
import java.math.BigInteger;

public class main {
	public static void main(String[] args) {
		vector v1;
		vector v2;
		vector result;
		BigDecimal norm1,norm2;
		boolean check;
		algorithm a=new algorithm();
		do{
		
			do{
			v1=a.choosevectors();
			v2=a.choosevectors();
			//a.checkvectors(v1,v2);
			norm1=a.euclideannorm(v1);
			norm2=a.euclideannorm(v2);
		
			}while(norm1.compareTo(norm2)>0);
		
		check=a.checkvectors(v1, v2);
		}while(check==false);
		
		result=a.GaussLegendre(v1, v2);
		System.out.println(result.getX()+" "+result.getY());
	}

}
