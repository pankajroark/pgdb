package com.pankaj.db.datastructures

import scala.collection.mutable.HashMap

object Util {
  def join(vals:Array[String], sep:String) = {
    (vals.head /: vals.tail)( "%s%s%s".format(_, sep, _))
  }

  def serialize(vals:Array[String]) = {
    "[" + join(vals, ",") + "]"
  }

  def serialize(map:HashMap[String, Row]) = {
    val unbraced = map.map{ case (k,v) => "\"%s\" : %s".format(k, v.serialize) }.toArray
    "{ " + join(unbraced, ",") + " }"
  }
}
