//merge Comment here

package ge.gtug;

import java.util.Scanner;

public class ToUni {
	public static class Main {
		public static void main(String[] argv) throws Exception {

			String lat = "abgdevzTiklmnopJrstufqRySCcZwWxjh";
			for(int i = 0; i< lat.length(); i++){
			
				
				
			}
			Scanner in = new Scanner(System.in);
			String input = in.nextLine();

			StringBuilder buildResult = new StringBuilder();

			for (int i = 0; i < input.length(); i++) {

				char inputPart = input.charAt(i);
				int latIndex = lat.indexOf(inputPart); // index of inputed char
				// from lat string

				if (latIndex == -1) {
					buildResult.append(inputPart);
				} else {

					int geoHex = 0x10d0 + latIndex; // computed hex for Georgian
					char geo = (char) (geoHex); // Georgian hex to char
					buildResult.append(geo);
				}
			}

			String result = buildResult.toString();
			System.out.println(result);

		}

	}

}
