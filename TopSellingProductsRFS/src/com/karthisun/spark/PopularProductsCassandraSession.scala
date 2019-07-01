package com.karthisun.spark

import org.apache.spark.sql.SparkSession
import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.log4j._
import com.datastax.spark.connector._, org.apache.spark.SparkContext, org.apache.spark.SparkContext._, org.apache.spark.SparkConf



object PopularProductsCassandraSession {
 

  def main(args: Array[String]) {
   
    // Set the log level to only print errors
    Logger.getLogger("org").setLevel(Level.ERROR)

    val sparkSession = SparkSession
                      .builder()
                      .appName("PopularProductsCassandraSession")
                      .master("local[*]")
                      .getOrCreate();
    
    val cassandraTableData = sparkSession.read.format("org.apache.spark.sql.cassandra")
                      .option("spark.cassandra.connection.host", "127.0.0.1")
                      .option("spark.cassandra.connection.port", "9042")
                      .option("keyspace", "products")
                      .option("table", "emp")
                      .load()
    
    val file_collect = cassandraTableData.collect().take(100)
    file_collect.foreach(println(_))
    

    val lines = sparkSession.sparkContext.textFile("../ml-100k/soldproducts123.csv")
    
    // Map to (ProductID, 1) tuples
    val recordss = lines.map(x => (x.split(",")(1).toInt, 1))
    // Count up all the 1's for each movie
    val productCounts = recordss.reduceByKey( (x, y) => x + y )
    //movieCounts.saveAsTextFile("../ml-100k/productCounts")
    
    // Flip (ProductID, count) to (count, ProductID)
    val flipped = productCounts.map( x => (x._2, x._1) )
    //flipped.saveAsTextFile("../ml-100k/flipped")
    
    // Sort
    val sortedProducts = flipped.sortByKey()
    //sortedMovies.saveAsTextFile("../ml-100k/sortedProducts")
    
    sortedProducts.saveToCassandra("products", "topsellingproducts10k", SomeColumns("count","product_id" ))
    
 
  }
  
}

