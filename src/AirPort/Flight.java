package AirPort;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class Flight implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String airline;
	private int terminal;
	private String flightId;
	private String comeingFrom;
	private String goingTo;
	private LocalTime time;
	private LocalDate date;
	private String dayOfTheWeek;

	public Flight(String airline, String comingFrom, String goingTo, LocalTime time, LocalDate date, int terminal,
			String flightId) {
		this.airline = airline;
		this.terminal = terminal;
		this.flightId = flightId;
		this.comeingFrom = comingFrom;
		this.goingTo = goingTo;
		this.time = time;
		this.date = date;
		this.dayOfTheWeek = date.getDayOfWeek().toString();
	}

	public Flight(Flight flight) {
		this.terminal = flight.getDepartureTerminal();
		this.flightId = flight.getFlightId();
		this.comeingFrom = flight.getArrivalName();
		this.goingTo = flight.getDepartureName();
		this.time = flight.getTime();
		this.date = flight.getDate();
		this.airline = flight.getAirline();
		this.dayOfTheWeek = flight.getDate().getDayOfWeek().toString();
	}

	public String getFlightId() {
		return flightId;
	}

	public String getArrivalName() {
		return comeingFrom;
	}

	public String getDepartureName() {
		return goingTo;
	}

	public LocalTime getTime() {
		return time;
	}

	public LocalDate getDate() {
		return date;
	}

	public String getDayOfTheWeek() {
		return dayOfTheWeek;
	}
	public int getDepartureTerminal() {
		return terminal;
	}

	public void setDepartureTerminal(int departureTerminal) {
		this.terminal = departureTerminal;
	}

	public String getAirline() {
		return airline;
	}

	public boolean equals(Object other) {// fixing equals, inside Departures, can't be same flight with same id and airline name. Same for Arrivals
		if (!(other instanceof Flight))
			return false;
		else {
		Flight temp = (Flight) other;
		boolean check = false;
				if(flightId.equals(temp.getFlightId()) && airline.equals(temp.getAirline()))
					check = true;
		System.out.println("is true? " +check);
		return check;
		}
	}

	public String save() {
		String dataLine = this.flightId + "::" + this.getAirline() + "::" + this.goingTo + "::" + this.comeingFrom + "::"
				+ getDate().toString() + "::" + this.getTime().toString() + "::" + this.terminal+"\n";
		return dataLine;
	}

	@Override
	public String toString() {
		return "Flight: " + flightId + ", Airline:" + airline + ", Departure : " + goingTo + ", Arrival: "
				+ comeingFrom + " , Date: " + date.getDayOfMonth() + "/" + date.getMonthValue() + "/" + date.getYear() + " On "+dayOfTheWeek
				+ ", Time: " + time + ", Terminal: " + terminal + "\n";
	}

}
