package com.karthisun.tspcfs.config.cassandra;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.mapping.BasicCassandraMappingContext;
import org.springframework.data.cassandra.core.mapping.CassandraMappingContext;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Configuration
@ConfigurationProperties
@EnableCassandraRepositories(basePackages = "com.karthisun.tspcfs.cassandra.repository")
public class CassandraConfiguration extends AbstractCassandraConfiguration {

	

	@Value("${spring.data.cassandra.keyspace-name}")
	private String keyspace;

	@Value("${spring.data.cassandra.contact-points}")
	private String contactPoints;
	
	@Value("${spring.data.cassandra.username}")
	private String username;
	
	@Value("${spring.data.cassandra.password}")
	private String password;
	
	@Value("${spring.data.cassandra.dataCenter}")
	private String dataCenter;
	
	@Value("${spring.data.cassandra.port}")
	private int port;

	  @Override
	  protected String getKeyspaceName() {
	    return keyspace;
	  }

	  @Override
	  protected String getContactPoints() {
	    return contactPoints;
	  }

	  @Override
	  protected int getPort() {
	    return port;
	  }
	  
	  public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getDataCenter() {
			return dataCenter;
		}

		public void setDataCenter(String dataCenter) {
			this.dataCenter = dataCenter;
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
}
