package model;


public class RegularSeat extends Seat {
	
	private char seatType;
/*	
	public RegularSeat(char seatType){
		
		setSeatType(seatType);
	}
*/
	public RegularSeat(char seatRow, int seatNum, boolean reserved){
		super(seatRow, seatNum,reserved);
	}
	/*
	public boolean isHot() {
		
		for(int i=0; i<hotRows.length(); i++){
			System.out.println(i + "  " + hotRows.charAt(i) + " comparing to " + seatType);
			if(hotRows.charAt(i) == getSeatType()){
				return true;
			}
		}
		
		return false;
	}
	*/
	

	
	
	
}
