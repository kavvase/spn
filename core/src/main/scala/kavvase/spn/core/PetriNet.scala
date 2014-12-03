package kavvase.spn.core

trait PetriNet[P, T, I[_, _], M[_]] {

  def places: Set[P]

  def transitions: Set[T]

  def backward: I[P, T]

  def forward: I[P, T]

  def marking: M[P]

}
