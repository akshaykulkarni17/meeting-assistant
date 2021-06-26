package info.hello.world;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface MeetingService {

     ResponseEntity createCalender(String user);

     ResponseEntity createBooking(ScheduleTime request);

     ResponseEntity cancelMeeting(ScheduleTime request);

     ResponseEntity deleteCalender(String user);

     ResponseEntity checkSlots(ScheduleTime request);

}
