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
	 *
	 * @param id The string ID of the instance to return.
	 * @return The appropriate GameInstance, or null if it's not found.
	 */
	public GameInstance getGameInstanceFromId(String id)
	{
		for (GameInstance gameInstance : gameInstances)
		{
			if (gameInstance.getGameId().equals(id))
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
