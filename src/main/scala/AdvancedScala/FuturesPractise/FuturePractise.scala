package AdvancedScala.FuturesPractise

import scala.concurrent.{Future, Promise}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Random, Success, Failure}

object FuturePractise {

  case class Person(id:String, name:String) {
    def poke(anotherProfile: Person): Unit = {
      println(s"${this.name} pokes ${anotherProfile.name}")
    }
  }

  object SocialNetwork {
    val names = Map(
      "1" -> "bhargava",
      "2" -> "akshay",
      "3"-> "raju"
    )

    val friends = Map(
      "bhargava" -> "2"
    )

    def getPerson(id: String) = Future {
      Thread.sleep(2000)
      Person(id, names(id))
    }

    def getFriendBasedOnProfile(profile:Person) = Future{
      Thread.sleep(2000)
      Person(friends(profile.name), names(friends(profile.name)))
    }
  }


  def main(args: Array[String]):Unit = {
    for {
      personProfile <- SocialNetwork.getPerson("100")
      friendsProfile <- SocialNetwork.getFriendBasedOnProfile(personProfile)
    } yield personProfile.poke(friendsProfile)



    def intRetirner = Future{
      Thread.sleep(2000)
      50
    }

    val promise = Promise[Int]

    promise.future.recover({
      case t : Throwable => 42
    }).onComplete{
      case Success(value) => println(s"I completed with a value $value")
    }

    val answer = new Thread(() => {
      println("I am preparing numbers")
      val random = new Random()
      if(false){
        promise.success(42)
      }
      else {
        promise.failure(new RuntimeException("I failed"))
      }
      println("Promise complete")
    })

    answer.start()

    Thread.sleep(5000)


  }

}
