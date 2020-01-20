package io.battlesnake.snake.pathing;

public enum Move {
	UP("up"),
	DOWN("down"),
	LEFT("left"),
	RIGHT("right");

	private String direction;
	Move(String direction) {
		this.direction = direction;
	}

	public String getDirectionAsString() {
		return direction;
	}
}