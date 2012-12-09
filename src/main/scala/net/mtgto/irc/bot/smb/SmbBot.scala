package net.mtgto.irc.bot.smb

import net.mtgto.irc.{Bot, Client}
import net.mtgto.irc.event._

import util.matching.Regex
import util.matching.Regex.Match

class SmbBot extends Bot {
  private val winPathRegexp = """^\\((?:\\(?:[^\\]+))+)(\\?)$""".r

  private val winSubPathRegexp = """\\([^\\]+)""".r

  private val macPathRegexp = """^smb:/((?:/(?:[^/]+))+)(/?)$""".r

  private val macSubPathRegexp = """/([^/]+)""".r

  override def onMessage(client: Client, message: Message) = {
    win2mac(message.text) map {
      convertedPath => client.sendNotice(message.channel, convertedPath)
    }
    mac2win(message.text) map {
      convertedPath => client.sendNotice(message.channel, convertedPath)
    }
  }

  /**
   * Windows風のパスをMac風に変換する
   */
  def win2mac(path: String): Option[String] = {
    winPathRegexp findFirstMatchIn path map {
      case winPathRegexp(subPath, postfix) => "smb:/" + {
        winSubPathRegexp replaceAllIn (subPath, (m: Match) =>
          "/" + m.group(1)
        )
      } + (if (postfix.length > 0) "/" else "")
    }
  }

  /**
   * Mac風のパスをWindows風に変換する
   */
  def mac2win(path: String): Option[String] = {
    macPathRegexp findFirstMatchIn path map {
      case macPathRegexp(subPath, postfix) => "\\" + {
        macSubPathRegexp replaceAllIn (subPath, (m: Match) =>
          Regex.quoteReplacement("\\") + m.group(1)
        )
      } + (if (postfix.length > 0) "\\" else "")
    }
  }
}
