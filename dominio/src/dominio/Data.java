package dominio;

public class Data {
	private int dia;
	private int mes;
	private int any;
	
	private boolean esBisiesto(int year){
		if ((year%4==0 && year%100!=0) || year%400!=0) return true;
		else return false;
	}
	public Data(int dia,int mes,int any) {
		this.dia=dia;
		this.mes=mes;
		this.any=any;
		//if(mes>12) throw new Exception("mes no valid");
		if((mes==1 || mes== 7) && dia<=31){
			this.dia=dia;
			this.mes=mes;
			this.any=any;
		}
		/*else if(mes==2 && esBisiesto(any)&& dia>29) throw new Exception("Dia no valid");
		else if(mes==2 && dia>28)throw new Exception("dia no valid");
		else if(mes%2==0 && dia>31) throw new Exception("Dia no valid");
		else if(mes%2!=0 && dia>30) throw new Exception("Dia no valid");*/
	}
	public int getDia(){
		return this.dia;
	}
	public int getMes(){
		return this.mes;
	}
	public int getAny(){
		return this.any;		
	}
        
        public static Data StringToData(String d)
        {
            Data aux = new Data(1, 1, 1995);
            return aux;
        }
        
        public static String DataToString(Data d) {
            return "1/1/1995";
        }
        
        public boolean checkCompleta() {
            return true;
        }
}
        