package com.knoldus.Validator

class MobileNumberValidator {


  def mobileNumberIsValid(mobileNo: Long): Boolean = {
     val a = mobileNo.toString.matches("(0/91)?[7-9][0-9]{9}")
 println(a +"MObile Number Valid")
    a
  }
}
