package com.knoldus.Operations
import com.knoldus.Validator.{EmailValidator, MobileNumberValidator}
import com.knoldus.db.UserData
import com.knoldus.Models.{User, UserType}
import org.mockito.MockitoSugar.{mock, when}
import org.scalatest.flatspec.AnyFlatSpec

import java.util.UUID
import scala.collection.mutable.ListBuffer
import scala.language.postfixOps
class UserOperationTest extends AnyFlatSpec {

  val mockedEmailValidator: EmailValidator = mock[EmailValidator]
  val mockedMobileNoValidator: MobileNumberValidator = mock[MobileNumberValidator]
  val mockedUserDb: UserData = mock[UserData]
  val uuid: UUID = UUID.randomUUID()

  val UserOperations = new userOperations(mockedUserDb, mockedEmailValidator, mockedMobileNoValidator)
  val user: User = User(userName = "sanjay", userType = UserType.Admin, password = "sanjay@123", age = 24, emailId = "sanjay@gmail.com", mobileNo = 9999666658L, address = Some("Mandsaur"))

  /* Add Method tests */

  "add" should "not add as mobile number & email Id both are invalid" in {
    when(mockedEmailValidator.EmailValid(user.emailId)) thenReturn false
    when(mockedMobileNoValidator.mobileNumberIsValid(user.mobileNo)) thenReturn false

    assertThrows[RuntimeException](UserOperations.add(user))
  }

  it should "not add as mobile no is invalid" in {

    when(mockedEmailValidator.EmailValid(user.emailId)) thenReturn true
    when(mockedMobileNoValidator.mobileNumberIsValid(user.mobileNo)) thenReturn false

    assertThrows[RuntimeException](UserOperations.add(user))
  }

  it should "not add as email id is invalid" in {

    when(mockedEmailValidator.EmailValid(user.emailId)) thenReturn false
    when(mockedMobileNoValidator.mobileNumberIsValid(user.mobileNo)) thenReturn true

    assertThrows[RuntimeException](UserOperations.add(user))
  }

  it should "add the user" in {

    when(mockedEmailValidator.EmailValid(user.emailId)) thenReturn true
    when(mockedMobileNoValidator.mobileNumberIsValid(user.mobileNo)) thenReturn true
    when(mockedUserDb.add(user)) thenReturn UUID.randomUUID()

    val result = UserOperations.add(user)
    assert(Some(result).nonEmpty)
  }

/*****************************************************************************************************************/

  "getById" should "throw exception when user id does not exist" in {

   when(mockedUserDb.getById(user.id)) thenReturn true
    assertThrows[RuntimeException] (UserOperations.getById(user.id))
 }

  it should "return the user when  when id exist" in {
    val uuid = UUID.randomUUID()
    when(mockedUserDb.getById(Some(uuid))) thenReturn List(user)

   var result  = UserOperations.getById(Some(uuid))
    assert(Some(result).nonEmpty)

  }

  /* getById Method test cases ends */

/****************************************************************************************************************/

"getAll " should "return empty list when ListBuffer is empty" in {
  when(mockedUserDb.getAll.isEmpty) thenReturn true

  val result  =  UserOperations.getAll
  assert(result.isEmpty)
}

  it should "return list of users when ListBuffer is not empty" in {
    when(mockedUserDb.getAll.nonEmpty) thenReturn true

    assert(UserOperations.getAll.nonEmpty)
  }

  /** getAll Methods Test Ends */
/*********************************************************************************/

  "update" should  "not update as mobile number and email id both are invalid" in {

    when(mockedEmailValidator.EmailValid(user.emailId)) thenReturn false
    when(mockedMobileNoValidator.mobileNumberIsValid(user.mobileNo)) thenReturn false

    assert(UserOperations.update(Some(UUID.randomUUID())))

  }
  it should "not update as mobile number is invalid" in {

    when(mockedEmailValidator.EmailValid(user.emailId)) thenReturn true
    when(mockedMobileNoValidator.mobileNumberIsValid(user.mobileNo))  thenReturn false

  }





}
