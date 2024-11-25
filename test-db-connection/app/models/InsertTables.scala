package models

import models.DB.setupFuture
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.Await
import scala.concurrent.duration.Duration

object InsertTables extends App {
  val setup = DBIO.seq(
    CoffeeTable.coffees += Coffee(0, "New coffee", 200)
  )
  val db = Database.forConfig("postgres")
  val setupFuture = db.run(setup)
  Await.result(setupFuture, Duration.Inf) // Без этой строки база данных не обновиться
}

