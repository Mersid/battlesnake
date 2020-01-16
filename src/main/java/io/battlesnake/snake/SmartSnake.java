package io.battlesnake.snake;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * A snake with additional abilities. This is used to represent our own snake, since
 * we have more information about our own snake than others, such as its plans and strategies.
 */
public class SmartSnake extends Snake {
	/**
	 * @param jsonNode JSON information to generate a new snake. See each element of snakes at https://docs.battlesnake.com/snake-api#tag/.
	 */
	public SmartSnake(JsonNode jsonNode)
	{
		super(jsonNode);
	}
}
