import org.specs2.mutable._
import com.pankaj.db.datastructures.Table
import com.pankaj.db.datastructures.Row

class TableSpec extends Specification {
  "The table" should {
    "let us add a row " in {
      val table:Table = new Table("t1", Array("first"))
      val row:Row = new Row(Array("some"))
      table.add("key", row)
      table("key")(0) must beEqualTo("some")
    }

    "be able to get cols" in {
      val table:Table = new Table("t1", Array("first", "second"))
      val row1:Row = new Row(Array("r1c1", "r1c2"))
      val row2:Row = new Row(Array("r2c1", "r2c2"))
      table.add("r1", row1)
      table.add("r2", row2)
      val cols = table.getCol("second")
      cols must beEqualTo(Array("r1c2", "r2c2"))
    }
  }
}
