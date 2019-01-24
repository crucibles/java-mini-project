package model;

public class HotSeat extends Seat {
	private char seatType;
/*
	public HotSeat(char seatType){
		setSeatType(seatType);
	}
*/
	public HotSeat(char seatRow, int seatNum, boolean reserved){
		super(seatRow, seatNum,reserved);
	}
/*	
	public boolean isHot() {

		for (int i = 0; i < hotRows.length(); i++) {
			System.out.println(i + "  " + hotRows.charAt(i) + " comparing to " + seatType);
			if (hotRows.charAt(i) == getSeatType()) {
				return true;
			}
		}

		return false;
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

	public char getSeatType() {
		return seatType;
	}

	public void setSeatType(char seatType) {
		this.seatType = seatType;
	}
	*/
}
