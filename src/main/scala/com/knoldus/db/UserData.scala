package com.knoldus.db
import com.knoldus.Models.User

import java.util.UUID
import scala.collection.mutable.ListBuffer
import scala.math.Ordered.orderingToOrdered


class UserData extends  dao[User] {

  private var listBuffer = new ListBuffer[User]()

  override def add(user: User): UUID = {
    val uuid = UUID.randomUUID()

    user match {
      case user: User => listBuffer += user.copy(id=Some(uuid))
        uuid

      case User(Some(_), _, _, _, _, _, _, _) => throw new RuntimeException("Invalid operations")


    }
  }

  override def getById(id: UUID): User = {
    val list = filterListById(id)
    println(list)
    if (list != null) list else throw new NoSuchElementException("user not exist")
  }

  override def getAll: List[User] = {

    if (listBuffer.toList != Nil) listBuffer.toList else throw new NoSuchElementException("No user found")
  }

  override  def update(id: UUID, user: User): Boolean = {
     val temp = User(Some(id),user.userName, user.userType, user.password , user.age , user.emailId , user.mobileNo, user.address)
    val index = findIndexById(id)
    if (index != -1) {
      listBuffer.update(index, temp)
     // println(listBuffer(index).id + " hello")
      true
    } else false
  }

  override  def deleteAll(): Boolean = {
    if (listBuffer.nonEmpty) { listBuffer.remove(0, listBuffer.length); true} else false

  }


  def filterListById(id:UUID):User = {
   val temp =  listBuffer.filter(listBuffer => if(listBuffer.id.compareTo(Some(id)) == 0) true else false).toList
    println(temp+ " filter called"+id)
    temp
    var elem:User = null
    for(a <- listBuffer){
      println(a+"har ek elem")
      if(a.id.contains(id)) elem = a
    }
    println(elem+"ye return krra hu")
    elem
  }

  def findIndexById(id:UUID): Int = {
    val list = filterListById(id)
    if(list != null) listBuffer.indexOf(list) else -1
  }

  override def delete(id: UUID): Boolean = {
   /* val index = findIndexById(id)
    if (index != -1) {
      listBuffer.remove(index); true
    } else false
*/println("----------------------------------------------------------")
    println(listBuffer)
    println("----------------------------------------------------")
    for(a <-listBuffer){
      if(a.id.contains(id)){
        listBuffer.remove(listBuffer.indexOf(a))
        return true
      }
    }
    println(listBuffer)
    println("------------------------------------------------")
    false
  }


}
