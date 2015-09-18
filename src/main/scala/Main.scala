import actors.Erasable
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import cfg.Config
import db.LocationDaoImpl
import routes.Routes
import scala.concurrent.duration._



/**
 * Author: @aguestuser
 * License: GPLv3 (https://www.gnu.org/licenses/gpl-3.0.html)
 */


object Main
  extends App
  with Config
  with Routes
  with Erasable {

  override implicit val system = ActorSystem()
  override implicit val executor = system.dispatcher
  override implicit val materializer = ActorMaterializer()
  val dao = LocationDaoImpl(db)

  dao.build map { _ ⇒

    Http().bindAndHandle(route(LocationDaoImpl(db)), httpInterface, httpPort)
    println(s"Server online at http://localhost:$httpPort")

    scheduleErase(system, dao, 1 hour)
  }
}