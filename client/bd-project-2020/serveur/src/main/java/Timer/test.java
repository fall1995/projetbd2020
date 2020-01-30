package Timer;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class test {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		String s ="oct. 5, 2019";
		s = s.replace(".", "");
		s = s.replace(",", "");
		//System.out.println(s);
		String[] test = s.split(" ");
		String mois = test[0];
		String jour = test[1];
		String annee = test[2];
		String d = jour+"-"+mois+"-"+annee;
		System.out.println(d);
		switch(mois) {
		case "janv":
			mois = "01" ;
			break;
		case "févr" :
			mois="02";
			break;
		case "mars" :
			mois="03";
			break;
		case "avr" :
			mois="04";
			break;
		case "mai" :
			mois="05";
			break;
		case "juin" :
			mois="06";
			break;
		case "juil" :
			mois="07";
			break;
		case "août" :
			mois="08";
			break;
		case "sept" :
			mois="09";
			break;
		case "oct" :
			mois="10";
			break;
		case "nov" :
			mois="11";
			break;
		case "déc" :
			mois="12";
			break;
			
		}
		d = jour+"-"+mois+"-"+annee;
		System.out.println(d);
			
			
		
	}

}
