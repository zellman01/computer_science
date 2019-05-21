package ch06questions;

import java.util.Scanner;

public class findBestFit {
	int size1, size2, space;
	private static int k;
	static Scanner kb = new Scanner(System.in);
	/**
	 * 
	 * @param size1 Size of first file (in MB)
	 * @param size2 Size of second file (in MB)
	 * @param space Size of memory card (in MB)
	 * @return k Thing to decide what the program says
	 */
	public static int findBestFits(int size1, int size2, int space) {
		k = 0;
	
		if ((size1 + size2 <= space)) {
			k = 3;
		}
		else if (size1 < space && size2 > space) {
			k = 1;
		}
		else if (size1 > space && size2 < space) {
			k = 2;
		}
		else if (size1 == space && size2 != space)
			k = 1;
		else if (size2 == space && size1 != space)
			k = 2;
		else if (size1 + size2 <= space) {
			int h = Math.max(size1, size2);
			if (h == size1) {
				k = 1;
			} else {
				k = 2;
			}
		} else if (size1 > space && size2 > space) {
			k = 0;
		} else {
			int h = Math.max(size1, size2);
				if (h == size1) {
					k = 1;
				} else {
					k = 2;
				}
			}
		
		return k;
	}
	
	public static void main(String[] args) {
		System.out.print("How much space does the first file contain (in MB) (0-2000)?: ");
		int size1 = kb.nextInt();
		System.out.print("How much space does the second file contain (in MB) (0-2000)?: ");
		int size2 = kb.nextInt();
		if (size1 > 2000 || size2 > 2000 || size1 < 0 || size2 < 0) {
			System.out.println("Do you think that the file size can be bigger than 2000 MB or smaller than 0 MB?");
			System.exit(1);
		}
		//System.out.print("How much space does the memory card contain (in MB) (0-4000)?: ");
		int space = 512;//kb.nextInt();
		int total = findBestFits(size1, size2, space);
		kb.close();
		System.out.println(total);
	}
}
