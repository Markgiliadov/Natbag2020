package AirPort;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class Airport extends LinkedList<Flight> implements Iterable<Flight> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Flight> arrivals;
	private ArrayList<Flight> departures;
	private String airportName;

	public Airport(String name) {
		this.airportName = name;
		arrivals = new ArrayList<>();
		departures = new ArrayList<>();

	}

	public ArrayList<Flight> getDeparture() {
		return this.departures;
	}

	public ArrayList<Flight> getArrival() {
		return this.arrivals;
	}

	public Flight getFlightById(ArrayList<Flight> src, String id) {
		for (Flight flight : src) {
			if (flight instanceof Flight) {
				if (flight.getFlightId().equals(id))
					return flight;
			}
		}
		return null; // no such flight
	}

	public void sortByDateAndTime() {
		this.departures.sort(new CompareFlightByDate());
		this.arrivals.sort(new CompareFlightByDate());
	}

	public void addFlight(Flight flight) {
		boolean ifDepExists = false;
		System.out.println(flight);
		if (flight.getDepartureName().equals("Israel")) {
			for (Flight departure : departures)
				if (departure.equals(flight))
					ifDepExists = true;
			if (!ifDepExists) {
				getDeparture().add(flight);
				System.out.println("Successfuly Added!");
			} else
				System.out.println("The flight is already logged in the system, try again!");
		} else {
			boolean ifAriExists = false;
			for (Flight arrival : arrivals)
				if (arrival.equals(flight))
					ifAriExists = true;
			if (!ifAriExists) {
				getArrival().add(flight);
				System.out.println("Successfuly Added!");
			} else
				System.out.println("The flight is already logged in the system, try again!");
		}
	}

	public ArrayList<Flight> searchFlightsByAirline(String airline) {
		ArrayList<Flight> myFlights = new ArrayList<>();
		for (Flight flight : arrivals) {
			if (flight.getAirline().equals(airline)) {
				myFlights.add(flight);
			}

		}
		for (Flight flight : departures) {
			if (flight.getAirline().equals(airline)) {
				myFlights.add(flight);
			}
		}
		return myFlights;
	}

	public ArrayList<Flight> searchFlightsByCity(String city) {
		ArrayList<Flight> myFlights = new ArrayList<>();
		for (Flight flight : arrivals) {
			if (flight.getDepartureName().equals(city) || flight.getArrivalName().equals(city))
				myFlights.add(flight);
		}
		for (Flight flight : departures) {
			if (flight.getDepartureName().equals(city) || flight.getDepartureName().equals(city))
				myFlights.add(flight);
		}
		return myFlights;
	}

	public ArrayList<Flight> searchFlightsByDayOfTheWeek(String chosenDay) {

		switch (chosenDay) {
		case "SUNDAY":
			return searchChosenDay("SUNDAY");

		case "MONDAY":
			return searchChosenDay("MONDAY");

		case "TUESDAY":
			return searchChosenDay("TUESDAY");

		case "WEDNSDAY":
			return searchChosenDay("WEDNSDAY");

		case "THURSDAY":
			return searchChosenDay("THURSDAY");

		case "FRIDAY":
			return searchChosenDay("FRIDAY");

		case "SATURDAY":
			return searchChosenDay("SATURDAY");

		default: {
			System.out.println("ERROR DAY INPUT!");
			return arrivals;

		}
		}

	}

	private ArrayList<Flight> searchChosenDay(String theDay) {
		ArrayList<Flight> filteredFlights = new ArrayList<>();
		for (Flight flight : arrivals) {
			if (flight.getDate().getDayOfWeek().toString().equals(theDay))
				filteredFlights.add(flight);
		}
		for (Flight flight : departures) {
			if (flight.getDate().getDayOfWeek().toString().equals(theDay))
				filteredFlights.add(flight);
		}
		return filteredFlights;
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

	public void saveFlightsToFile() {
		try (ObjectOutputStream oOut = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream("Flights.obj")))) {
			oOut.writeInt(this.arrivals.size()); // writing size of arrivals first, than departures, than all arrivals,
													// than all departures
			oOut.writeInt(this.departures.size());

			for (int i = 0; i < this.arrivals.size(); i++)
				oOut.writeObject(this.arrivals.get(i));
			for (int i = 0; i < this.departures.size(); i++)
				oOut.writeObject(this.departures.get(i));
			System.out.println(this.arrivals.size() + this.departures.size() + " Flights Saved!\n");
		} catch (FileNotFoundException e) {
			System.out.println("Save Exception: File Contacts.obj Not Found!");
		} catch (IOException e) {
			System.out.println("Save Exception: " + e.getMessage());
		}
	}

	public void loadFlightsFromFile() {
		try (ObjectInputStream oIn = new ObjectInputStream(
				new BufferedInputStream(new FileInputStream("Flights.obj")))) {
			int countArrival = oIn.readInt(), countDeparture = oIn.readInt(), added = 0, updated = 0;

			for (int i = 0; i < countArrival; i++) {
				Flight tempFlight = (Flight) oIn.readObject();
				for (int j = 0; j < this.arrivals.size(); j++) {
					if (tempFlight.equals(this.arrivals.get(j))) {
						updated++;
						this.arrivals.remove(j);
					} else
						added++;
				}
				this.arrivals.add(tempFlight);
			}
			for (int i = 0; i < countDeparture; i++) {
				Flight tempFlight = (Flight) oIn.readObject();
				for (int j = 0; j < this.departures.size(); j++) {
					if (tempFlight.equals(this.departures.get(j))) {
						updated++;
						this.departures.remove(j);
					} else
						added++;
				}
				this.departures.add(tempFlight);

			}

			if (added + updated > 0)
				System.out.printf("%d Flights Loaded! Added: %d Updated: %d\n", (added + updated), added, updated);
			else
				System.out.println("File Is Empty!");

		} catch (FileNotFoundException e) {
			System.out.println("Load Exception: File Flights.obj Not Found!");
		} catch (IOException e) {
			System.out.println("Load Exception: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("Load Exception: File Flights.obj Not Contains Flights Data!");
		}
	}

	@Override
	public String toString() {

		return "Airport:\n" + airportName + "\n----Departures----\n" + departures.toString() + "\n----Arrivals----\n"
				+ arrivals.toString();
	}

}