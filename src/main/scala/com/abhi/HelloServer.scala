package com.abhi

import com.twitter.finagle.{Http, ListeningServer}
import com.abhi.service._
import com.abhi.filter._
import java.time.Clock
import com.twitter.util.Future

class HelloServer {
  private var server: ListeningServer = _
  private val service = new HelloService(Clock.systemUTC()).service
  def start() : Future[Unit] = {
    Http.serve(s":8080", service)
    Future.Done
  }
  def stop() : Future[Unit] = server.close()
}
