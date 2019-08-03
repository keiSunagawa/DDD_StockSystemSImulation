package me.kerfume.stocksim.domain

import core._

case class Owner(
  ID: Long,
  name: String,
  // companyID -> count
  stock: Map[Long, Int]
) {
  def hasStock(count: Int, companyID: Long): Boolean = ???
}

object Owner {
  trait Repository extends RepositoryCore[Owner]
  trait Presenter extends PresenterCore {
    def registedBuyOrder(ordeer: StockCompany.BuyOrder): Unit
  }
}
