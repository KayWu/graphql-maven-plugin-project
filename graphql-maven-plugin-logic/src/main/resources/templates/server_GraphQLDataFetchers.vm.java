/** Generated by the default template from graphql-java-generator */
package ${packageUtilName};

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.dataloader.BatchLoader;
import org.dataloader.DataLoader;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;

import com.graphql_java_generator.GraphqlUtils;

import graphql.schema.DataFetcher;

#foreach($import in $imports)
import $import;
#end

/**
 * @author generated by graphql-java-generator
 * @see <a href="https://github.com/graphql-java-generator/graphql-java-generator">https://github.com/graphql-java-generator/graphql-java-generator</a>
 */
@Component
public class GraphQLDataFetchers {

	/** The logger for this instance */
	protected Logger logger = LoggerFactory.getLogger(GraphQLDataFetchers.class);

#foreach ($dataFetchersDelegate in $dataFetchersDelegates)
	@Resource
	${dataFetchersDelegate.pascalCaseName} ${dataFetchersDelegate.camelCaseName};

#end
	@Resource
	GraphqlUtils graphqlUtils;

#foreach ($dataFetchersDelegate in $dataFetchersDelegates)
	////////////////////////////////////////////////////////////////////////////////////////////////
	// Data fetchers for ${dataFetchersDelegate.name}
	////////////////////////////////////////////////////////////////////////////////////////////////
#foreach ($dataFetcher in $dataFetchersDelegate.dataFetchers)

	public DataFetcher<#if(${dataFetcher.completableFuture})CompletableFuture<#end#if(${dataFetcher.dataFetcherDelegate.type.requestType}=="subscription")Publisher<#end#if(${dataFetcher.field.list})List<#end${dataFetcher.field.type.classSimpleName}#if(${dataFetcher.field.list})>#end#if(${dataFetcher.dataFetcherDelegate.type.requestType}=="subscription")>#end#if(${dataFetcher.completableFuture})>#end> ${dataFetchersDelegate.camelCaseName}${dataFetcher.pascalCaseName}#if(${dataFetcher.completableFuture})WithDataLoader#end() {
		return dataFetchingEnvironment -> {
#foreach ($argument in $dataFetcher.field.inputParameters)          
## $argument is an instance of Field
#if ($argument.type.class.simpleName == "EnumType")
#if ($argument.mandatory)
			#if(${argument.list})List<#end${argument.type.classSimpleName}#if(${argument.list})>#end ${argument.javaName} = ${argument.type.classSimpleName}.valueOf(dataFetchingEnvironment.getArgument("${argument.javaName}"));
#else
			#if(${argument.list})List<#end${argument.type.classSimpleName}#if(${argument.list})>#end ${argument.javaName} = null;
			if (dataFetchingEnvironment.getArgument("${argument.name}") != null)
				${argument.javaName} = ${argument.type.classSimpleName}.valueOf(dataFetchingEnvironment.getArgument("${argument.name}"));
#end
#elseif (${argument.type.inputType})
#if(${argument.list})
			List<${argument.type.classSimpleName}> ${argument.javaName} = graphqlUtils.getListInputObjects((List<Map<String, Object>>) dataFetchingEnvironment.getArgument("${argument.name}"), ${argument.type.classSimpleName}.class);
#else
			${argument.type.classSimpleName} ${argument.javaName} = graphqlUtils.getInputObject((Map<String, Object>) dataFetchingEnvironment.getArgument("${argument.name}"), ${argument.type.classSimpleName}.class);
#end
#elseif (${argument.type.classSimpleName} == "UUID")
			#if(${argument.list})List<#end${argument.type.classSimpleName}#if(${argument.list})>#end ${argument.javaName} = (dataFetchingEnvironment.getArgument("${argument.name}") == null) ? null : UUID.fromString(dataFetchingEnvironment.getArgument("${argument.name}"));
#else
			#if(${argument.list})List<#end${argument.type.classSimpleName}#if(${argument.list})>#end ${argument.javaName} = dataFetchingEnvironment.getArgument("${argument.name}");
#end
#end  ##Foreach
#if($dataFetcher.graphQLOriginType)
			${dataFetcher.graphQLOriginType} source = dataFetchingEnvironment.getSource();
#end

#if (${dataFetcher.completableFuture})
			DataLoader<${dataFetcher.field.type.identifier.type.classSimpleName}, #if(${argument.list})List<#end${dataFetcher.field.type.classSimpleName}#if(${argument.list})>#end> dataLoader = dataFetchingEnvironment.getDataLoader("${dataFetcher.field.type.classSimpleName}"); 
			
			// This dataLoader may be null. Let's hande that:
			if (dataLoader != null) 
				return ${dataFetchersDelegate.camelCaseName}.${dataFetcher.javaName}(dataFetchingEnvironment, dataLoader#if($dataFetcher.graphQLOriginType), source#end#foreach($argument in $dataFetcher.field.inputParameters), ${argument.javaName}#end);
			else
				return new CompletableFuture<#if(${dataFetcher.field.list})List<#end${dataFetcher.field.type.classSimpleName}#if(${dataFetcher.field.list})>#end>().completeAsync(
						() -> ${dataFetchersDelegate.camelCaseName}.${dataFetcher.javaName}(dataFetchingEnvironment#if($dataFetcher.graphQLOriginType), source#end#foreach($argument in $dataFetcher.field.inputParameters), ${argument.javaName}#end));
#elseif (${dataFetcher.field.list})
			#if (${dataFetcher.dataFetcherDelegate.type.requestType}=="subscription")Publisher<#end List<${dataFetcher.field.type.classSimpleName}>#if(${dataFetcher.dataFetcherDelegate.type.requestType}=="subscription")>#end ret = ${dataFetchersDelegate.camelCaseName}.${dataFetcher.javaName}(dataFetchingEnvironment#if($dataFetcher.graphQLOriginType), source#end#foreach($argument in $dataFetcher.field.inputParameters), ${argument.javaName}#end);
			logger.debug("${dataFetcher.name}: {} found rows", (ret==null) ? 0 : ret.size());

			return ret;
#else
			#if(${dataFetcher.dataFetcherDelegate.type.requestType}=="subscription")Publisher<#end${dataFetcher.field.type.classSimpleName}#if(${dataFetcher.dataFetcherDelegate.type.requestType}=="subscription")>#end ret = null;
			try {
				ret = ${dataFetchersDelegate.camelCaseName}.${dataFetcher.javaName}(dataFetchingEnvironment#if($dataFetcher.graphQLOriginType), source#end#foreach($argument in $dataFetcher.field.inputParameters), ${argument.javaName}#end);
			} catch (NoSuchElementException e) {
				// There was no items in the Optional
			}

			if (ret != null)
				logger.debug("${dataFetcher.name}: 1 result found");
			else
				logger.debug("${dataFetcher.name}: no result found");

			return ret;
#end
		};
	}

#end
#end
}
