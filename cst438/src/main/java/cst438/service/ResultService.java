package cst438.service;

public class ResultService {

	public int yes;
	public int no;
	public String[] symptoms_all;
	public double calculation;
	public String message;
	public String color;
	public String risk;
	
	public ResultService(Boolean[] symptomVal, int symptom_size) {
		super();
		int x = 0;
		int y = 0;
		String[] symptoms_all = new String[symptom_size];
		for(int i = 0; i < symptom_size; i++) {
			if (symptomVal[i] == true) {
				x++;
				symptoms_all[i] = "danger";
			}else {
				y++;
				symptoms_all[i] = "";
			}
		}
		
		double xi = x;
		double yi = y;
		this.yes = x;
		this.no = y;
		this.calculation = (xi / (xi+yi)) * 100;
		if (calculation == 100) {
			this.message = "Based on your results, you are experiencing all of the symptoms a typical COVID-19 patient would display. We highly recommend to seek medical attention.";
			this.color = "danger";
			this.risk = "HIGH RISK";
		}else if (calculation > 90) {
			this.message = "You present a large amount of symptoms. Although this is just a preliminary check to see if you should seek medical attention, please make sure you call ahead to let the hospital know you are coming and with the symptoms you are presenting.";
			this.color = "danger";
			this.risk = "MEDIUM-HIGH RISK";
		}else if (calculation > 60) {
			this.message = "You present symptoms that we would consider in the cusp of possibly having COVID-19. We would recommend to get tested if possible. If not, continue to monitor your symptoms and seek medical attention if you are not feeling well.";
			this.color = "warning";
			this.risk = "MEDIUM RISK";
		}else if (calculation > 30) {
			this.message = "You present a low risk. Possibly have a cold or something else. However, if you start presenting more symptoms, we recommend to seek medical advise. ";
			this.color = "warning";
			this.risk = "MEDIUM-LOW RISK";
		}else if (calculation > 0) {
			this.message = "You do not present many symptoms to be worried as having COVID-19, however, you could still be asymptomatic, and should continue to follow the CDC guidelines of social distancing.";
			this.color = "success";
			this.risk = "LOW RISK";
		}else {
			this.message = "You do not show any symptoms that would suggest you have COVID-19, however, just because you do not show symptoms, does not mean you do not have the virus. You can be asymptomatic. Please continue to follow CDC guidelines of social distancing.";
			this.color = "success";
			this.risk = "LOW RISK";
		}
	}
}
