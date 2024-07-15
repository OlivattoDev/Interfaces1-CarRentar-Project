package application;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import javax.print.attribute.standard.RequestingUserName;

import entities.CarRental;
import entities.Vehicle;
import services.BrazilTaxService;
import services.RentalService;

public class CarRentalProgram {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

		System.out.println("Entre com os dados do aluguel");
		System.out.print("Modelo do carro: ");
		String model = sc.nextLine();
		System.out.print("Retirada (dd/MM/yyyy hh:mm): ");
		LocalDateTime date1 = LocalDateTime.parse(sc.nextLine(), dtf);
		System.out.print("Retorno (dd/MM/yyyy hh:mm): ");
		LocalDateTime date2 = LocalDateTime.parse(sc.nextLine(), dtf);
		
		CarRental cr = new CarRental(date1, date2, new Vehicle(model));
		
		System.out.print("Entre com o preço por hora: ");
		double pricePerHour = sc.nextDouble();
		System.out.print("Entre com o preço por dia: ");
		double pricePerDay = sc.nextDouble();
		
		RentalService rentalService = new RentalService(pricePerHour, pricePerDay, new BrazilTaxService());
		
		rentalService.processInvoice(cr);
	
		System.out.println("FATURA: ");
		System.out.println("Pagamento básico: " + String.format("%.2f", cr.getInvoice().getBasicPayment()));
		System.out.println("Imposto: " + String.format("%.2f", cr.getInvoice().getTax()))	;
		System.out.println("Pagamento total: " + String.format("%.2f", cr.getInvoice().getTotalPayment()));
	
		
		
		sc.close();
	}
}