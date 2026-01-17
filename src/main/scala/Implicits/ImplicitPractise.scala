package Implicits

import scala.language.implicitConversions

object ImplicitPractise extends App{

  case class Employee(
                     name:String,
                     employeeId: Int,
                     companyName: String
                     )

  implicit val defaultString: String = "Bhargava"

  private def greeting(greeter:String)(implicit name:String): Unit = {
    println(s"$greeter, $name")
  }

  greeting("Hello")

  implicit class EmployeeOps(employee: Employee) {
    def employeeName: String = employee.name
    def employeeId: Int = employee.employeeId
  }

  implicit def intToString(x:Int):String = s"String : $x"

  val bhargavEmploy = Employee("Bhargava", 12098, "Tecnotree")

  println(bhargavEmploy.employeeName)

  val myString:String = 10

  println(myString)

  implicit class StringImplicitClass(str: String) {
    def bark:String = "wooff wooffff"
  }

  println("dog".bark)




}
