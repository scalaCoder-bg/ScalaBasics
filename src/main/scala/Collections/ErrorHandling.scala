package Collections

import scala.util.Random
import scala.util.Try

object ErrorHandling extends App{

  val hostname = "localhost"
  val port = "8080"

  def renderHtml(page:String) = {
    println(page)
  }

  class Connection {
    def get(url:String):String = {
      val random = new Random(System.nanoTime())
      if(random.nextBoolean()) "</html>.....</html>"
      else throw new RuntimeException(("Connection Interupted"))
    }
  }

  object HttpService {
    def getConnection(host: String, port : String): Connection = {
      val random = new Random(System.nanoTime())
      if (random.nextBoolean()) new Connection
      else throw new RuntimeException(("Port taken"))
    }
  }

  val result = Try{HttpService.getConnection(hostname, port)}.flatMap(conn => Try{conn.get(hostname + port)})

  if(result.isSuccess) {
    renderHtml(result.get)
  } else {
    println("Failed with ex: ")
  }

  for {
    conn <- Try{HttpService.getConnection(hostname, port)}
    htmlPage <- Try{conn.get("url")}
  } yield renderHtml(htmlPage)



}
