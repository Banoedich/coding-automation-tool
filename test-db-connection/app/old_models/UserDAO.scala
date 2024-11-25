package old_models

//package models
//
//import java.time.LocalDate
//import java.util.concurrent.Executors
//import scala.concurrent.{ExecutionContext, Future}
//import scala.util.{Failure, Success, Try}
//
////import scala.concurrent.ExecutionContext.global
////import javax.inject.{Inject, Singleton}
////import slick.jdbc.PostgresProfile.api._
////import scala.concurrent.{ExecutionContext, Future}
//
//object PrivateExecutionContext {
//  val executor = Executors.newFixedThreadPool(4)
//  implicit val ec: ExecutionContext = ExecutionContext.fromExecutorService(executor)
//}
//
//object UserDAO {
//  import slick.jdbc.PostgresProfile.api._
//  import PrivateExecutionContext._
//
//
//  val showshankRedemption = Movie(1L, "Shawshank Redemption", LocalDate.of(1994, 9, 23), 162)
//  def demoInsertMovie(): Unit = {
//    val queryDescription = SlickTables.movieTable += showshankRedemption
//    val futureId: Future[Int] = Connection.db.run(queryDescription)
//
//    futureId.onComplete {
//      case Success(newMovieId) => println(s"Query was successful, new id is $newMovieId")
//      case Failure(ex) => println(s"Query failed, reason: $ex")
//    }
//  }
//
//  def main(args: Array[String]): Unit = {
//    println("Hello")
//  }
//}