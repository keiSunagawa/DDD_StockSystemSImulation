package me.kerfume.stocksim.domain

import core._

case class StockCompany(
  ID: Long,
  name: Int
) {
}

object StockCompany {
  case class BuyOrder(ID: Long)
  object BuyOrder {
    def create(scomp: StockCompany, owner: Owner, org: Organization, count: Int): Result[BuyOrder] = ???
  }
  trait Repository extends RepositoryCore[StockCompany] {
    def registBuyOrder(order: BuyOrder): Result[Unit]
  }
  trait Presenter extends PresenterCore
}
