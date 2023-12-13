package com.example.ticket_bug_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootApplication
@EnableAsync
@EnableScheduling
public class TicketBugSystemApplication {

	public static void main(String[] args) {
//		System.out.println(2%2+"************************");
//		int[] s={1,2,3,4,5,6,7,8,9};
//		for(int i=0; i<s.length;i++){
//			if(s[i]%2==0){
//				System.out.println(s[i]);
//			}
//		}









//		List<String> months = new ArrayList<>();
//		months.add("aug 2023");
//		months.add("jan 2023");
//		months.add("dec 2023");
//
//		// Define a custom comparator to sort by month and year
//		Comparator<String> monthYearComparator = new Comparator<String>() {
//			SimpleDateFormat dateFormat = new SimpleDateFormat("MMM yyyy");
//
//			@Override
//			public int compare(String o1, String o2) {
//				try {
//					Date date1 = dateFormat.parse(o1);
//					Date date2 = dateFormat.parse(o2);
//					return date1.compareTo(date2);
//				} catch (ParseException e) {
//					e.printStackTrace();
//					return 0; // Handle parse exception gracefully
//				}
//			}
//		};
//
//		// Sort the list using the custom comparator
//		Collections.sort(months, monthYearComparator);
//
//		// Print the sorted list
//		for (String month : months) {
//			System.out.println(month);
//		}
		SpringApplication.run(TicketBugSystemApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}

}
