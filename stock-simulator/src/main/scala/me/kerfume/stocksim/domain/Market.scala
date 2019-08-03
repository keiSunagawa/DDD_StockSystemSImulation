package me.kerfume.stocksim.domain

import core._

case class Market(
  ID: Long
) {
  def publishStock(org: Organization, count: Int): Result[Unit] = ???
}

object Market {
  trait Repository extends RepositoryCore[Market] {
    def defaultMarket(): Market
  }
  trait Presenter extends PresenterCore
}
