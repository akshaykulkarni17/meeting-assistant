package info.hello.world;

import info.hello.world.Day;
import info.hello.world.MeetingService;
import info.hello.world.MeetingServiceImplementation;
import info.hello.world.ScheduleTime;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;


public class MeetingServiceTest {

	private MeetingService meetingService = new MeetingServiceImplementation();

	@Test
	public void createCalender() {
		ResponseEntity response=meetingService.createCalender("akshay");
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void createBooking() {
		ScheduleTime requestBody = new ScheduleTime();
		requestBody.setTime(1100);
		requestBody.setDay(Day.MON);
		requestBody.setUsers(new ArrayList<>(Arrays.asList("a","b")));
		ResponseEntity response=meetingService.createBooking(requestBody);
		assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
	}

	@Test
	public void createBooking_Success(){
		meetingService.createCalender("ak");
		meetingService.createCalender("pk");
		ScheduleTime requestBody = new ScheduleTime();
		requestBody.setTime(1100);
		requestBody.setDay(Day.MON);
		requestBody.setUsers(new ArrayList<>(Arrays.asList("ak","pk")));
		ResponseEntity response=meetingService.createBooking(requestBody);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void checkSlots(){
		ScheduleTime requestBody = new ScheduleTime();
		requestBody.setDay(Day.MON);
		requestBody.setUsers(new ArrayList<>(Arrays.asList("ak","pk")));
		ResponseEntity response=meetingService.checkSlots(requestBody);
		assertEquals(response.getStatusCode(),HttpStatus.OK);
	}

	@Test
	public void deleteCalender(){
		ResponseEntity response=meetingService.deleteCalender("ak");
		assertEquals(response.getStatusCode(),HttpStatus.OK);
	}

	@Test
	public void cancelMeeting(){
		meetingService.createCalender("ak");
		meetingService.createCalender("pk");
		ScheduleTime requestBody = new ScheduleTime();
		requestBody.setDay(Day.MON);
		requestBody.setTime(1000);
		requestBody.setUsers(new ArrayList<>(Arrays.asList("ak","pk")));
		meetingService.createBooking(requestBody);
		ResponseEntity response=meetingService.cancelMeeting(requestBody);
		assertEquals(response.getStatusCode(),HttpStatus.OK);
	}

}