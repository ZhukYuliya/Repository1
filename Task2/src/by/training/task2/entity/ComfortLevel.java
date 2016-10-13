package by.training.task2.entity;

public enum ComfortLevel {
	ECONOMY(1), BUSINESS(2), LUXURY(3);

	private int levelNumber;

	private ComfortLevel(int levelNumber) {
		this.levelNumber = levelNumber;
	}

	public int getLevelNumber() {
		return levelNumber;
	}

}
