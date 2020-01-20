package io.battlesnake.snake.pathing;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Represents a path for the snake to take. This is expressed as a series of moves.
 * The front is the player's location, the last is the destination.
 */
public class Path {
	private Deque<Move> moves = new LinkedList<>();

	public void addFront(Move move)
	{
		moves.addFirst(move);
	}

	public void addBack(Move move)
	{
		moves.addLast(move);
	}

	public int getDistance()
	{
		return moves.size();
	}
}
