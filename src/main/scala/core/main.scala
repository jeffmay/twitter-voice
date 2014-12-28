package core

import akka.actor.{Props, ActorSystem}
import core.TweetStreamerActor.AddProcessor
import scala.annotation.tailrec
import scala.io.StdIn

object Main extends App {
  import Commands._

  val system = ActorSystem()
  val sentiment = system.actorOf(Props(
    new SentimentAnalysisActor with CSVLoadedSentimentSets with AnsiConsoleSentimentOutput
  ))

  // TODO: Support streaming from TweetStreamerActor to any other actor
  val stream = system.actorOf(Props(
    new TweetStreamerActor(TweetStreamerActor.twitterUri) with OAuthTwitterAuthorization
  ))

  // TODO: Support follow command
  @tailrec
  private def commandLoop(): Unit = {
    StdIn.readLine() match {
      case QuitCommand             => return
      case TrackCommand(query)     => stream ! query
      case cmd                     => println(s"Unrecognized command: '$cmd'")
    }

    commandLoop()
  }

  system.actorOf(Props())

  Console.println("Awaiting command...")
  // TODO Print argument list

  // start processing the commands
  stream ! AddProcessor(sentiment)
  commandLoop()

//  System.exit(0)

}

object Commands {

  val QuitCommand   = "quit"
  val TrackCommand = "track (.*)".r

}
