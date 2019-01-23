package model;

public abstract class Seat {

	private char seatRow = 'A';
	private int seatNum = 0;
	private boolean isReserved= false;
	
	public Seat(char seatRow, int seatNum, boolean reserved){
		setSeatRow(seatRow);
		setReserved(reserved);
		setSeatNum(seatNum);
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

}
