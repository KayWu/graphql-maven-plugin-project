package com.graphql_java_generator.client.domain.starwars;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * @author generated by graphql-java-generator
 * @See https://github.com/graphql-java-generator/graphql-java-generator
 */
public class MutationTypeAddFriend {

	@JsonDeserialize(as = Character.class)
	Character addFriend;

	public void setAddFriend(Character addFriend) {
		this.addFriend = addFriend;
	}

	public Character getAddFriend() {
		return addFriend;
	}

	@Override
	public String toString() {
		return "MutationTypeAddFriend {addFriend: " + addFriend + "}";
	}
}
