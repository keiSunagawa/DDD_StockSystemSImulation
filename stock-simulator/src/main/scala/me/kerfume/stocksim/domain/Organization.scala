package me.kerfume.stocksim.domain

import core._

case class Organization(
  ID: Long,
  name: String,
  // 株購入の単元
  stockUnit: Int
) {
  // 株式を公開できるかどうか
  def canReleaseStock: Boolean = ???
  // 配当金を計算する
  def dividendAmount: Long = ???
  // 単元 * nの場合にのみ購入可能
  def checkStockUnit(count: Int): Boolean = count % stockUnit == 0
}

object Organization {
  trait Repository extends RepositoryCore[Organization]
  trait Presenter extends PresenterCore {
    def releasedStock(org: Organization, market: Market): Unit
  }
}
