import cats.data.Kleisli
import org.http4s.{HttpRoutes, Request, Response, Status}
import org.http4s.dsl.io._
import cats.effect.{ExitCode, IO, IOApp, Sync}
import org.http4s.dsl.impl.Root
import org.http4s.server.blaze.BlazeServerBuilder
import org.http4s.implicits._

import scala.concurrent.ExecutionContext.global

object Main extends IOApp {

  val helloWorldService: Kleisli[IO, Request[IO], Response[IO]] = HttpRoutes.of[IO] {
    case GET -> Root =>
      Ok(s"http4s")
  }.orNotFound

  def middlewareService(service: Kleisli[IO, Request[IO], Response[IO]])(implicit sync: Sync[IO]) = Kleisli { (req: Request[IO]) =>
    service(req).map{
      case Status.Successful(res) => res
      case res => res
    }
  }

  def run(args: List[String]): IO[ExitCode] =
    BlazeServerBuilder[IO](global)
      .bindHttp(8080, "localhost")
      .withHttpApp(middlewareService(helloWorldService))
      .serve
      .compile
      .drain
      .as(ExitCode.Success)
}
