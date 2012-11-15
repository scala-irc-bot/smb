package net.mtgto.irc.bot.smb

import org.specs2.mutable._

class SmbBotSpec extends Specification {
  "SmbBot" should {
    val bot = new SmbBot

    "convert windows smb path to mac path" in {
      bot.win2mac("""\\server\path\to\file""") must beSome("smb://server/path/to/file")
      bot.win2mac("""\\server\path\to\file""" + "\\") must beSome("smb://server/path/to/file/")
      bot.win2mac("""\\ほげ\ふが\ぴよ""") must beSome("smb://ほげ/ふが/ぴよ")
    }

    "not convert invalid windows smb path to mac path" in {
      bot.win2mac("""\server\path\to\file""") must beNone
      bot.win2mac("""\\\server\path\to\file""") must beNone
      bot.win2mac("""\\server\\path\to\file""") must beNone
      bot.win2mac("""\\""") must beNone
    }

    "convert mac smb path to windows path" in {
      bot.mac2win("smb://server/path/to/file") must beSome("""\\server\path\to\file""")
      bot.mac2win("smb://server/path/to/file/") must beSome("""\\server\path\to\file""" + "\\")
      bot.mac2win("smb://ほげ/ふが/ぴよ") must beSome("""\\ほげ\ふが\ぴよ""")
    }

    "not convert invalid mac smb path to windows path" in {
      bot.mac2win("/server/path/to/file") must beNone
      bot.mac2win("smb:///server/path/to/file") must beNone
      bot.mac2win("smb://server//path/to/file") must beNone
      bot.mac2win("smb://") must beNone
    }
  }
}
