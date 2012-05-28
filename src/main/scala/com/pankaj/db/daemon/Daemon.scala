package com.pankaj.db.daemon

import java.io._
import java.net.{InetAddress,ServerSocket,Socket,SocketException}

object Daemon {
  def main(args:Array[String]) {
    try {
      val listener = new ServerSocket(9999)
      while(true) {
        new ServerThread(listener.accept()).start()
      }
      listener.close()
    } catch {
      case e: IOException => 
        System.err.println("Could not listen on port")
        e.printStackTrace()
    }
  }

  case class ServerThread(socket:Socket) extends Thread("Server Thread") {
    def readCommand(in:BufferedReader) = {
      var succeeded = true;
      var command = ""
      while(succeeded) {
        val line = in.readLine()
        if (line.isEmpty) 
          succeeded = false
        else {
          command = line
        }
      }
      command
    }

    override def run() {
      try {
        val in = new BufferedReader(new InputStreamReader(socket.getInputStream))
        val out = new PrintWriter(socket.getOutputStream, true)
        val router:Router = new Router
        val command = readCommand(in)
        try {
          val result = router.route(command)
          out.println(result)
        } catch {
          case e:Exception => {
            e.printStackTrace
            out.println("Error: %s".format(e))
          }
        }
        in.close
        out.close
      } catch {
        case e:SocketException => ()
        case e:IOException => e.printStackTrace
      } finally {
        socket.close
      }
    }
  }
  
  /*
  def main(args:Array[String]) {
    try {
      val ia = InetAddress.getByName("localhost")
      val socket = new Socket(ia, 19999)
      val out = new ObjectOutputStream(
        new DataOutputStream(socket.getOutputStream()))
      val in = new DataInputStream(socket.getInputStream)
      while(true) {
        val s = in.readLine()
        println(s)
      }
    }
    catch {
      case e: IOException => 
        e.printStackTrace()
    }
  }
  */
}
