package models
import slick.jdbc.PostgresProfile.api._
import scala.concurrent.Future

case class Coffee(id: Long, name: String, price: Double)

class Coffees(tag: Tag) extends Table[Coffee](tag, "COFFEES") {
  def id = column[Long]("ID", O.PrimaryKey, O.AutoInc)
  def name = column[String]("NAME")
  def price = column[Double]("PRICE")

  def * = (id, name, price) <> (Coffee.tupled, Coffee.unapply)
}

object CoffeeTable {
  val coffees = TableQuery[Coffees]

  def getAll()(implicit db: Database): Future[Seq[Coffees]] = {
    db.run(coffees.result)
  }
}

