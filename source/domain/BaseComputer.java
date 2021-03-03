package domain;

public final class BaseComputer implements java.io.Serializable {

	int ram = 0, diskSize = 0;
	String cpu = "n/a";

	public static BaseComputer getInstance(String cpu, int ram, int diskSize) {
		boolean valid = validate(cpu, ram, diskSize);
		if (valid)
			return new BaseComputer(cpu, ram, diskSize);
		return null;
	}

	private BaseComputer() {
	}

	private BaseComputer(String cpu, int ram, int diskSize) {
		this.cpu = cpu;
		this.ram = ram;
		this.diskSize = diskSize;
	}

	public int getRam() {
		return ram;
	}

	public int getDiskSize() {
		return diskSize;
	}

	public String getCpu() {
		return cpu;
	}

	public static boolean validate(String cpu, int ram, int diskSize) {
		boolean valid = false;

		if (!cpu.equals("i5") && !cpu.equals("i7"))
			System.out.println("***ERROR : Invalid CPU type***");
		else if (ram != 8 && ram != 16)
			System.out.println("***ERROR : Invalid RAM type***");
		else if (diskSize != 250 && diskSize != 500)
			System.out.println("***ERROR : Invalid Disk size***");
		else
			valid = true;

		return valid;
	}

	private void writeObject(java.io.ObjectOutputStream stream) throws java.io.IOException {
		cpu = encodeString(cpu);
		stream.defaultWriteObject();
	}

	private void readObject(java.io.ObjectInputStream stream) throws java.io.IOException, ClassNotFoundException {
		stream.defaultReadObject();
		cpu = decodeString(cpu);
	}

	private String encodeString(String s) {
		String encoded = "";

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			c++;
			encoded = encoded + c;
		}

		return encoded;
	}

	private String decodeString(String s) {
		String decoded = "";

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			c--;
			decoded = decoded + c;
		}

		return decoded;
	}

}
