package com.knoldus.Validator

class EmailValidator {

  def EmailValid(emailId: String): Boolean = {
    if("""^([a-zA-Z\d.-]+) @ ([a-zA-Z\d-]+)\.(com|net|org)$""".r.findFirstIn(emailId).isEmpty) {
      true
    }
    else {
      false
    }
  }

}
