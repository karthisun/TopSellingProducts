package com.karthisun.spark

import org.apache.spark.sql.SparkSession
import org.apache.spark._
import org.apache.spark.SparkContext._
import org.apache.spark.sql._
import org.apache.log4j._
import com.datastax.spark.connector._, org.apache.spark.SparkContext, org.apache.spark.SparkContext._, org.apache.spark.SparkConf

object SparkSQLTopSellingProducts {
  
  case class SalcesRecord(categoryID:Int,productID:Int, count:Int)
  
  def mapper(line:String): SalcesRecord = {
    val records = line.split(',')  
    
    val salesDetails:SalcesRecord = SalcesRecord(records(0).toInt,  records(1).toInt, records(2).toInt)
    return salesDetails
  }
  

  def main(args: Array[String]) {
    
    // Set the log level to only print errors
    Logger.getLogger("org").setLevel(Level.ERROR)
    

    val spark = SparkSession
      .builder
      .appName("SparkSQLTopSellingProducts")
      .master("local[*]")
      .config("spark.sql.warehouse.dir", "file:///C:/temp")
      .getOrCreate()
      
    val cassandraTableData = spark.read.format("org.apache.spark.sql.cassandra")
    .option("spark.cassandra.connection.host", "127.0.0.1")
    .option("spark.cassandra.connection.port", "9042")
    .option("keyspace", "products")
    .option("table", "emp")
    .load()
    
    val file_collect = cassandraTableData.collect().take(100)
    file_collect.foreach(println(_))
    
    import spark.implicits._
      
    val records = spark.sparkContext.textFile("../ml-100k/soldproducts.csv")
    val totalSoldProductsDetail = records.map(mapper).toDS().cache()
    
    totalSoldProductsDetail.printSchema()
    println("Here is our inferred schema:")
    totalSoldProductsDetail.printSchema()
    

    //val catagiryProductDS = totalSoldProductsDetail.groupBy("categoryID","productID").count().orderBy("count").show()
    

    //val soldProductDetailsDS = totalSoldProductsDetail.groupBy("productID").count().orderBy("count").toDF().show()
    val given_category:Int = args(0).toInt
    val topSoldProductsByCatagory = totalSoldProductsDetail
                                    .filter("categoryID == "+given_category)
                                    .groupBy("categoryID","productID")
                                    .count().toDF()
                                    //.orderBy("count")
                                    //.show()
                                    
     val topSoldProductsByCatagoryRDD = topSoldProductsByCatagory.rdd
     //topSoldProductsByCatagoryRDD.saveAsTextFile("../ml-100k/topSoldProductsByCatagoryRDD")
     topSoldProductsByCatagoryRDD.saveToCassandra("products", "soldproducts", SomeColumns("category_id","product_id","count" ))
     //topSoldProductsByCatagoryRDD.saveToCassandra("products", "topsellingproducts10k", SomeColumns("count","product_id" ))
                       
                                   
 
    spark.stop()
  }
}