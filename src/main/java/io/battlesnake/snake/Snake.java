package io.battlesnake.snake;

import com.fasterxml.jackson.databind.JsonNode;

import javax.vecmath.Point2i;
import java.util.*;

/**
 * Represents a snake.
 */
public class Snake {
	private String id;
	private String name;
	private int health;
	private List<Point2i> segments;
	private Point2i head;

	/**
	 *
	 * @param jsonNode JSON information to generate a new snake. See each element of snakes at https://docs.battlesnake.com/snake-api#tag/.
	 */
	public Snake(JsonNode jsonNode)
	{
		id = jsonNode.get("id").asText();
		name = jsonNode.get("name").asText();
		health = jsonNode.get("health").asInt();
		segments = new ArrayList<>();

		// Each bodySegmentJson is a x, y pair.
		for (JsonNode bodySegmentJson : jsonNode.get("body"))
		{
			segments.add(new Point2i(bodySegmentJson.get("x").asInt(), bodySegmentJson.get("y").asInt()));
		}

		head = segments.get(0);
	}



	public String getId()
	{
		return id;
	}

	public String getName()
	{
		return name;
	}

	public int getHealth()
	{
		return health;
	}

	public List<Point2i> getSegments()
	{
		return Collections.unmodifiableList(segments);
	}

	public Point2i getHead()
	{
		return head;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Snake snake = (Snake) o;
		return health == snake.health &&
				Objects.equals(id, snake.id) &&
				Objects.equals(name, snake.name) &&
				Objects.equals(segments, snake.segments);
	}
}
