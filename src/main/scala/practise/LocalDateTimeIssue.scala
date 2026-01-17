package practise

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object LocalDateTimeIssue extends App{

  case class Event(id: Int, eventDateTime: LocalDateTime) {
    private val formatter =
      DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")

    override def toString: String = {
      s"Event($id, ${eventDateTime.withSecond(0).withNano(0).format(formatter)})"
    }
  }

  val event =  Event(1, LocalDateTime.parse("2025-10-12T01:01:20"))

  println(event)

}
