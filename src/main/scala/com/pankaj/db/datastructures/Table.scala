package com.pankaj.db.datastructures

import scala.collection.mutable.HashMap

class Table(tablename:String, columns:Array[String]) {
  val name:String = tablename
  val cols:Array[String] = columns

  private var rows:HashMap[String,Row] = new HashMap()
  
  def add(rowKey:String, row:Row) {
    if (row.length != cols.length) 
      throw new IllegalArgumentException("invalid number of entries")

    rows(rowKey) = row
  }

  def apply(rowKey:String):Row = rows(rowKey)

  def getCol(colName:String) = {
    val index = cols.indexOf(colName)
    if (index == -1)
      throw new IllegalArgumentException("column does not exist")
    else 
      (rows map { case (k,v) =>  v(index) }).toArray.reverse
  }

  def serialize() = {
    "{ \"cols\" : %s, \"rows\" : %s".format(serializeColumns, serializeRows)
  }

  def serializeColumns() = {
    Util.serialize(cols)
  }

  def serializeRows() = {
    Util.serialize(rows)
  }

}
