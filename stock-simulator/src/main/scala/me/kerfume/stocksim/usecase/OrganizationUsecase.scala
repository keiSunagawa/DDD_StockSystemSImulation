package me.kerfume.stocksim.usecase

import me.kerfume.stocksim.domain.{ Organization, Market }
import Organization._
import me.kerfume.stocksim.domain.core._

class OrganizationUsecase(
  repo: Repository,
  mRepo: Market.Repository,
  presenter: Presenter
) {
  // use by outer system
  def releaseStock(orgID: Long, stockCount: Int, marketID: Option[Long]): Unit = {
    val released = for {
      org <- repo.find(orgID)
      _ <- guard(org.canReleaseStock, s"${org.name} is cant release stock.")
      market <- marketID match {
        case Some(id) => mRepo.find(id)
        case None => Right(mRepo.defaultMarket())
      }
      _ <- market.publishStock(org, stockCount)
    } yield (org, market)

    present(presenter, released) { case ((org, market), p) =>
      p.releasedStock(org, market)
    }
  }

  // 配当金配布、システム外なので後回し
  def distDividend(): Unit = ???
}
