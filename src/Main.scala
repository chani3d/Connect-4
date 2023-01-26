import Input.readInt
import hevs.graphics.FunGraphics
import java.awt.Color
import java.awt.event.{KeyAdapter, KeyEvent}

object Main extends App {

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

  val r: Int = 6
  val c: Int = 7
  var gameOver: Boolean = false
  var turn: Int = 0
  var row: Int = 0
  var column: Int = 0
  var player: String = "Player 1"
  var ok: Boolean = false
  var console_OR_graphics_mode: Boolean = false

  // Change console_OR_graphics_mode to true if you want to play in console mode

  if (console_OR_graphics_mode)

  //Console mode

  {
    val board: Array[Array[Int]] = new Console_mode createBoard(r, c)
    new Console_mode menu(board, turn, player)

    while (!gameOver) {

      var temp: Int = 0

      if (turn % 2 == 0) {
        temp = readInt() - 1
        while (temp < 0 || temp > 6) {
          println(s"Not a valid number, try again $player !")
          temp = readInt() - 1
        }
        column = temp

        if (checkPos(board, column)) {
          row = nextPos(board, column)
          new Console_mode dropPiece(board, row, column, 1)
          player = "Player 2"

          if (checkWin(board, 1, r, c)) {
            player = "Player 1"
            println(s"$player wins !")
            gameOver = true
          }
        }
      }

      else {
        temp = readInt() - 1

        while (temp < 0 || temp > 6) {
          println(s"Not a valid number, try again $player !")
          temp = readInt() - 1
        }
        column = temp
        if (checkPos(board, column)) {
          row = nextPos(board, column)
          new Console_mode dropPiece(board, row, column, 2)
          player = "Player 1"

          if (checkWin(board, 2, r, c)) {
            player = "Player 2"
            println(s"$player wins !")
            gameOver = true
          }
        }
      }
      if (!gameOver) {
        turn += 1
        new Console_mode menu(board, turn, player)
      }
    }
  }

  else

  //GUI mode

  {
    val w = new FunGraphics(700, 700, "Connect 4, 2023 By SJCG - ISC1")
    var board: Array[Array[Int]]  = Array.empty
    // boardStatus determines whether the array of the board is empty or not
    var boardStatus: Boolean = false
    var started: Boolean = false
    new GUI_mode textTitle(w, player)
    new GUI_mode textPressStart(w, 150, 400)


    //Keyboard control

    w.setKeyManager(new KeyAdapter() {
      override def keyPressed(e: KeyEvent): Unit = {
        ok = true
        if (e.getKeyChar == '1' && boardStatus) column = 0
        else if (e.getKeyChar == '2' && boardStatus) column = 1
        else if (e.getKeyChar == '3' && boardStatus) column = 2
        else if (e.getKeyChar == '4' && boardStatus) column = 3
        else if (e.getKeyChar == '5' && boardStatus) column = 4
        else if (e.getKeyChar == '6' && boardStatus) column = 5
        else if (e.getKeyChar == '7' && boardStatus) column = 6
        else if (e.getKeyChar == 's' && !started) {
          started = true
          gameOver = false
          board = new GUI_mode createBoard(r, c, w)
          boardStatus = true
          new GUI_mode textPlayer(w, player)
          ok = false
        }
        else if (e.getKeyChar == 'r' && started) {
          turn = 0
          gameOver = false
          player = "Player 1"
          board = new GUI_mode createBoard(r, c, w)
          boardStatus = true
          new GUI_mode textPlayer(w, player)
          ok = false
        }

        else ok = false

        //Game start

        /*

        If "while" is used AND is outside the "w.setKeyManager": write "Thread.sleep(10)" right after.
        According to Mr. Mudry, since "while" is constantly turning, the CPU isn't fast enough to go back and read
        the "keyManager".

        */


        if (ok && !gameOver) {
          if (turn % 2 == 0) {
            ok = false

            //Player 1's turn

            if (checkPos(board, column)) {
              row = nextPos(board, column)
              new GUI_mode dropPiece(board, row, column, 1, (column * 100) + 5, (row * 100) + 105, Color.blue, w)
              player = "Player 2"
              new GUI_mode textPlayer(w, player)
              new GUI_mode textTurn (w, turn)
              new GUI_mode textPressRestart(w)


              if (checkWin(board, 1, r, c)) {
                player = "Player 1"
                new GUI_mode textWin(w, s"$player wins !")
                gameOver = true
              }
            }
          }
          else if (turn == 41){
            gameOver = true
            new GUI_mode textTie(w)

          }

          else {
            //Player 2's turn

            ok = false

            if (checkPos(board, column)) {
              row = nextPos(board, column)
              new GUI_mode dropPiece(board, row, column, 2, (column * 100) + 5, (row * 100) + 105, Color.red, w)
              player = "Player 1"
              new GUI_mode textPlayer(w, player)
              new GUI_mode textTurn (w, turn)


              if (checkWin(board, 2, r, c)) {
                player = "Player 2"
                new GUI_mode textWin(w, s"$player wins !")
                gameOver = true
              }
            }
          }

          if (!gameOver) {
            turn += 1

            /*
            To debug (check if the array is being filled) : new Console_mode menu(board, turn, player)
             */

          }
        }
      }
    })
  }
}