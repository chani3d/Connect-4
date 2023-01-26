class Console_mode {

  def menu(board: Array[Array[Int]], turn: Int, player: String): Unit = {
    println("           Connect 4          ")
    println(s"    Turn n°: $turn, $player\n")
    println(" +---+---+---+---+---+---+---+")
    println(board.map(_.mkString(" | ", " | ", " | ")).mkString("\n +---+---+---+---+---+---+---+ \n"))
    println(" +---+---+---+---+---+---+---+")
  }

  def createBoard(r: Int, c: Int): Array[Array[Int]] = {
    val board: Array[Array[Int]] = Array.ofDim[Int](r, c)
    return board
  }

  def dropPiece(board: Array[Array[Int]], row: Int, column: Int, piece: Int): Unit = {
    board(row)(column) = piece
  }
}