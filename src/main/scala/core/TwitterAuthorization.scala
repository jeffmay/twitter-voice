package core

import java.io.File

import com.typesafe.config.ConfigFactory
import core.OAuth._
import spray.http.HttpRequest

trait TwitterAuthorization {

  def authorize(request: HttpRequest): HttpRequest
}

trait OAuthTwitterAuthorization extends TwitterAuthorization {

  val config = {
    val home = System.getProperty("user.home")
    ConfigFactory.parseFile(new File(s"$home/.twitter-voice/config"))
  }

  val consumer = Consumer(config.getString("app.key"), config.getString("app.secret"))
  val token = Token(config.getString("token.value"), config.getString("token.secret"))

  val authorizer = OAuth.Authorizer(consumer, token)

  override def authorize(request: HttpRequest): HttpRequest = authorizer.authorize(request)
}
