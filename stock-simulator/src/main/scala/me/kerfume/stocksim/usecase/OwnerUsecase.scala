package me.kerfume.stocksim.usecase

import me.kerfume.stocksim.domain.{ Owner, StockCompany, Organization }
import Owner._
import me.kerfume.stocksim.domain.core._

class OwnerUsecase(
  repo: Repository,
  oRepo: Organization.Repository,
  scRepo: StockCompany.Repository,
  presenter: Presenter
) {
  def buyOrderStock(ownerID: Long, orgID: Long, scompID: Long, stockCount: Int) = {
    val registed = for {
      owner <- repo.find(ownerID)
      org <- oRepo.find(orgID)
      _ <- guard(org.checkStockUnit(stockCount), "impossible buy stockCount.")
      scomp <- scRepo.find(scompID)
      order <- StockCompany.BuyOrder.create(scomp, owner, org, stockCount)
      _ <- scRepo.registBuyOrder(order)
    } yield order

    present(presenter, registed) { case (order, p) =>
      p.registedBuyOrder(order)
    }
  }

  // buyOrderとほぼ同じ
  def sellOrderSocket() = ???
}
