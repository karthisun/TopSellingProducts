package com.karthisun.tspcfs.config.cassandra;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.mapping.BasicCassandraMappingContext;
import org.springframework.data.cassandra.core.mapping.CassandraMappingContext;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Configuration
@EnableCassandraRepositories(basePackages = "com.karthisun.tspcfs.cassandra.repository")
public class CassandraConfiguration extends AbstractCassandraConfiguration {




	  @Override
	  protected String getKeyspaceName() {
	    return "products";
	  }

	  @Override
	  protected String getContactPoints() {
	    return "localhost";
	  }

	  @Override
	  protected int getPort() {
	    return 9042;
	  }

	  @Override
	  public SchemaAction getSchemaAction() {
	    return SchemaAction.CREATE_IF_NOT_EXISTS;
	  }

	  @Override
		public String[] getEntityBasePackages() {
			return new String[] {
					"com.karthisun.tspcfs.cassandra.model"
			};
		}

	/*
	 * @Override protected String getKeyspaceName() { // TODO Auto-generated method
	 * stub return "products"; }
	 */
	
}
