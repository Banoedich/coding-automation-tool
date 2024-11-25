package controllers

import models.CoffeeTable
import models.DB.setup

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import slick.dbio.DBIO
import slick.jdbc.PostgresProfile.api._
import scala.concurrent.Await
import scala.concurrent.duration.Duration

case class CoffeeData(name: String, price: BigDecimal)




/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(val controllerComponents: MessagesControllerComponents) extends MessagesBaseController {
  import scala.concurrent.ExecutionContext.Implicits.global
  import scala.concurrent.Future
  import models.Coffee
  import models.CoffeeTable.coffees

  val coffeeForm = Form(
    mapping(
      "name" -> nonEmptyText,
      "price" -> bigDecimal(10, 2)
    )(CoffeeData.apply)(CoffeeData.unapply)
  )
  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */

  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }

  def get_form() = Action { implicit request =>
    Ok(views.html.form_html(coffeeForm))
  }

  def getFormData(name: String, price: Int) = Action {
    Ok(s"$name: $price")
  }

  // Для Action вызываем аргумент по имени
  def postFormData = Action { request =>
    val postVals = request.body.asFormUrlEncoded
    // Здесь данные закодированы в теле запроса, поэтому их нужно получить хитрым способом с помощью парсера
    postVals.map { args =>
      val name = args("name").head
      val price = args("price").head
      println(s"$name: $price")
      Redirect(routes.HomeController.index())
    }.getOrElse(
      Redirect(routes.HomeController.get_form())
    )
  }

  def validateCoffeeForm = Action { implicit request =>
    coffeeForm.bindFromRequest.fold(
      formWithErrors => BadRequest(views.html.form_html(formWithErrors)),
      ld => {
        println(ld.name, ld.price)
        val coffee = Coffee(0, ld.name, ld.price.toDouble)
        val setup = DBIO.seq(
          CoffeeTable.coffees += coffee
        )
        val db = Database.forConfig("postgres")
        val setupFuture = db.run(setup)
        Await.result(setupFuture, Duration.Inf)
        Redirect(routes.HomeController.get_form())
      }
    )
  }
}


