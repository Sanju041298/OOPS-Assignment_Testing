package com.knoldus.Models

sealed trait UserType

object UserType {
  case object Customer extends UserType
  case object Admin extends UserType

}
