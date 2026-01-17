package ObjectOrientedScala

object ClassPlayAround extends App {

  class Employee(name: String, company: String, salary: Int) {
    def printEmployee = println(s"name : $name , company : $company , salary $salary")
  }

  object Employee {
    def apply(name: String) = println(s"Employee Name: $name")
  }


  val bgEmployee = Employee("Bhargava")

  println(bgEmployee)

}
