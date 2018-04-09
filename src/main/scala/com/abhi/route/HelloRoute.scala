package com.abhi.route

import io.fintrospect._
import com.twitter.finagle.Service
import com.twitter.finagle.http._
import com.twitter.util.Future
import io.circe.generic.auto._
import io.fintrospect.formats.Circe.JsonFormat.encode
import io.fintrospect.formats.Circe.ResponseBuilder._
import com.twitter.finagle.http.Method.Get
import io.fintrospect.parameters.Path

object HelloRoute {
  val name = Path.string("name")
  def route() : ServerRoute[Request, Response] = {
    def sayHello(name: String) = Service.mk[Request, Response] { _ =>
      Future(Ok(encode(s"Hello $name")))
    }
    RouteSpec("This is my hello world API")
      .returning(Ok(encode("Hello name")))
      .at(Get) / "hello" / name bindTo sayHello
  }
}