package com.abhi.api

import com.abhi.route._
import com.twitter.finagle.Service
import io.fintrospect.{Module, ApiKey, RouteModule}
import com.twitter.finagle.http.filter.Cors.HttpFilter
import com.twitter.finagle.http.filter.Cors
import com.twitter.util.Future
import io.fintrospect.renderers.swagger2dot0.{ApiInfo, Swagger2dot0Json}
import com.twitter.finagle.http.path.Path
import io.fintrospect.parameters.Header

object HelloAPI {
  def module(path: Path) : Module = {
    val filter = new HttpFilter(Cors.UnsafePermissivePolicy)
    val apiKey = ApiKey(Header.required.string("key"), Service.mk { key: String => Future(key.equals("realSecret")) })
    RouteModule(
      path, 
      Swagger2dot0Json(ApiInfo("Project Name", "1.0", "Description of API")), 
      filter) 
    .withDescriptionPath(_ / "api-docs")
    .securedBy(apiKey)
    .withRoute(HelloRoute.route)    
  }
}