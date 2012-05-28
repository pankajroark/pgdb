import org.specs2.mutable._
import java.io._
import java.net.{InetAddress,ServerSocket,Socket,SocketException}

class ClientSpec extends Specification {
  def runCommand(command:String) = {
    var result = ""
    try {
      val ia = InetAddress.getByName("localhost")
      val socket = new Socket(ia, 9999)
      val in = new DataInputStream(socket.getInputStream)
      val out = new PrintWriter(socket.getOutputStream, true)

      out.println(command)
      out.println("")
      result = in.readLine
      println(result)
      out.close
      in.close
      socket.close
    } catch {
      case e:IOException =>
        e.printStackTrace
    } finally {
    }
    result
  }

  sequential

  "Database " should {
    "be created" in {
      val result = runCommand("create database mydb")
      println(runCommand("delete database mydb"))
      result must beEqualTo("Successfully created database mydb")
    }
  }

  "Table" should {
    "be created" in {
      var result = runCommand("create database mydb")
      result must beEqualTo("Successfully created database mydb")
      result = runCommand("mydb create table mytable(first,second)")
      result must beEqualTo("Successfully created table mytable")
    }
  }
}


