package com.simpleprogrammer.proteintracker;

public class InvalidGoalException extends Exception {
	public InvalidGoalException( ) {
	}
	public InvalidGoalException(String message) {
		super(message);
	}
}
