package models

import slick.jdbc.PostgresProfile.api._
import scala.concurrent.Await
import scala.concurrent.duration.Duration

object DB extends App {
  val setup = DBIO.seq(
    CoffeeTable.coffees.schema.createIfNotExists,
    CoffeeTable.coffees += Coffee(0, "Espresso", 2.5),
    CoffeeTable.coffees += Coffee(0, "Latte", 3.0),
    CoffeeTable.coffees += Coffee(0, "Cappuccino", 3.5)
  )

  val db = Database.forConfig("postgres")
  val setupFuture = db.run(setup)

  Await.result(setupFuture, Duration.Inf)
}
