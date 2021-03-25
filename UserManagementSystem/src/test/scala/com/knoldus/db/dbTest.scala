package com.knoldus.db

import com.knoldus.Models.{User, UserType}
import org.scalatest.flatspec.AsyncFlatSpec

import java.util.UUID

class dbTest extends AsyncFlatSpec{

  val  userData = new UserData

  val userById:User = User(Some(UUID.randomUUID()),"sanjay",UserType.Admin,"sanjay@123",24,"sanjay@gmail.com",9967840658L,Some("Mandsaur"))

  val user:User = User(userName = "sanjay",userType = UserType.Admin,password = "sanjay@123",age = 24,emailId = "sanjay@gmail.com",mobileNo = 9967840658L,address = Some("Mandsaur"))

  "add" should "add the user" in {
    val id = userData.add(user)
    assert(Some(id).nonEmpty)

  }

  "getById" should "not return the user when user id does not exists" in {
    assertThrows[RuntimeException](userData.getById(UUID.randomUUID()))
  }

  it should "return the user when user id exists" in {
    val id = userData.add(user)
    assert(userData.getById(id) != null)

  }


  "update"  should "not update when user id not exists" in {

    assert(!userData.update(UUID.randomUUID(),user))
    
  }
  
  it should "update when user id exists" in {

    val id = userData.add(user)
    val updatedUser : User = User(Some(id),"sanjay",userType = UserType.Admin, "sanjay@123",24,"sanjay@gmail.com",9867840658L, Some("Mandsaur"))
    assert(userData.update(id,updatedUser))
  }


  "delete" should "not delete when user id does not exists" in {
    assert(!userData.delete(UUID.randomUUID()))
  }

  it should "delete the user when id does not exists" in {
    val id = userData.add(user)
    assert(userData.delete(id))
  }

  "deleteAll" should "not delete when no user exists" in {
    assert(!userData.deleteAll())
  }

  it should "delete all users when users exists" in {
    userData.add(user)
    assert(userData.deleteAll())
  }

}
