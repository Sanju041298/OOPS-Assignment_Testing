package com.knoldus.Operations
import com.knoldus.Validator.{EmailValidator, MobileNumberValidator}
import com.knoldus.db.UserData
import com.knoldus.Models.{User,UserType}
import org.mockito.MockitoSugar.{mock,when}
import org.scalatest.flatspec.AnyFlatSpec
import java.util.UUID
class UserOperationTest extends AnyFlatSpec {

  val mockedEmailValidator: EmailValidator = mock[EmailValidator]
  val mockedMobileNoValidator: MobileNumberValidator = mock[MobileNumberValidator]
  val mockedUserDb: UserData = mock[UserData]


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






}
