import org.specs2.mutable._
import com.pankaj.db.datastructures.Database
import com.pankaj.db.datastructures.Row

class DatabaseSpec extends Specification {
  "Database " should {
    "be created" in {
      val db:Database = new Database("cabinet")
      db.name must beEqualTo("cabinet")
    }

    "be able to create table" in {
      val db:Database = new Database("cabinet")
      db.createTable("t1", Array("c1", "c2"))
      var table = db.getTable("t1")
      table.name must beEqualTo("t1")
    }
  }
}

