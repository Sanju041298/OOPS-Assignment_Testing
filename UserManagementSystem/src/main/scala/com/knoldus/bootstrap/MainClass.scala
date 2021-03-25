package com.knoldus.bootstrap
import com.knoldus.Models.{User, UserType}
import com.knoldus.Operations.userOperations
import com.knoldus.Validator.{EmailValidator, MobileNumberValidator}
import com.knoldus.db.{UserData, dao}

object MainClass extends App{

   val userData: dao[User] = new UserData
   val emailValidator = new EmailValidator
   val mobileNumberValidator = new MobileNumberValidator
   val UserOperations = new userOperations(userData,emailValidator,mobileNumberValidator)

  val admin  = User(userName = "sanjay",userType = UserType.Admin,password = "sanjay@123",age = 23,emailId = "sanjay@gmail.com",mobileNo = 7630057820L,address = Some("Mandsaur"))
  val customer = User(userName = "Arun",userType = UserType.Customer,password = "Arun@123",age = 22,emailId = "Arun@gmail.com",mobileNo = 9893467363L,address = Some("Noida"))

  val adminId = UserOperations.add(admin)
  val customerId = UserOperations.add(customer)

  print("list of all users" + "\n")

  for(index <- UserOperations.getAll.indices){
    println(index + "list of users")
    print("User " + (index + 1) + "\n")
    print("(UserName: " + UserOperations.getAll(index).userName + ", ")
    print("UserType: " + UserOperations.getAll(index).userType + ", ")
    print("Age : " + UserOperations.getAll(index).age + ", ")
    print("Email ID: " + UserOperations.getAll(index).emailId + ", ")
    print("Mobile No: " + UserOperations.getAll(index).mobileNo + ", ")
    print("Address: " + UserOperations.getAll(index).address.get + ")\n")
  }

  val updatedUser = User(userName = "Piyush",userType = UserType.Admin,password = "piyushsingh@123",age = 24,emailId = "piyushsingh@gmail.com",mobileNo = 9630057820L,address = Some("Noida"))

  print("\nUpdate method called: " + UserOperations.update(adminId,updatedUser) + "\n")
  print("Updated List of all users" + "\n")
  for(index <- UserOperations.getAll.indices){
    print("User " + (index + 1) + "\n")
    print("(UserName: " + UserOperations.getAll(index).userName + ", ")
    print("UserType: " + UserOperations.getAll(index).userType + ", ")
    print("Age : " + UserOperations.getAll(index).age + ", ")
    print("Email ID: " + UserOperations.getAll(index).emailId + ", ")
    print("Mobile No: " + UserOperations.getAll(index).mobileNo + ", ")
    print("Address: " + UserOperations.getAll(index).address.get + ")\n")
  }


  print("\nDelete method called: " + UserOperations.delete(customerId) + "\n")



}
