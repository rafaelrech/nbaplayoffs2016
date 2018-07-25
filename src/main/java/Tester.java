import java.util.Random;

public class Tester {

	public static void main(String[] args) {
		for (int a = 0; a <= 20; a++)
			System.out.println(keyGenerator() + "\n");
		// Calendar c = Calendar.getInstance();
		// String date = "2016-04-13T20:00:00-04:00";
		// SimpleDateFormat sdfInput = new
		// SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"); //Z
		// try {
		// c.setTime(sdfInput.parse(date));
		// SimpleDateFormat sdfOutput = new SimpleDateFormat("yyyy-MM-dd
		// HH:mm");
		// System.out.println(sdfOutput.format(c.getTime()));
		// } catch (ParseException e) {
		// e.printStackTrace();
		// }
	}

	public static String keyGenerator() {
		String ret = "";
		Random r = new Random();
		for (int x = 1; x <= 48; x++) {
			int rint = r.nextInt(16);
			ret += String.format("%x", rint);
		}
		return ret;
	}

}
