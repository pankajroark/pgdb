package com.pankaj.db.daemon

import com.pankaj.db.datastructures.Database

class Router {
  def route(msg:String):String = {
    println(msg)
    val createTablePat = """(\S+) create table (\S+)""".r
    val createDatabasePat = """create database (\S+)""".r
    msg match {
      case createTablePat(databasename, tablename) =>
        "Not implemented yet"
      case createDatabasePat(databasename) => {
        Database.createDatabase(databasename)
        "Successfully created database %s".format(databasename)
      }
      case _ =>
        "Unknown command"
    }
  }
}
