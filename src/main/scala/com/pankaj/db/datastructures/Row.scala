package com.pankaj.db.datastructures

class Row(values:Array[String]) extends IndexedSeq[String] {
  def apply(i:Int) = values(i)
  def length() = values.length
  def get(i:Int) = values(i)

  def join(vals:Array[String], sep:String) = {
    (vals.first /: vals.tail)("%s%s%s".format(_, sep, _))
  }

  def serialize() = {
    "[" + join((values.map{ "\"%s\"".format(_)}), ",") + "]"
  }
}
