package com.graphql_java_generator.client.domain.allGraphQLCases;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;


/**
 * @author generated by graphql-java-generator
 * @see <a href="https://github.com/graphql-java-generator/graphql-java-generator">https://github.com/graphql-java-generator/graphql-java-generator</a>
 */
public class MyQueryTypeWithOneOptionalParam {

	@JsonDeserialize(as = CharacterImpl.class)
	Character withOneOptionalParam;

	public void setWithOneOptionalParam(Character withOneOptionalParam) {
		this.withOneOptionalParam = withOneOptionalParam;
	}

	public Character getWithOneOptionalParam() {
		return withOneOptionalParam;
	}
	
    public String toString() {
        return "MyQueryTypeWithOneOptionalParam {withOneOptionalParam: " + withOneOptionalParam + "}";
    }
}