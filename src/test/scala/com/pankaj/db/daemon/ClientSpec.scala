import org.specs2.mutable._
import java.io._
import java.net.{InetAddress,ServerSocket,Socket,SocketException}

class ClientSpec extends Specification {
  "Database " should {
    "be created" in {
      var result = ""
      try {
        println("running test client")
        val ia = InetAddress.getByName("localhost")
        val socket = new Socket(ia, 9999)
        val in = new DataInputStream(socket.getInputStream)
        val out = new PrintWriter(socket.getOutputStream, true)
        out.println("create database mydb")
        out.println("")
        result = in.readLine
        println(result)

        out.close
        in.close
        socket.close
      } catch {
        case e:IOException =>
          e.printStackTrace
      }
      result must beEqualTo("Successfully created database mydb")
    }
  }
}


