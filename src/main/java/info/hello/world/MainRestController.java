package info.hello.world;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MainRestController {

	private final MeetingService meetingService = new MeetingServiceImplementation();

	@GetMapping("/createCalender/{user}")
	public ResponseEntity createCalender(@PathVariable String user) {
		return meetingService.createCalender(user);
	}

	@PostMapping(value="createMeeting",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity createBooking( @RequestBody ScheduleTime requestBody) {
		return meetingService.createBooking(requestBody);
	}

	@PostMapping(value="cancelMeeting",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity cancelBooking(  @RequestBody ScheduleTime requestBody) {
		return meetingService.cancelMeeting(requestBody);
	}

	@PostMapping(value="checkSlots",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity checkSlots ( @RequestBody ScheduleTime requestBody){
		return meetingService.checkSlots(requestBody);
	}

	@GetMapping("deleteCalender/{user}")
	public ResponseEntity deleteCalender (@PathVariable String user){
		return meetingService.deleteCalender(user);
	}

}
