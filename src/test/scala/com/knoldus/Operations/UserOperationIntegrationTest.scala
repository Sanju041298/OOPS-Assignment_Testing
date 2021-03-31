package com.knoldus.Operations
import com.knoldus.Models.{User, UserType}
import com.knoldus.Validator.{EmailValidator, MobileNumberValidator}
import com.knoldus.db.UserData
import org.scalatest.flatspec.AnyFlatSpec

import java.util.UUID
class UserOperationIntegrationTest extends AnyFlatSpec {

  val emailValidator = new EmailValidator
  val mobileValidator = new MobileNumberValidator
  val userData = new UserData
  val UserOperations = new userOperations(userData,emailValidator,mobileValidator)

  /* add method tests */

  "add" should "not add if mobile number is invalid" in {
    
    val user : User = User(userName = "sanjay", userType = UserType.Admin, password = "sanjay@123",age = 23, emailId = "sanjay@gmail.com", mobileNo = 9630057820L,address = Some("Mandsaur"))

    assertThrows[RuntimeException](UserOperations.add(user))

  }

  it should "not add as email id is invalid" in {

    val user: User = User(userName = "sanjay",userType = UserType.Admin,password = "sanjay@123",age = 23,emailId = "sanjay@gmail",mobileNo = 9630057820L,address = Some("Mandsaur"))
    assertThrows[RuntimeException](UserOperations.add(user))

  }

  it should "add the user" in {

    val user: User = User(userName = "sanjay",userType = UserType.Admin,password = "sanjay@123",age = 23,emailId = "sanjay@gmail.com",mobileNo = 9630057820L,address = Some("Mandsaur"))

    val id = UserOperations.add(user)
    assert(Some(id).nonEmpty)
    UserOperations.delete(id)
  }

  /* Add method testcases ended */

/************************************************************************************/

/* getById method test cases */

"getById" should "throw an exception whn user id does not exists" in {
  assertThrows[RuntimeException](UserOperations.getById(UUID.randomUUID()))

}

  it should "return the user when user id exists" in {
    val user: User = User(userName = "sanjay",userType = UserType.Admin,password = "sanjay@123",age=23, emailId = "sanjay@gmail.com",mobileNo = 9630057820L,address = Some("Mandsaur"))
    val id = UserOperations.add(user)

    assert(UserOperations.getById().nonEmpty)
    UserOperations.delete(id)

  }

 /* getById method testcase end */
/* --------------------------------------------------------------*/
 /* getAll method test case */

  "getAll" should "return empty list when ListBuffer is empty" in {
    assert(UserOperations.getAll.isEmpty)
  }

  it should "return list of users when ListBuffer is not empty" in {
    val user:User = User(userName = "Sanjay",userType = UserType.Admin,password = "sanjay@123",age=23, emailId = "sanjay@gmail.com",mobileNo = 9630057820L,address = Some("Mandsaur"))
    val id = UserOperations.add(user)

    assert(UserOperations.getAll.nonEmpty)
    UserOperations.delete(id)
  }
 // getAll method test cases ended//
  /* ------------------------------------------------------------------*/

  "update" should "not update as mobile number and email id both are invalid" in {

    val user:User = User(userName = "Sanjay",userType = UserType.Admin,password = "sanjay@123",age=23, emailId = "sanjay@gmail.com",mobileNo = 9630057820L,address = Some("Mandsaur"))
    assertThrows[RuntimeException](UserOperations.update(Some(UUID.randomUUID()),user))
  }

  it should "not update as mobile no is invalid" in {

    val user:User = User(userName = "Sanjay",userType = UserType.Admin,password = "sanjay@123",age=23, emailId = "sanjay@gmail.com",mobileNo = 9630057820L,address = Some("Mandsaur"))
    assertThrows[RuntimeException](UserOperations.update(Some(UUID.randomUUID()),user))
  }

  it should "not update as email id is invalid" in {
    val user:User = User(userName = "sanjay", userType = UserType.Admin, password = "sanjay@123", age = 23, emailId = "sanjay@gmail.com", mobileNo = 9630057820L, address = Some("sanjay"))
    assertThrows[RuntimeException](UserOperations.update(Some(UUID.randomUUID()),user))
  }

  it should "update the user" in {
    val user:User = User(userName = "sanjay",userType = UserType.Admin, password ="sanjay@123",age=23,emailId = "sanjay@gmail.com",mobileNo = 9630057820L,address = Some("Mandsaur"))

    val  id = UserOperations.add(user)
    val updatedUser: User = User(userName = "piyush",userType = UserType.Admin,password = "piyush@123",age = 24, emailId = "piyush@gmail.com",mobileNo = 9630057820L,address = Some("Noida"))

    assert(UserOperations.update(id,updatedUser).nonEmpty)
    UserOperations.delete(id)
  }

  /* Update test cases Ended */


/* -------------------------------------------------------------------*/

/* deleteAll Method test cases */

  "deleteAll" should "not delete all users as ListBuffer is empty" in {

    assert(UserOperations.deleteAll.nonEmpty)

  }
  it should "delete all users if ListBuffer is not empty" in {
    val user:User = User(userName ="sanjay",userType = UserType.Admin ,password = "sanjay@123", age=23, emailId = "sanjay@gmail.com",mobileNo = 9630057820L, address = Some("Mandsaur"))
      UserOperations.add(user)

    UserOperations.deleteAll()
  }

  /* deleteAll Method TestCases Ended */
}
