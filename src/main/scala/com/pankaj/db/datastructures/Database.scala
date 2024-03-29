package com.pankaj.db.datastructures

import scala.collection.mutable.HashMap

object Database {
  var databases:HashMap[String, Database] = new HashMap()

  def hasDatabase(name:String) = databases.contains(name)

  def createDatabase(name:String) = {
    if (hasDatabase(name))
      throw new IllegalArgumentException("Database already exists")
    else
      databases(name) = new Database(name)
  }

  def deleteDatabase(name:String) = {
    if (!hasDatabase(name))
      throw new IllegalArgumentException("Database doesn't exist")
    else
      databases -= name
  }

  def createTable(db:String, table:String, cols:Array[String]) {
    if (!hasDatabase(db))
      throw new IllegalArgumentException("Database does not exist:%s".format(db))
    else
      databases(db).createTable(table, cols)
  }
}

class Database(n:String) {
  val name:String = n
  private var tables:HashMap[String, Table] = new HashMap()

  def createTable(name:String, cols:Array[String]) = {
    if (tables.contains(name))
      throw new IllegalArgumentException("A table with the given name already exists")
    val table:Table = new Table(name, cols)
    tables(name) = table
    table
  }

  def getTable(name:String) = tables(name)

  def serialize():String = {
    ""
  }
}
