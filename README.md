#Spray client, Akka and streaming tweets

Twitter streaming & simple sentiment analysis application. To build & run the plain-vanilla version of the application, run ``sbt run``. Then you can type in the ``track`` command, which expects the Twitter search term to track. See https://dev.twitter.com/docs/api/1.1/post/statuses/filter for ``track`` filter.

##Twitter application
Before you run the application, create the ``~/.twitter-voice/config`` file, containing four configuration params; these represent your twitter consumer key and secret, as well as token value and token secret. To generate these values, head over to https://dev.twitter.com/apps/, create an application and add the appropriate config values to this file from the `Keys and Access Tokens` tab.

An example ``~/.twitter-voice/config`` is

```
app {
  key = <Consumer Key (API Key)>
  secret = <Consumer Secret (API Secret)>
}
token {
  value = <Access Token>
  secret = <Access Token Secret>
}
```

##Running
Having added the file above, you can see the application "in action", by run ``sbt run`` in an ANSI terminal. Once running, type in ``track christmas``, ``track daley``, or anything else that tickles your fancy.
