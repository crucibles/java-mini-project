package services;

import java.util.stream.Collectors;

import model.Cinema;
import model.HotSeat;
import model.RegularSeat;
import model.Seat;

public class CinemaService {
	final static char min_row = 'A';
	final static char max_row = 'O';
	final static char min_hot_row = 'F';
	final static char max_hot_row = 'J';
	final static int min_seat_num = 1;
	final static int max_seat_num = 10;

	public void initializeSeatList(Cinema c) {

		for (char row = min_row; row <= max_row; row++) {

			for (int seat_num = min_seat_num; seat_num <= max_seat_num; seat_num++) {

				if (isHotRow(row)) {
					c.getSeat_list().add(new HotSeat(row, seat_num, false));
				} else if (isRegularRow(row)) {
					c.getSeat_list().add(new RegularSeat(row, seat_num, false));
				}

			}

		}

	}

	public boolean isHotRow(char row) {

		if (row >= min_hot_row && row <= max_hot_row) {
			return true;
		}

		return false;
	}

	public boolean isRegularRow(char row) {

		if (row >= min_hot_row && row <= max_hot_row) {
			return false;
		}

		return true;
	}

	public boolean isSeatAvailable(Cinema c, char row, int num) {

		if (getSeatByRowAndNum(c, row, num).isReserved()) {
			return false;
		}

		return true;
	}

	public Seat getSeatByRowAndNum(Cinema c, char row, int num) {
		
		return c.getSeat_list().stream().filter(seat -> seat.getSeatRow() == row && seat.getSeatNum() == num)
				.collect(Collectors.toList()).get(0);

	}

} // end bracket
