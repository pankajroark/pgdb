package com.pankaj.db.datastructures
import scala.collection.mutable.HashMap

class Table {
  private var rows:HashMap[String,Row] = new HashMap()
  
  def insert(rowKey:String, row:Row) {
    rows(rowKey) = row
  }
}
