name := "TopSellingProductsRFS"

version := "0.1"

scalaVersion := "2.13.0"

libraryDependencies ++= Seq(
  "com.datastax.dse" % "dse-spark-dependencies" % dseVersion % "provided"  excludeAll (
    ExclusionRule("com.datastax.dse", "dse-java-driver-core"),
    ExclusionRule("org.apache.solr", "solr-solrj")
  ),
  "com.datastax.dse" % "dse-java-driver-core" % "1.2.3",
  "org.apache.solr" % "solr-solrj" % "6.0.1"
)

