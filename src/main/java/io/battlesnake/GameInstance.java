package io.battlesnake;

import com.fasterxml.jackson.databind.JsonNode;
import io.battlesnake.snake.Snake;
import io.battlesnake.snake.pathing.Move;
import org.apache.commons.lang3.time.StopWatch;

import javax.vecmath.Point2i;
import java.util.*;

/**
 * Represents a instance of Battlesnake, that is, an entire game.
 * It turns out that multiple games can be played simultaneously.
 * It also holds all the logic for our bot.
 */
public class GameInstance {
	private String gameId;
	private int turn;
	private Point2i mapSize;
	private List<Point2i> food;
	private List<Snake> snakes; // All snakes, including self.
	private Snake self;
	private Move move = Move.UP; // When move/ is called, whatever move is defined here will be sent. Initializes to up by default.
	private StopWatch stopWatch = StopWatch.createStarted();

	/**
	 *
	 * @param jsonNode The jsonNode supplied by start/, move/, or end/. Documentation at https://docs.battlesnake.com/snake-api#tag/
	 */
	public GameInstance(JsonNode jsonNode)
	{
		updateInstance(jsonNode);
	}

	/**
	 * Updates the gameInstance variables.
	 * @param jsonNode See ctor.
	 */
	public void updateInstance(JsonNode jsonNode)
	{
		gameId = jsonNode.get("game").get("id").asText();
		turn = jsonNode.get("turn").asInt();

		JsonNode boardNode = jsonNode.get("board");
		mapSize = new Point2i(boardNode.get("width").asInt(), boardNode.get("height").asInt());

		// Food pellets. Each JsonNode in foodJsonIterator contains an x, y value.
		Iterator<JsonNode> foodJsonIterator = boardNode.get("food").iterator();
		food = new ArrayList<>();

		while (foodJsonIterator.hasNext())
		{
			JsonNode foodCoords = foodJsonIterator.next();
			food.add(new Point2i(foodCoords.get("x").asInt(), foodCoords.get("y").asInt()));
		}

		// Snakes. Each JsonNode contains the data needed to generate a snake using its constructor.
		Iterator<JsonNode> snakesJsonIterator = boardNode.get("snakes").iterator();
		snakes = new ArrayList<Snake>();

		while (snakesJsonIterator.hasNext())
		{
			snakes.add(new Snake(snakesJsonIterator.next()));
		}

		self = new Snake(jsonNode.get("you"));

		getNextMove();
	}

	/**
	 * Called after updateInstance. Set the returned move here.
	 */
	private void getNextMove()
	{
		if (move == Move.UP)
		{
			move = Move.RIGHT;
		}
		else if (move == Move.RIGHT)
		{
			move = Move.DOWN;
		}
		else if (move == Move.DOWN)
		{
			move = Move.LEFT;
		}
		else if (move == Move.LEFT)
		{
			move = Move.UP;
		}
	}

	public String getGameId()
	{
		return gameId;
	}

	public int getTurn()
	{
		return turn;
	}

	public Point2i getMapSize()
	{
		return mapSize;
	}

	public List<Point2i> getFood()
	{
		return Collections.unmodifiableList(food);
	}

	public List<Snake> getSnakes()
	{
		return Collections.unmodifiableList(snakes);
	}

	public Snake getSelf()
	{
		return self;
	}

	public Move getMove()
	{
		return move;
	}

	public void printInfo()
	{
		System.out.println("ID: " + gameId);
		System.out.println("Turn: " + turn);
		System.out.println("Board size: " + mapSize.x + "x" + mapSize.y);
		System.out.println("Food pellet count: " + food.size());
		System.out.println("Snake count: " + snakes.size());
		System.out.println("Self health: " + self.getHealth());
		System.out.println("Self pos: " + self.getHead());
		System.out.println("Move direction: " + move);
		long swTime = stopWatch.getTime();
		System.out.println("Last print: " + swTime + " ms ago.");
		if (swTime >= 500) System.out.println("WARNING: More than 500 ms elapsed since last call. This response will have been ignored by the server, or recorded for the next move.");
		stopWatch.reset();
		stopWatch.start();
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		GameInstance that = (GameInstance) o;
		return turn == that.turn &&
				Objects.equals(gameId, that.gameId) &&
				Objects.equals(mapSize, that.mapSize) &&
				Objects.equals(self, that.self);
	}


}