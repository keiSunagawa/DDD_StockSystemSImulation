package me.kerfume.stocksim.domain

object core {
  type Result[A] = Either[String, A]
  def guard(cond: => Boolean, left: String): Result[Unit] = Either.cond(cond, (), left)
  def present[V, Pre <: PresenterCore](presenter: Pre, res: Result[V])(f: (V, Pre) => Unit): Unit = res match {
    case Right(v) => f(v, presenter)
    case Left(err) => presenter.failUsecase(err)
  }

  trait RepositoryCore[Domain] {
    def find(id: Long): Result[Domain]
  }
  trait PresenterCore {
    def failUsecase(err: String): Unit
  }
}
