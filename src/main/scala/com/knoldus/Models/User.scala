package com.knoldus.Models
import java.util.UUID

case class User(


                 id: Option[UUID]=None,
                 userName: String,
                 userType: UserType,
                 password: String,
                 age: Int,
                 emailId: String,
                 mobileNo: Long,
                 address: Option[String]
               ) {



}
