package com.winter.app.account;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AccountVO {

	private String accountNum;
	private String username;
	private Long productNum;
	private LocalDate accountDate;
	private Long accountBalance;
}
