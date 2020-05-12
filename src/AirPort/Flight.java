package AirPort;

import java.time.LocalDate;
import java.time.LocalTime;

public class Flight {
	private String airline;
	private int terminal;
	private String flightId;
	private String comeingFrom;
	private String goingTo;
	private LocalTime time;
	private LocalDate date;
//	private LocalDate arrivalDate;
//	private LocalTime departureTime;

	public Flight(String airline, String comingFrom, String goingTo, LocalTime time, LocalDate date, int terminal,
			String flightId) {
		this.airline = airline;
		this.terminal = terminal;
		this.flightId = flightId;
		this.comeingFrom = comingFrom;
		this.goingTo = goingTo;
		this.time = time;
//		this.departureTime = departureTime;
		this.date = date;
//		this.arrivalDate = arrivalDate;

	}

	public Flight(Flight flight) {
		this.terminal = flight.getDepartureTerminal();
		this.flightId = flight.getFlightId();
		this.comeingFrom = flight.getArrivalName();
		this.goingTo = flight.getDepartureName();
		this.time = flight.getTime();
//		this.departureTime = flight.getDepartureTime();
//		this.arrivalDate = flight.getArrivalDate();
		this.date = flight.getDate();
		this.airline = flight.getAirline();
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

//	public LocalTime getDepartureTime() {
//		return departureTime;
//	}
//	
//	public LocalDate getArrivalDate() {
//		return arrivalDate;
//	}

	public LocalDate getDate() {
		return date;
	}

	public int getDepartureTerminal() {
		return this.terminal;
	}

	public void setDepartureTerminal(int departureTerminal) {
		this.terminal = departureTerminal;
	}

	public String getAirline() {
		return this.airline;
	}

	public boolean equals(Object other) {
		if (!(other instanceof Flight))
			return false;
		Flight temp = (Flight) other;
		return flightId == temp.getFlightId() && airline.equals(temp.getAirline());
	}

	@Override
	public String toString() {
		return "Flight: " + flightId + ", Airline:" + this.airline + ", Departure : " + goingTo + ", Arrival: "
				+ comeingFrom + " , Departure Time: " + date.getDayOfMonth() + "/" + date.getMonthValue() + "/"
				+ date.getYear() + " at " + ", Arrival Time: " + " at " + time + "\n";
	}

}
