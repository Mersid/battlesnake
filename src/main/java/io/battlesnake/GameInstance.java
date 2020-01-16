package io.battlesnake;

import com.fasterxml.jackson.databind.JsonNode;
import io.battlesnake.snake.Snake;

import javax.vecmath.Point2i;
import java.sql.SQLOutput;
import java.util.*;

/**
 * Represents a instance of Battlesnake, that is, an entire game.
 * It turns out that multiple games can be played simultaneously.
 */
public class GameInstance {
	private String gameId;
	private int turn;
	private Point2i mapSize;
	private List<Point2i> food;
	private List<Snake> snakes;
	private Snake self;

	/**
	 *
	 * @param jsonNode The jsonNode supplied by start/, move/, or end/. Documentation at https://docs.battlesnake.com/snake-api#tag/
	 */
	public GameInstance(JsonNode jsonNode)
	{
		UpdateInstance(jsonNode);
	}

	/**
	 * Updates the gameInstance variables.
	 * @param jsonNode See ctor.
	 */
	public void UpdateInstance(JsonNode jsonNode)
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

	public void printInfo()
	{
		System.out.println("ID: " + gameId);
		System.out.println("Turn: " + turn);
		System.out.println("Board size: " + mapSize.x + "x" + mapSize.y);
		System.out.println("Food pellet count: " + food.size());
		System.out.println("Snake count: " + snakes.size());
		System.out.println(new Point2i(10, 20));
	}
}
