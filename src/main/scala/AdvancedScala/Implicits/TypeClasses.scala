package AdvancedScala.Implicits

object TypeClasses extends App{

  case class User(name:String, age: Int, email: String)

  trait Equals[T] {
    def equals(x:T, y:T):Boolean
  }

  object Equals {
    def apply[T](implicit equals: Equals[T]) = equals
  }

  implicit object UserNameComparator extends Equals[User] {
    override def equals(x:User, y:User):Boolean = {
      x.name == y.name
    }
  }

  object UserNameAndEmailComparator extends Equals[User] {
    override def equals(x: User, y: User): Boolean = {
      (x.name == y.name) && (x.email == y.email)
    }
  }

  println(Equals[User].equals(User("Bhargava", 25, "email1"), User("Bhargava", 25, "email2")))

  implicit class RichString(val string: String) extends AnyVal {
    def asInt: Int = string.toInt
    def encrypt: String = {
      string.foldLeft("")((result, char) => {
        result + (char + 2).toChar
      })
    }
  }

  println("Bhargava".encrypt)

}
