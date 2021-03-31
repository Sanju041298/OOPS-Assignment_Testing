package com.knoldus.Operations
import java.util.UUID
import com.knoldus.Models.User
import com.knoldus.Validator.{EmailValidator, MobileNumberValidator}
import com.knoldus.db.dao



class userOperations(userData: dao[User],emailValidator: EmailValidator,mobileNoValidator: MobileNumberValidator) {

  def add(user: User): UUID ={
    if(emailValidator.EmailValid(user.emailId) && mobileNoValidator.mobileNumberIsValid(user.mobileNo)) {
      userData.add(user)
    }
    else {
      throw new IllegalArgumentException("email id or mobile no is not valid")
    }
  }

  def getById(id: UUID): User = {
    userData.getById(id)
  }

  def getAll: List[User] = userData.getAll

  def update(id:UUID,updatedUser: User): Boolean = {


    if(emailValidator.EmailValid(updatedUser.emailId) && mobileNoValidator.mobileNumberIsValid(updatedUser.mobileNo)) {
      userData.update(id,updatedUser)
    }
    else{
      throw new IllegalArgumentException("email id or mobile no is not valid")
    }
  }

  def delete(id: UUID): Boolean = {
    userData.delete(id)
  }

  def deleteAll(): Boolean = {
    userData.deleteAll()
  }



}
