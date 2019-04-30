package com.filemoving;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class FilemovingApplication {

	public static void main(final String[] args) {

		SpringApplication.run(FilemovingApplication.class, args);

//		final Scanner scanner = new Scanner(System.in);
//		System.out.print("Please enter a string and press <enter>: ");
//		while (true) {
//			final String input = scanner.nextLine();
//			if ("q".equals(input.trim())) {
//				// context.close();
//				scanner.close();
//				break;
//			}
//		}

	}

}
