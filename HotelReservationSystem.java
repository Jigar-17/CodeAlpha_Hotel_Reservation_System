import java.util.*;

class Room {
    int roomNumber;
    String category;
    boolean isAvailable = true;

    Room(int roomNumber, String category) {
        this.roomNumber = roomNumber;
        this.category = category;
    }
}

class Booking {
    String customerName;
    int roomNumber;
    double amountPaid;

    Booking(String customerName, int roomNumber, double amountPaid) {
        this.customerName = customerName;
        this.roomNumber = roomNumber;
        this.amountPaid = amountPaid;
    }
}

public class HotelReservationSystem {
    static List<Room> rooms = new ArrayList<>();
    static List<Booking> bookings = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        initializeRooms();

        while (true) {
            System.out.println("\n--- Hotel Reservation System ---");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Make Reservation");
            System.out.println("3. View Bookings");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1 -> viewAvailableRooms();
                case 2 -> makeReservation(sc);
                case 3 -> viewBookings();
                case 4 -> System.exit(0);
                default -> System.out.println("Invalid option.");
            }
        }
    }

    static void initializeRooms() {
        rooms.add(new Room(101, "Single"));
        rooms.add(new Room(102, "Double"));
        rooms.add(new Room(103, "Deluxe"));
        rooms.add(new Room(201, "Single"));
        rooms.add(new Room(202, "Double"));
        rooms.add(new Room(203, "Deluxe"));
    }

    static void viewAvailableRooms() {
        System.out.println("\n--- Available Rooms ---");
        for (Room r : rooms) {
            if (r.isAvailable) {
                System.out.println("Room " + r.roomNumber + " - " + r.category);
            }
        }
    }

    static void makeReservation(Scanner sc) {
        viewAvailableRooms();
        System.out.print("Enter your name: ");
        sc.nextLine(); // consume leftover newline
        String name = sc.nextLine();
        System.out.print("Enter room number to reserve: ");
        int roomNum = sc.nextInt();

        Room selectedRoom = null;
        for (Room r : rooms) {
            if (r.roomNumber == roomNum && r.isAvailable) {
                selectedRoom = r;
                break;
            }
        }

        if (selectedRoom == null) {
            System.out.println("Room not available.");
            return;
        }

        System.out.print("Enter payment amount: ₹");
        double payment = sc.nextDouble();

        selectedRoom.isAvailable = false;
        bookings.add(new Booking(name, roomNum, payment));
        System.out.println("Reservation successful for " + name + " in Room " + roomNum);
    }

    static void viewBookings() {
        System.out.println("\n--- Booking Details ---");
        for (Booking b : bookings) {
            System.out.printf("Name: %s | Room: %d | Paid: ₹%.2f\n", b.customerName, b.roomNumber, b.amountPaid);
        }
    }
}
