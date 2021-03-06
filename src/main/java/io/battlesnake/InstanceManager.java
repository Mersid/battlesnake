package io.battlesnake;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.LinkedList;
import java.util.List;

public class InstanceManager {
	// TODO: Fix bug with instances not being removed when match ends.
	private final List<GameInstance> gameInstances = new LinkedList<>();

	public void addGame(GameInstance gameInstance)
	{
		if (!gameInstances.contains(gameInstance))
		{
			gameInstances.add(gameInstance);
		}
		else
		{
			System.out.println("Attempted to add new game, but it is already running!");
		}
	}

	/**
	 * Returns the GameInstance associated with the player's snakeId. The gameId is not used because multiple of this snake can be on a board.
	 * @param snakeId The string ID of the player's snake in that instance.
	 * @return The appropriate GameInstance, or null if it's not found.
	 */
	public GameInstance getGameInstanceFromId(String snakeId)
	{
		for (GameInstance gameInstance : gameInstances)
		{
			if (gameInstance.getSelf().getId().equals(snakeId))
			{
				return gameInstance;
			}
		}
		return null;
	}

	public int getInstanceCount()
	{
		return gameInstances.size();
	}
}
