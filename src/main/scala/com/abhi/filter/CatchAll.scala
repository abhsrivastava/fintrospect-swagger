package com.abhi.filter

import com.twitter.finagle.http.{Request, Response}
import com.twitter.finagle.{Filter, Service, SimpleFilter}
import io.fintrospect.formats.Circe.ResponseBuilder._

object CatchAll {
  def apply() : Filter[Request, Response, Request, Response] = new SimpleFilter[Request, Response] {
    override def apply(request: Request, service: Service[Request, Response]) = 
      service(request).handle {
        case e: Throwable => InternalServerError(e.getMessage)
      }
  }
}