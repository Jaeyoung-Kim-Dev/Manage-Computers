import java.util.*;
import java.io.*;
import java.security.AccessControlException;

import domain.*;

public class ManageComputers {
	public static void main(String args[]) throws ClassNotFoundException, IOException {
		if (System.getSecurityManager() == null) {
			System.out.println("*** ERROR: program must run under a Security Manager! ***");
			System.exit(0);
		}

		int input;
		Scanner keyboard = new Scanner(System.in);
		input = -1;
		ArrayList<Object> allComputers = new ArrayList<>();

		while (input != 0) {
			input = displayMenu(keyboard);

			switch (input) {
			case 1: // Load
			{
				allComputers = load(allComputers);
				break;
			}
			case 2: // Save
			{
				save(allComputers);
				break;
			}
			case 3: // List
			{
				list(allComputers);
				break;
			}
			case 4: // Add
			{
				add(keyboard, allComputers);
				break;
			}
			case 5: // Delete
			{
				delete(keyboard, allComputers);
				break;
			}
			case 6: // Edit
			{
				edit(keyboard, allComputers);
				break;
			}
			case 0: // Exit
			{
				break;
			}
			default: {
				System.out.println("*** ERROR: Invalid option number entered ***");
			}
			}
		}
	}

	public static int displayMenu(Scanner keyboard) {
		System.out.println("====MENU====");
		System.out.println("1. Load");
		System.out.println("2. Save");
		System.out.println("3. List");
		System.out.println("4. Add");
		System.out.println("5. Delete");
		System.out.println("6. Edit");
		System.out.println("0. Exit");
		System.out.println("Enter selection:");

		try {
			return keyboard.nextInt();
		} catch (InputMismatchException e) { // catch String value.
			keyboard.nextLine(); // flush keyboard buffer.
			return 999; // return 999 to display the error message.
		}
	}

	// option 1
	private static ArrayList<Object> load(ArrayList<Object> allComputers)
			throws FileNotFoundException, IOException, ClassNotFoundException {
		allComputers = new ArrayList<>();
		FileInputStream fileIn = null;
		ObjectInputStream in = null;

		try {
			File f = new File("../assign1Data/");
			File[] files = f.listFiles();

			for (int i = 0; i < files.length; i++) {
				fileIn = new FileInputStream("../assign1Data/" + files[i].getName());
				in = new ObjectInputStream(fileIn);
				Object obj = in.readObject();
				if (obj instanceof LaptopComputer) {
					allComputers.add((LaptopComputer) obj);
				} else {
					allComputers.add((DesktopComputer) obj);
				}
			}
		} catch (AccessControlException e) {
			System.out.println("*** ERROR: access Denied ***");
		}
		return allComputers;
	}

	// option 2
	private static void save(ArrayList<Object> allComputers) throws IOException {
		try {
			File f = new File("../assign1Data/");

			for (File file : f.listFiles()) {
				if (!file.isDirectory())
					file.delete();
			}

			for (int i = 0; i < allComputers.size(); i++) {
				FileOutputStream fout = new FileOutputStream("../assign1Data/" + (i + 1) + ".txt");
				ObjectOutputStream oos = new ObjectOutputStream(fout);
				oos.writeObject(allComputers.get(i));
				oos.close();
				fout.close();
			}
		} catch (AccessControlException e) {
			System.out.println("*** ERROR: access Denied ***");
		}
	}

	// option 3
	private static void list(ArrayList<Object> allComputers) {
		for (int i = 0; i < allComputers.size(); i++) {
			System.out.println("------------");
			System.out.println("COMPUTER #" + (i + 1));
			System.out.println(allComputers.get(i).toString());
		}
	}

	// option 4
	public static void add(Scanner keyboard, ArrayList<Object> allComputers) {

		int input = 0;

		while (input != 3) {
			System.out.println("ADD NEW COMPUTER");
			System.out.println("1. Add new LaptopComputer");
			System.out.println("2. Add new DesktopComputer");
			System.out.println("3. Back to main menu");

			try {
				input = keyboard.nextInt();
			} catch (InputMismatchException e) { // catch String value.
				keyboard.nextLine(); // flush keyboard buffer and pass to display error message.
			}

			switch (input) {
			case 1: // Add new LaptopComputer
			{
				LaptopComputer laptopComputer = addLaptopComputer(keyboard, allComputers);
				if (laptopComputer != null)
					allComputers.add(laptopComputer);
				break;
			}
			case 2: // Add new DesktopComputer
			{
				DesktopComputer desktopComputer = addDesktopComputer(keyboard, allComputers);
				if (desktopComputer != null)
					allComputers.add(desktopComputer);
				break;
			}
			case 3: // Back to main menu
			{
				break;
			}
			default: {
				System.out.println("*** ERROR: Invalid option number entered ***");
			}
			}
		}
	}

	// option 5
	private static void delete(Scanner keyboard, ArrayList<Object> allComputers) {
		int input = 0;
		System.out.println("DELETE COMPUTER");
		System.out.println("Enter number of computer to delete:");

		try {
			input = keyboard.nextInt();
			allComputers.remove(input - 1);
		} catch (Exception e) {
			System.out.println("*** ERROR : Invalid computer number entered ***");
		}
	}

	// option 6
	private static void edit(Scanner keyboard, ArrayList<Object> allComputers) {
		int input = 0;
		System.out.println("EDIT COMPUTER");
		System.out.println("Enter number of computer to edit:");

		try {
			input = keyboard.nextInt();
			if (allComputers.get(input - 1) instanceof LaptopComputer) {
				LaptopComputer laptopComputer = addLaptopComputer(keyboard, allComputers);
				if (laptopComputer != null)
					allComputers.set(input - 1, laptopComputer);
			} else {
				DesktopComputer desktopComputer = addDesktopComputer(keyboard, allComputers);
				if (desktopComputer != null)
					allComputers.set(input - 1, desktopComputer);
			}
		} catch (Exception e) {
			System.out.println("*** ERROR : Invalid computer number entered ***");
		}
	}

	// option 4-1, option 6-sub
	private static LaptopComputer addLaptopComputer(Scanner keyboard, ArrayList<Object> allComputers) {
		int ram, diskSize, screenSize;
		String cpu;

		try {
			System.out.println("Enter CPU type (i5/i7) :");
			cpu = keyboard.next();
			System.out.println("Enter RAM size (8/16) :");
			ram = keyboard.nextInt();
			System.out.println("Enter disk size (250/500) :");
			diskSize = keyboard.nextInt();
			System.out.println("Enter screen size (13/15) :");
			screenSize = keyboard.nextInt();
			return LaptopComputer.getInstance(cpu, ram, diskSize, screenSize);
		} catch (InputMismatchException e) { // catch String value.
			keyboard.nextLine(); // flush keyboard buffer.
			System.out.println("*** ERROR: RAM, disk size and screen size must be number. ***");
			return null; // return 999 to display the error message.
		}
	}

	// option 4-2, option 6-sub
	private static DesktopComputer addDesktopComputer(Scanner keyboard, ArrayList<Object> allComputers) {
		int ram, diskSize;
		String cpu, gpu;

		try {
			System.out.println("Enter CPU type (i5/i7) :");
			cpu = keyboard.next();
			System.out.println("Enter RAM size (8/16) :");
			ram = keyboard.nextInt();
			System.out.println("Enter disk size (250/500) :");
			diskSize = keyboard.nextInt();
			System.out.println("Enter GPU (intel/amd/nvidia) :");
			gpu = keyboard.next();
			return DesktopComputer.getInstance(cpu, ram, diskSize, gpu);
		} catch (InputMismatchException e) { // catch String value.
			keyboard.nextLine(); // flush keyboard buffer.
			System.out.println("*** ERROR: RAM and disk size must be number. ***");
			return null; // return 999 to display the error message.
		}
	}

}