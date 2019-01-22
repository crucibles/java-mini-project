package model;

public abstract class Seat {
	char seatRow = 'A';
	int seatNum = 0;
	boolean isReserved= false;
	
	public void setSeatRow(char a){
		seatRow = a;
	}
	public void setSeatNum(int a){
		seatNum = a;
	}
	
}
