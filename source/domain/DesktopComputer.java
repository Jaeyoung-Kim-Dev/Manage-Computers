package domain;

public final class DesktopComputer implements java.io.Serializable {

	private String gpu;
	BaseComputer baseComputer;

	public static DesktopComputer getInstance(String cpu, int ram, int diskSize, String gpu) {
		boolean valid = BaseComputer.validate(cpu, ram, diskSize) && validate(gpu);
		if (valid)
			return new DesktopComputer(cpu, ram, diskSize, gpu);
		return null;
	}

	private DesktopComputer() {
	}

	private DesktopComputer(String cpu, int ram, int diskSize, String gpu) {
		baseComputer = BaseComputer.getInstance(cpu, ram, diskSize);
		this.gpu = gpu;
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

	public String getGpu() {
		return gpu;
	}

	private static boolean validate(String gpu) {
		boolean valid = false;

		if (gpu.equals("intel") && gpu.equals("amd") && gpu.equals("nvidia"))
			System.out.println("***ERROR : Invalid GPU option***");
		else
			valid = true;

		return valid;
	}

	@Override
	public String toString() {
		String output = "Type: DesktopComputer\n" + "CPU: " + getCpu() + "\n" + "RAM: " + getRam() + " GB\n" + "DISK: "
				+ getDiskSize() + " GB\n" + "GPU: " + getGpu();
		return output;
	}

	private void writeObject(java.io.ObjectOutputStream stream) throws java.io.IOException {
		gpu = encodeString(gpu);
		stream.defaultWriteObject();
	}

	private void readObject(java.io.ObjectInputStream stream) throws java.io.IOException, ClassNotFoundException {
		stream.defaultReadObject();
		gpu = decodeString(gpu);
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
