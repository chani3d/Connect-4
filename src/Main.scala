import hevs.graphics.FunGraphics
import java.awt.Color
import java.awt.event.{KeyAdapter, KeyEvent}
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



  val w = new FunGraphics(700, 700, "Connect 4, 2023 By SJCG & DR - ISC1")
  val r: Int = 6
  val c: Int = 7
  val board: Array[Array[Int]] = new Graphics createBoard(r, c, w)
  var gameOver: Boolean = false
  var turn: Int = 0
  var column: Int = 0
  var row: Int = 0
  var player: String = "Player 1"
  var ok: Boolean = false


  //Mouse control
  w.addMouseListener(new MouseAdapter() {
    override def mouseClicked(e: MouseEvent): Unit = {
      val event = e
      ok = true
      column = (event.getX)/100
      print(column)
    }
    })

  //Keyboard control
  w.setKeyManager(new KeyAdapter() {
    override def keyPressed(e: KeyEvent): Unit = {
      ok = true
      if (e.getKeyChar == '1') column = 0
      else if (e.getKeyChar == '2') column = 1
      else if (e.getKeyChar == '3') column = 2
      else if (e.getKeyChar == '4') column = 3
      else if (e.getKeyChar == '5') column = 4
      else if (e.getKeyChar == '6') column = 5
      else if (e.getKeyChar == '7') column = 6
     else ok = false
    }
  })




  menu(board, turn, player)

  //STOP WHILE!!
  while (!gameOver) {

    // LE PROF nous a expliqué que c'était...
    // FIXME
    Thread.sleep(10)

    if (ok) {


      if (turn % 2 == 0) {
        ok = false
        w.drawFancyString(250, 50, "Player 1", Color.black, 50)


        if (checkPos(board, column)) {
          row = nextPos(board, column)




          w.setColor(Color.blue)
          w.drawFilledCircle((column * 100) + 5, (row * 100) + 105, 90)




          dropPiece(board, row, column, 1)

          println(checkPos(board, column))
          print(row)


          player = "Player 2"

          if (checkWin(board, 1, r, c)) {
            player = "Player 1"
            println(s"$player Wins !")
            gameOver = true
          }
        }
      }

      else {

        ok = false
        if (checkPos(board, column)) {
          row = nextPos(board, column)
          w.setColor(Color.red)
          w.drawFilledCircle((column * 100) + 5, (row * 100) + 105, 90)



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


}


