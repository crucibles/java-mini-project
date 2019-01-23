package model;

public abstract class Seat {
	final String rows = "ABCDEFGHIJKLMNO";
	final String hotRows = "FGHIJ";
	
	//public char seatType; // r - regular, h - hot
	public char seatRow = 'A';
	public int seatNum = 0;
	public boolean isReserved= false;
	
	public Seat(char seatRow, int seatNum, boolean reserved){
		this.seatRow = seatRow;
		this.seatNum = seatNum;
		isReserved = reserved;
	}
	

	public boolean isReserved() {
		return isReserved;
	}
	public void setReserved(boolean isReserved) {
		this.isReserved = isReserved;
	}
	public int getSeatNum() {
		return seatNum;
	}
	public void setSeatNum(int seatNum) {
		this.seatNum = seatNum;
	}
	public char getSeatRow() {
		return seatRow;
	}
	public void setSeatRow(char seatRow) {
		this.seatRow = seatRow;
	}
/*
	public char getSeatType() {
		return seatType;
	}
	public void setSeatType(char seatType) {
		this.seatType = seatType;
	}
*/	
}
