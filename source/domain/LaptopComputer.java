package domain;

public final class LaptopComputer implements java.io.Serializable {

	private int screenSize;
	BaseComputer baseComputer;

	public static LaptopComputer getInstance(String cpu, int ram, int diskSize, int screenSize) {
		boolean valid = BaseComputer.validate(cpu, ram, diskSize) && validate(screenSize);
		if (valid)
			return new LaptopComputer(cpu, ram, diskSize, screenSize);
		return null;
	}

	private LaptopComputer() {
	}

	private LaptopComputer(String cpu, int ram, int diskSize, int screenSize) {
		baseComputer = BaseComputer.getInstance(cpu, ram, diskSize);
		this.screenSize = screenSize;
	}

	public String getCpu() {
		return baseComputer.getCpu();
	}

	public int getRam() {
		return baseComputer.getRam();
	}

	public int getDiskSize() {
		return baseComputer.getDiskSize();
	}

	public int getScreenSize() {
		return screenSize;
	}

	private static boolean validate(int screenSize) {
		boolean valid = false;

		if (screenSize != 13 && screenSize != 15)
			System.out.println("***ERROR : Invalid Screen size***");
		else
			valid = true;

		return valid;
	}

	@Override
	public String toString() {
		String output = "Type: LaptopComputer\n" + "CPU: " + getCpu() + "\n" + "RAM: " + getRam() + " GB\n" + "DISK: "
				+ getDiskSize() + " GB\n" + "Screen Size: " + getScreenSize() + " inches";
		return output;
	}
}
