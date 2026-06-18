package ch.bbw.m319.battleship;

import ch.bbw.m319.battleship.api.BattleshipArena;
import ch.bbw.m319.battleship.api.BattleshipField;
import ch.bbw.m319.battleship.api.BattleshipPlayer;
import ch.bbw.m319.battleship.api.ShipPosition;

import java.util.ArrayList;
import java.util.List;

public class RicoPlayer implements BattleshipPlayer {

	public static void main(String[] args) {
		// let it play against itself
		BattleshipArena.playMultipleAndCount(new RicoPlayer(), new DumbPlayer(), 1000);
		// BattleshipArena.playOnce(new RicoPlayer(), new DumbPlayer());
	}


	private final List<BattleshipField> shots = new ArrayList<>();

	private BattleshipField randomField() {
		int randomNum = (int)(Math.random() * 3);
		int randomNumLetter = (int)(Math.random() * 3);
		String randomLetter = "A";

		randomNum++;
		if (randomNumLetter == 1) {
			randomLetter = "B";
		} else if (randomNumLetter == 2) {
			randomLetter = "C";
		}
        return BattleshipField.valueOf(randomLetter + randomNum);
	}


	private BattleshipField checkShip(BattleshipField fieldOne) {
		String[] validFieldTwo;
		if (fieldOne.name().equals("A1")) {
			validFieldTwo = new String[]{"A2", "B1"};
		} else if (fieldOne.name().equals("A2")) {
			validFieldTwo = new String[]{"A1", "A3", "B2"};
		} else if (fieldOne.name().equals("A3")) {
			validFieldTwo = new String[]{"A2", "B3"};
		} else if (fieldOne.name().equals("B1")) {
			validFieldTwo = new String[]{"A1", "B2", "C1"};
		} else if (fieldOne.name().equals("B2")) {
			validFieldTwo = new String[]{"A2", "B1", "B3", "C2"};
		} else if (fieldOne.name().equals("B3")) {
			validFieldTwo = new String[]{"A3", "B2", "C3"};
		} else if (fieldOne.name().equals("C1")) {
			validFieldTwo = new String[]{"B1", "C2"};
		} else if (fieldOne.name().equals("C2")) {
			validFieldTwo = new String[]{"B2", "C1", "C3"};
		} else {
			validFieldTwo = new String[]{"B3", "C2"};
		}
		int arrayLength = validFieldTwo.length;
		return BattleshipField.valueOf(validFieldTwo[(int)(Math.random() * arrayLength)]);
	}

	@Override
	public ShipPosition placeYourShip() {
		// TODO: replace this implementation: always top-left is not that good...
		BattleshipField fieldOne = randomField();

		BattleshipField fieldTwo = checkShip(fieldOne);

		shots.clear();
		return new ShipPosition(fieldOne, fieldTwo);

	}

	public BattleshipField checkAim() {
        BattleshipField shot;
        do {
            shot = randomField();
        } while (shots.contains(shot));

        shots.add(shot);

        return shot;
    }

	@Override
	public BattleshipField takeAim() {
		// TODO: replace this implementation: always bottom-right is not that good...

        return checkAim();
	}
}
