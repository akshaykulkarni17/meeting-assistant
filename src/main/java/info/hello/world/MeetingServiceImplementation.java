package info.hello.world;

import org.springframework.http.ResponseEntity;

import java.util.*;

public class MeetingServiceImplementation implements MeetingService{

    private static final Map<String, Map<Day, Map<Integer, Boolean>>> db = new HashMap<>();

    @Override
    public ResponseEntity createCalender(String user) {
        Map<Day,Map<Integer,Boolean>> daySchedule = new HashMap<>();
        if (db.containsKey(user)) return ResponseEntity.badRequest().body("User's calender already exists.");
        for (Day d: Day.values()){
            Map<Integer,Boolean> slots = new HashMap<>();
            for (int i = 1000; i < 1800; i+=100) {
                slots.put(i,false);
            }
            for (int i = 1030; i < 1800; i+=100) {
                slots.put(i,false);
            }
            daySchedule.put(d,slots);
        }
        db.put(user,daySchedule);
        return ResponseEntity.ok().body("Calender created.");
    }

    @Override
    public ResponseEntity createBooking(ScheduleTime request) {
        List<String> users = request.getUsers();
        if (users.size()<2)  return ResponseEntity.badRequest().body( "At least 2 participants required for scheduling a meeting.");
        String invalidUser = invalidUser(users);
        if (!invalidUser.equals("")) return ResponseEntity.badRequest().body(invalidUser+ " User not found.");
        if (!db.get(users.get(0)).get(request.getDay()).containsKey(request.getTime())) return ResponseEntity.badRequest().body("Incorrect slot");
        ArrayList<String> conflicts = checkConflicts(users, request.getDay(),request.getTime());
        if (!conflicts.isEmpty()) return ResponseEntity.badRequest().body("Can't schedule. Conflict with users " +conflicts);
        addBooking(users,request.getDay(),request.getTime());
        return ResponseEntity.ok().body("Meeting scheduled");
    }

    private void addBooking(List<String> users, Day day, int time) {
        for (String user: users){
            db.get(user).get(day).put(time,true);
        }
    }

    private ArrayList<String> checkConflicts(List<String> users, Day day, int time) {

        ArrayList<String> conflicts = new ArrayList<>();
        for (String user : users){
            if (db.get(user).get(day).get(time)) conflicts.add(user);
        }
        return conflicts;
    }

    private String  invalidUser(List<String> user){
        String invalid ="";
        for (String uname : user){
            if (!db.containsKey(uname)) invalid=uname;
        }
        return invalid;
    }

    @Override
    public ResponseEntity cancelMeeting(ScheduleTime request) {
        List<String> users = request.getUsers();
        if (users.size()<2)  return ResponseEntity.badRequest().body( "At least 2 participants required for cancelling a meeting.");
        String invalidUser = invalidUser(users);
        if (!invalidUser.equals("")) return ResponseEntity.badRequest().body(invalidUser+ " User not found.");
        if (!db.get(users.get(0)).get(request.getDay()).containsKey(request.getTime())) return ResponseEntity.badRequest().body("Incorrect slot");
        for (String user: users){
            db.get(user).get(request.getDay()).put(request.getTime(),false);
        }
        return ResponseEntity.ok().body("Meeting cancelled");
    }

    @Override
    public ResponseEntity deleteCalender(String user) {
        if (!db.containsKey(user)) return ResponseEntity.badRequest().body("User does not exist");
        db.remove(user);
        return ResponseEntity.ok().body("Deleted");
    }

    @Override
    public ResponseEntity checkSlots(ScheduleTime request) {
        List<String> users = request.getUsers();
        if (users.size()<2)  return ResponseEntity.badRequest().body( "At least 2 participants required for cancelling a meeting.");
        String invalidUser = invalidUser(users);
        if (!invalidUser.equals("")) return ResponseEntity.badRequest().body(invalidUser+ " User not found.");
        Day day = request.getDay();
        Set<Integer> slots = db.get(users.get(0)).get(day).keySet();
        ArrayList<Integer> freeSlots = freeSlots(users,day,slots);
        if (freeSlots.isEmpty()) return ResponseEntity.badRequest().body("No free slot available");
        else {
            return ResponseEntity.ok().body(freeSlots);
        }
    }

    private ArrayList<Integer> freeSlots(List<String> users, Day day, Set<Integer> slots){
        ArrayList<Integer> freeSlots = new ArrayList<>();
        for (int slot : slots){
            boolean available =true;
            for (String user : users){
                available=available&&!db.get(user).get(day).get(slot);
            }
            if (available) freeSlots.add(slot);
        }
        Collections.sort(freeSlots);
        return freeSlots;
    }
}
