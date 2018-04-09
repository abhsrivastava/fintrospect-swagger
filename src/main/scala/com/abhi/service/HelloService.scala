package com.abhi.service

import com.abhi.api._
import com.abhi.filter._
import java.time.Clock
import com.twitter.finagle.http.path.Root

class HelloService(clock: Clock) {
  val service = HelloFilter(clock, (s: String) => println(s)).andThen(CatchAll()).andThen(HelloAPI.module(Root).toService)
}