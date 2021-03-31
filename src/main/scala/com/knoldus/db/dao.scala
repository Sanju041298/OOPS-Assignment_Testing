package com.knoldus.db
import java.util.UUID

trait dao[User] {
  def add(user: User):UUID

  def getById(id: UUID): User

  def getAll: List[User]

  def update(id: UUID, user: User):Boolean

  def delete(id: UUID): Boolean

  def deleteAll():Boolean

}
