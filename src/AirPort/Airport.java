package AirPort;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javafx.util.converter.LocalDateStringConverter;

public class Airport {
//	private Departures myDepartures;
//	private Arrivals myArrivals;
	private ArrayList<Flight> arrivals;
	private ArrayList<Flight> departures;
	private String airportName;

	public Airport(String name) {
		this.airportName = name;
		arrivals = new ArrayList<>();
		departures = new ArrayList<>();
//		this.myDepartures = new Departures(10);
//		this.myArrivals = new Arrivals(10);
	}

	public ArrayList<Flight> getDeparture() {
		return this.departures;
	}

	public ArrayList<Flight> getArrival() {
		return this.arrivals;
	}

	public Flight getFlightById(ArrayList<Flight> src,String id) {
		for (Flight flight : src) {
			if (flight instanceof Flight) {
				if (flight.getFlightId().equals(id))
					return flight;
			}
		}
		return null; // no such flight
	}
	
	public void addFlight(Flight flight) {
		if (flight.getDepartureName().equals("Israel"))
			getDeparture().add(flight);
		else
			getArrival().add(flight);
	}

	public ArrayList<Flight> searchArrivalsByDate(ArrayList<Flight> arrivals, LocalDate lowDate, LocalDate highDate) {
		ArrayList<Flight> results = new ArrayList<Flight>();
		for (int i = 0; i < arrivals.size(); i++)
			if (arrivals.get(i) != null)
				if ((arrivals.get(i).getDate().compareTo(lowDate) >= 0)
						&& (arrivals.get(i).getDate().compareTo(highDate) <= 0))
					results.add(arrivals.get(i));
		return results;
	}

	public ArrayList<Flight> searchDeparturesByDate(ArrayList<Flight> flightArray, LocalDate lowDate,
			LocalDate highDate) {
		ArrayList<Flight> results = new ArrayList<Flight>();
		for (int i = 0; i < flightArray.size(); i++)
			if ((flightArray.get(i).getDate().compareTo(lowDate) >= 0)
					&& (flightArray.get(i).getDate().compareTo(highDate) <= 0))
				results.add(flightArray.get(i));
		return results;
	}

	public void save() throws FileNotFoundException {
		File file = new File("dataBase.txt");
		PrintWriter pw = new PrintWriter(file);
		pw.println("Arrivals");
		for (Flight flight : arrivals) {
			pw.print(flight.save());
		}
		pw.println("Departures");
		for (Flight flight : departures) {
			pw.print(flight.save());
		}
		pw.close();

	}

	public void loadArrivals(Scanner scan) {
		while (!scan.nextLine().toString().contentEquals("Departures")) {
				
			
			String[] flightData = scan.next().split("::");
			String[] date = flightData[4].split("-");
			String[] time = flightData[5].split(":");
			arrivals.add(new Flight(flightData[1], flightData[3], flightData[2],
					LocalTime.of(Integer.parseInt(time[0]), Integer.parseInt(time[1])),
					LocalDate.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2])),
					Integer.parseInt(flightData[6]), flightData[0]));
			
		}
	}
	public void loadDepartures(Scanner scan) {
		while (scan.hasNext()) {
			String[] flightData = scan.next().split("::");
			String[] date = flightData[4].split("-");
			String[] time = flightData[5].split(":");
			arrivals.add(new Flight(flightData[1], flightData[3], flightData[2],
					LocalTime.of(Integer.parseInt(time[0]), Integer.parseInt(time[1])),
					LocalDate.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2])),
					Integer.parseInt(flightData[6]), flightData[0]));
		}
	}

	public void load() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("dataBaseTest.txt"));
		String title = br.readLine();
		try {
		while (title.compareTo("Departures")!=0) {
			title = br.readLine();
			String[] flightData = title.split("::");
			String[] date = flightData[4].split("-");
			String[] time = flightData[5].split(":");
			arrivals.add(new Flight(flightData[1], flightData[3], flightData[2],
					LocalTime.of(Integer.parseInt(time[0]), Integer.parseInt(time[1])),
					LocalDate.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2])),
					Integer.parseInt(flightData[6]), flightData[0]));
		}
		}
		catch (ArrayIndexOutOfBoundsException e) {
			
		}
		try {
		while (title!=null) {
			title = br.readLine();
			String[] flightData = title.split("::");
			String[] date = flightData[4].split("-");
			String[] time = flightData[5].split(":");
			departures.add(new Flight(flightData[1], flightData[3], flightData[2],
					LocalTime.of(Integer.parseInt(time[0]), Integer.parseInt(time[1])),
					LocalDate.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2])),
					Integer.parseInt(flightData[6]), flightData[0]));
		}
		}
		catch (NullPointerException e) {
			
		}
		
	}

	@Override
	public String toString() {
		return "Airport:\n" + airportName + "\n----Departures----\n" + departures.toString() + "\n----Arrivals----\n"
				+ arrivals.toString();
	}

}