package AdvancedScala.Implicits

import java.time.{LocalDate, LocalDateTime}
import java.util.UUID
import scala.concurrent.Future

object JsonSerialiser extends App {

  case class BillingCycle(id: UUID, period: Periodicity, name: String, time: LocalDateTime)

  case class Customer(name: String, customerType: CustomerType, billCycleList: List[BillingCycle])

  sealed trait Periodicity

  case object Monthly extends Periodicity

  case object biMonthly extends Periodicity

  case object Quarterly extends Periodicity

  case object halfYearly extends Periodicity

  case object Yearly extends Periodicity

  case object Weekly extends Periodicity

  case object FortNightly extends Periodicity


  sealed trait CustomerType

  case object Corporate extends CustomerType

  case object Individual extends CustomerType


  sealed trait JsonValue {
    def stringify: String
  }

  final case class JsonString(value: String) extends JsonValue {
    override def stringify: String = "\"" + value.toString + "\""
  }

  final case class JsonNumber(value:Int) extends JsonValue {
    override def stringify: String = value.toString
  }

  final case class JsonArray(value :List[JsonValue]) extends JsonValue {
    override def stringify: String = value.map(_.stringify).mkString("[", ",", "]")
  }

  final case class JsonObject(jsonObject: Map[String, JsonValue]) extends JsonValue {
    override def stringify: String = jsonObject.map((key, value) => "\"" + key + "\":" + value.stringify).mkString("{", "," ,"}")
  }

  println(JsonObject(Map(
    "name" -> JsonString("Bhargava"),
    "age" -> JsonNumber(25),
    "hobbies" -> JsonArray(List(
      JsonString("Badminton"), JsonString("Cricket")
    ))
  )).stringify)

  trait JsonConversions[T] {
    def toJson(value: T): JsonValue
  }

  implicit object StringConverter extends JsonConversions[String] {
    override def toJson(value: String): JsonValue = JsonString(value)
  }

  implicit object IntegerConverter extends JsonConversions[Int] {
    override def toJson(value: Int): JsonValue = JsonNumber(value)
  }

  implicit object BillingCycleConverter extends JsonConversions[BillingCycle] {
    override def toJson(value: BillingCycle): JsonValue = {JsonObject(Map(
      "id" -> JsonString(value.id.toString),
      "period" -> JsonString(value.period.toString),
      "name" -> JsonString(value.name),
      "time" -> JsonString(value.time.toString)
    ))}
  }

  implicit object CustomerConversion extends JsonConversions[Customer] {
    override def toJson(value: Customer): JsonValue = JsonObject(Map(
      "name" -> JsonString(value.name),
      "customerType" -> JsonString(value.customerType.toString),
      "billCycleList" -> JsonArray(value.billCycleList.map(BillingCycleConverter.toJson))
    )
    )
  }

  implicit class SerializeCustomer[T](value: T) {
    def convertToJson(implicit converter: JsonConversions[T]):String = converter.toJson(value).stringify
  }

  val billingCycle1 = BillingCycle(
    id = UUID.randomUUID(),
    period = Monthly,
    name = "Monthly Cycle",
    time = LocalDateTime.now()
  )

  val billingCycle2 = BillingCycle(
    id = UUID.randomUUID(),
    period = Quarterly,
    name = "Quarterly Cycle",
    time = LocalDateTime.now().minusMonths(3)
  )

  val customer1 = Customer(
    name = "John Doe",
    customerType = Individual,
    billCycleList = List(billingCycle1, billingCycle2)
  )

  val customer2 = Customer(
    name = "Acme Corp",
    customerType = Corporate,
    billCycleList = List(billingCycle2)
  )

  println(customer1.convertToJson)
  println(billingCycle1.convertToJson)

  type Adder = Int => Int

  val adder: Adder = _ + 2



}
