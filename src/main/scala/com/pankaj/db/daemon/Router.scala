package com.pankaj.db.daemon

import com.pankaj.db.datastructures.Database

class Router {
  def route(msg:String):String = {
    println(msg)
    val createTablePat = """(\S+) create table (\w+)(\(.*\))""".r
    val createDatabasePat = """create database (\S+)""".r
    val deleteDatabasePat = """delete database (\S+)""".r
    msg match {
      case createTablePat(databasename, tablename, cols) =>
        Database.createTable(databasename, tablename, cols.split(","))
        "Successfully created table %s".format(tablename)
      case createDatabasePat(databasename) => {
        Database.createDatabase(databasename)
        "Successfully created database %s".format(databasename)
      }
      case deleteDatabasePat(databasename) => {
        Database.deleteDatabase(databasename)
        "Successfully deleted database %s".format(databasename)
      }
      case _ =>
        "Unknown command"
    }
  }
}
