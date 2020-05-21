package tests;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import AirPort.Airport;
import AirPort.Flight;
import junit.framework.TestCase;

public class TestAP extends TestCase {
	Airport ap = new Airport("Ben-Gurion");
	LocalDate date1 = LocalDate.of(2020, 5, 20);
	LocalDate date2 = LocalDate.of(2020, 4, 20);
	LocalDate date3 = LocalDate.of(2020, 3, 20);
	LocalDate date4 = LocalDate.of(2020, 4, 17);
	LocalDate date5 = LocalDate.of(2020, 4, 25);
	LocalDate date6 = LocalDate.of(2020, 5, 22);
	LocalTime time1 = LocalTime.of(14, 02);
	LocalTime time2 = LocalTime.of(19, 02);
	LocalTime time3 = LocalTime.of(20, 30);
	LocalTime time4 = LocalTime.of(20, 20);
	Flight f1 = new Flight("El-al", "New York", "Israel", time3, date1, 3, "LY365");
	Flight f2 = new Flight("JesterAirLines", "Alaska", "Israel", time3, date3, 3, "IL231");
	Flight f3 = new Flight("Transvania", "Jordan", "Israel", time4, date4, 3, "NY786");
	Flight f4 = new Flight("StarAir", "Israel", "New York", time1, date2, 3, "SA154");
	Flight f5 = new Flight("EL AL", "Israel", "Germany", time2, date1, 3, "FA194");
	Flight f6 = new Flight("El-al", "New York", "Israel", time3, date1, 3, "LY001");
	Flight f7 = new Flight("El-al", "New York", "Israel", time3, date6, 3, "YY002");

	@Test
	public void addInFlightTest() {
		ap.addFlight(f4);
		assertTrue(ap.getFlightById(ap.getArrival(),"SA154").toString().equals(
				"Flight: SA154, Airline:StarAir, Departure : New York, Arrival: Israel , Date: 20/4/2020, Time: 14:02\n"));
	}

	@Test
	public void addOutFlightTest() {
		ap.addFlight(f2);
		assertTrue(ap.getFlightById(ap.getDeparture(),"IL231").toString().equals(
				"Flight: IL231, Airline:JesterAirLines, Departure : Israel, Arrival: Alaska , Date: 20/3/2020, Time: 20:30\n"));
	}

	@Test
	public void scearchArrivalsByDateTest() {
		ap.addFlight(f1);
		ap.addFlight(f2);
		ap.addFlight(f3);
		ap.addFlight(f4);
		ap.addFlight(f5);
		ap.addFlight(f6);
		ap.addFlight(f7);
		ArrayList<Flight> temp = ap.searchArrivalsByDate(ap.getArrival(), date1, date6);
		assertTrue(temp.contains(f5));
	}
	

}
