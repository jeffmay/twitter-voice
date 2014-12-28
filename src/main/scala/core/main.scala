package core

import akka.actor.{Props, ActorSystem}
import scala.annotation.tailrec

object Main extends App {
  import Commands._

  val system = ActorSystem()
  val sentiment = system.actorOf(Props(
    new SentimentAnalysisActor with CSVLoadedSentimentSets with AnsiConsoleSentimentOutput
  ))

  // TODO: Support streaming from TweetStreamerActor to any other actor
  val stream = system.actorOf(Props(
    new TweetStreamerActor(TweetStreamerActor.twitterUri, sentiment) with OAuthTwitterAuthorization
  ))

  // TODO: Support follow command
  @tailrec
  private def commandLoop(): Unit = {
    Console.readLine() match {
      case QuitCommand             => return
      case TrackCommand(query)     => stream ! query
      case _                       => println("WTF??!!")
    }

    commandLoop()
  }

  Console.println("Awaiting command...")
  // TODO Print argument list

  // start processing the commands
  commandLoop()

  System.exit(0)

}

object Commands {

  val QuitCommand   = "quit"
  val TrackCommand = "track (.*)".r

}
