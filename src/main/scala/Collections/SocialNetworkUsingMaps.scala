package Collections

import scala.:+

object SocialNetworkUsingMaps extends App {


  val addPerson = (name: String, socialNetwork: Map[String, List[String]]) => socialNetwork + (name -> List(""))


  val addFriend = (socialNetwork: Map[String,List[String]], a: String, b: String) => {
    val friendsA = socialNetwork(a)
    val friendsB = socialNetwork(b)

    socialNetwork + (a -> (friendsA :+ b)) + (b -> (friendsB :+ a))
  }

  def unfriend(fromPerson: String, removedPerson: String, socialNetwork: Map[String,List[String]]) = {
    val friendsA = socialNetwork(fromPerson)
    val friendsB = socialNetwork(fromPerson)

    socialNetwork + (fromPerson -> (friendsA.filter(elem => !elem.equals(removedPerson)))) + (removedPerson -> (friendsB.filter(elem => !elem.equals(fromPerson))))
  }

  val removePerson = (socialNetwork: Map[String,List[String]], a: String) => {
    val friendsA = socialNetwork(a)
    def removeHelper(friendsList: List[String], network: Map[String,List[String]]):Map[String,List[String]] = {
      if(friendsList.isEmpty) {
        network
      } else {
        removeHelper(friendsList.tail, unfriend(a, friendsList.head, socialNetwork))
      }
    }

    val removedList = removeHelper(friendsA, socialNetwork)

    removedList.removed(a)
  }

  val myNetwork = addPerson("Bhargava", addPerson("Adithya", addPerson("Uma Shankar", Map.empty[String, List[String]])))
  val updatedFriendsNetork = addFriend(myNetwork, "Bhargava","Adithya") ++ addFriend(myNetwork, "Adithya", "Uma Shankar")
  println(updatedFriendsNetork)

}
