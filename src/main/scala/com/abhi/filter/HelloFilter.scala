package com.abhi.filter

import java.time.Clock
import com.twitter.finagle.http.{Request, Response}
import com.twitter.finagle.{Service, SimpleFilter}
import com.twitter.util.Future

object HelloFilter {
  def apply(clock: Clock, log: String => Unit) = new SimpleFilter[Request, Response] {
    override def apply(request: Request, service: Service[Request, Response]) : Future[Response] = {
      service(request).onSuccess { response => 
        log(s"${clock.instant.toString}: uri=${request.method}:${request.uri} status:${response.status.code}")
      }
    }
  }
}