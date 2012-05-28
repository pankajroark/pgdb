import java.io._
import java.net.{InetAddress,ServerSocket,Socket,SocketException}

object TestClient {
  def main(args:Array[String]) {
    try {
      println("running test client")
      val ia = InetAddress.getByName("localhost")
      val socket = new Socket(ia, 9999)
      val in = new DataInputStream(socket.getInputStream)
      val out = new DataOutputStream(socket.getOutputStream)
      out.writeUTF("create database mydb")
      out.flush()
      val result = in.readLine
      println(result)
    } catch {
      case e:IOException =>
        e.printStackTrace
    }
  }
}
