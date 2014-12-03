package kavvase.spn.core

import scala.util.Random
import scalaz.Monad

sealed trait RandomVariable[A] {

  def get: A

}

case object UniformRandomVariable extends RandomVariable[Double] {

  def get: Double = RandomVariable.rand.nextDouble()

}

object RandomVariable {

  val rand: Random = new Random

  implicit object RandomVariableMonad extends Monad[RandomVariable] {

    def point[A](a: => A): RandomVariable[A] = new RandomVariable[A] {
      def get: A = a
    }

    def bind[A, B](fa: RandomVariable[A])(f: (A) => RandomVariable[B]): RandomVariable[B] = new RandomVariable[B] {
      def get: B = f(fa.get).get
    }

  }

}
