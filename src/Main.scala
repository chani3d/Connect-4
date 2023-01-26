import Input.readInt
import hevs.graphics.FunGraphics


import java.awt.event.{MouseAdapter, MouseEvent}



object Main extends App {

  def menu(board: Array[Array[Int]], turn: Int, player: String): Unit = {
    println("           Connect 4          ")
    println(s"    Turn n°: $turn, $player\n")
    println(" +---+---+---+---+---+---+---+")
    println(board.map(_.mkString(" | ", " | ", " | ")).mkString("\n +---+---+---+---+---+---+---+ \n"))
    println(" +---+---+---+---+---+---+---+")
  }

  def checkPos(board: Array[Array[Int]], column: Int): Boolean = {
    return board(0)(column) == 0
  }

  def nextPos(board: Array[Array[Int]], column: Int): Int = {
    var a: Int = 0
    for (i <- board.indices) {
      if (board(i)(column) == 0) {
        a = i
      }
    }
    return a
  }

  def dropPiece(board: Array[Array[Int]], row: Int, column: Int, piece: Int): Unit = {
    board(row)(column) = piece
  }

  def checkWin(board: Array[Array[Int]], piece: Int, r: Int, c: Int): Boolean = {
    var check: Boolean = false

    //Diagonal check (going down)
    for (i <- 0 until r - 3) {
      for (j <- 0 until c - 3) {
        if (board(i)(j) == piece && board(i + 1)(j + 1) == piece && board(i + 2)(j + 2) == piece && board(i + 3)(j + 3)
          == piece) {
          check = true
        }
      }
    }

    //Diagonal check (going up)
    for (i <- 3 until r) {
      for (j <- 0 until c - 3) {
        if (board(i)(j) == piece && board(i - 1)(j + 1) == piece && board(i - 2)(j + 2) == piece && board(i - 3)(j + 3)
          == piece) {
          check = true
        }
      }
    }

    //Horizontal check
    for (i <- 0 until r) {
      for (j <- 0 until c - 3) {
        if (board(i)(j) == piece && board(i)(j + 1) == piece && board(i)(j + 2) == piece && board(i)(j + 3) == piece) {
          check = true
        }
      }
    }

    //Vertical check
    for(i <- 0 until r - 3) {
      for (j <- 0 until c) {
        if (board(i)(j) == piece && board(i + 1)(j) == piece && board(i + 2)(j) == piece && board(i + 3)(j) == piece) {
          check = true
        }
      }
    }
    return check
  }

  def checkPosx(posx: Int, newposx: Int): Unit =  {

  }

  val w = new FunGraphics(700, 700, "Connect 4, 2023 By SJCG & DR - ISC1")
  val r: Int = 6
  val c: Int = 7
  val board: Array[Array[Int]] = new Graphics createBoard(r, c, w)
  var gameOver: Boolean = false
  var turn: Int = 0
  var column: Int = 0
  var columnTemp: Int = 1
  var row: Int = 0
  var player: String = "Player 1"
  var posx = 0
  var posy = 0

  //Mouse stuff

  w.addMouseListener(new MouseAdapter() {
    override def mouseClicked(e: MouseEvent): Unit = {
      val event: MouseEvent = e

      // Get the mouse position from the event
      posx = event.getX
      posy = event.getY
    }
  })


  menu(board, turn, player)

  while (!gameOver) {

    if (turn == 0 || turn % 2 == 0) {
      //to play in the command line
      //column = readInt()
      column = readInt()

      if (checkPos(board, column)) {
        row = nextPos(board, column)
        dropPiece(board, row, column, 1)
        player = "Player 2"

        if (checkWin(board, 1, r, c)) {
          player = "Player 1"
          println(s"$player Wins !")
          gameOver = true
        }
      }
    }
    else {
      //to play in the command line
      column = readInt()


      if (checkPos(board, column)) {
        row = nextPos(board, column)
        dropPiece(board, row, column, 2)
        player = "Player 1"

        if (checkWin(board, 2, r, c)) {
          player = "Player 2"
          println(s"$player Wins !")
          gameOver = true
        }
      }
    }
    if (!gameOver) {
      turn += 1
      menu(board, turn, player)
    }
  }


}


