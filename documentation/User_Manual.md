# User Manual

Here is a quick user manual on how to test the bot on Lichess and XBoard.

Also the important Gradle commands are listed below.

## Gradle commands

With every command mentioned here it is assumed that you use Git Bash for Windows (I'm not sure what the commands are for linux/OSX systems, probably just "gradle *command*" )

* To build the project *./gradlew clean build*. For every other command to work the project has to be build before.
* To create checkstyle and jacoco reports *./gradlew check*. The reports can be found under */build/reports/jacoco or checkstyle*
* To run tests *./gradlew test*
* Javadoc can be create with *./gradlew javadoc*. Javadoc goes into */build/docs/javadoc*
* Clean project and start with clean table *./gradlew clean*
* Jar can be create with *./gradlew jar*. Javadoc goes into */build/libs/*

I think these are the most important Gradle commands.

## Running the bot

I haven't tested how the bot works with XBoard, so I can't put my head on the instructions to be 100% right. I took them from [Tira Chess library](https://github.com/TiraLabra/chess/blob/master/documentation/Beginners_guide.md) XBoard section.

For some reason in Lichess if you play black the blacks are on Ranks 1 and 2 in the beginning. My bot doesn't understand this. **So run the bot only by playing white side.**

### Lichess

Also very much copied from [Tira Chess library](https://github.com/TiraLabra/chess/blob/master/documentation/Beginners_guide.md). Why invent the wheel again. This was the method I did my acceptance testing.

1. Clone the Shakki-AI repository where you want it.
2. Register to [Lichess](https://lichess.org/signup) and agree to everything.
3. Create [New personal API access token](https://lichess.org/account/oauth/token/create) and choose all the scopes.
4. Upgrade your account to a bot:

    $ curl -d '' https<span></span>://lichess.org/api/bot/account/upgrade -H "Authorization: Bearer INSERT YOUR TOKEN HERE"

5. Select your way to pass your token to the Tiralabra chess bot.

    a) you can pass token as a commandline parameter or

    b) you can supply the token via the LICHESS_TOKEN environment variable

6. Now you can try the chess bot. One possible way to start the bot: after login, select “PLAY WITH THE COMPUTER” at https<span></span>://lichess.org/.
Then choose white side to play. Finally, at the command line, type
    *$ ./gradlew build*,
then if you inserted your token as environment variable:
    *./gradlew run --args="--lichess"*
or, if you didn't,
    *./gradlew run --args="--lichess --token=put_token_here"* .

7. Your chess bot will start playing.

**Note:** By default, program keeps running as it waits for new challenges from Lichess. To close the program use CTRL+C.

### XBoard

All the info below is from [Tira Chess library](https://github.com/TiraLabra/chess/blob/master/documentation/Beginners_guide.md) and not tested by yours truly. I battled with the xBoard installation for 1.5 hours before giving up. I installed cygwin and all kinds of different packages the xBoard said it needed. It was endless swamp.

XBoard is a graphical user interface chessboard for chess engines. Xboard can be connected to your tira chess engine.

1. Download the tar.gz file of the latest stable version of [XBoard](https://www.gnu.org/software/xboard/#download)

Uncompress it, for example, under the same directory where the chess directory is.

    $ tar xvzf xboard-4.9.0.tar.gz

2. Start XBoard:

    $ xboard

3. Make sure that you have a jar file for your engine:

    $ ./gradlew build

4. Under Engine tab, select Edit Engine List..

5. Add the path of the chess engine's jar file in the list

    "tira-chess" -fcp "java -jar /home/local/ ..your path.. /chess/build/libs/chess-all.jar"

You can replace "tira-chess" with any unique name.
Then click 'commit changes' and 'OK'.

6. Under Engine tab, select Load New 1st Engine..

Just select your engine from the list and click 'OK'.

7. Make your first move (as white) and your engine should respond with its move.

You can also select 'Machine White' under the Mode tab. In this case, your engine will play white pieces and make its move first.

With the "Two Machines" mode, you can also get two bots playing against each other.

**Note:** How to start a new XBoard game after one has ended? First under Edit, select New Game. Then just select the Mode.