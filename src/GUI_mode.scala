import hevs.graphics.FunGraphics
import hevs.graphics.utils.GraphicsBitmap

import java.awt.Color

class GUI_mode {

  def createBoard(r: Int, c: Int, w: FunGraphics): Array[Array[Int]] = {
   //Visual stuff
    val board: Array[Array[Int]] = Array.ofDim[Int](r, c)
    val notTooBrightYellow: Color = new Color(230,230,0)

    w.setColor(Color.lightGray)
    w.drawFillRect(0, 0, 700, 700)

    w.setColor(notTooBrightYellow)
    w.drawFillRect(0, 100, 700, 600)
    for (i <- 0 to c) {
      for (j <- 0 to r) {
        w.setColor(Color.lightGray)
        w.drawFilledCircle((i*100)+5, (j*100)+105, 90)
      }
    }
    return board
  }

  def dropPiece(board: Array[Array[Int]], row: Int, column: Int, piece: Int, posx: Int, posy: Int, color: Color, w: FunGraphics): Unit = {
    w.setColor(color)
    w.drawFilledCircle(posx, posy, 90)
    board(row)(column) = piece
  }

  //Text functions

  def textTitle(w: FunGraphics, player: String): Unit = {
    val hei_defr = new GraphicsBitmap("/res/images/hei-defr.png")
    val isc_icon = new GraphicsBitmap("/res/images/isc_icon.png")

    w.setColor(Color.lightGray)
    w.drawFillRect(0, 0, 700, 700)
    w.drawFancyString(73, 325, "Connect 4", Color.gray, 110)
    w.drawFancyString(70, 330, "Connect 4", Color.black, 110)
    w.drawFancyString(170, 600, "2023 by S.J. Cruz Go", Color.black, 30)
    //w.drawFancyString(110, 670, "ISC 1 - HES-SO Valais-Wallis", Color.black, 30)

    w.drawTransformedPicture(200, 650, 0, 0.7, isc_icon)
    w.drawTransformedPicture(450, 650, 0, 0.7, hei_defr)
  }

  def textPressStart(w: FunGraphics, posx: Int, posy: Int): Unit = {
    w.drawFancyString(posx + 3, posy - 5, "Press > s < to start", Color.gray, 40)
    w.drawFancyString(posx, posy, "Press > s < to start", Color.black, 40)
  }

  def textPlayer(w: FunGraphics, text: String): Unit = {
    w.setColor(Color.lightGray)
    w.drawFillRect(250, 0, 210, 65)
    w.drawFancyString(250, 50, text, Color.black, 50)
  }

  def textTurn(w: FunGraphics, turn: Int): Unit = {
    w.setColor(Color.lightGray)
    w.drawFillRect(10, 15, 160, 40)
    w.drawFancyString(10, 40, s"Turn : $turn" , Color.black, 30)
  }

  def textWin(w: FunGraphics, text: String): Unit = {
    w.setColor(Color.lightGray)
    w.drawFillRect(0, 0, 700, 100)
    w.drawFancyString(175, 50, text, Color.black, 50)
    w.drawFancyString(110, 90, "Press > r < to restart the game", Color.black, 30)
  }

  def textPressRestart(w: FunGraphics): Unit = {
    w.setColor(Color.lightGray)
    w.drawFillRect(10, 55, 310, 40)
    w.drawFancyString(10, 80, "Press > r < to restart the game", Color.black, 20)
  }

  def textTie(w: FunGraphics): Unit = {
    w.setColor(Color.lightGray)
    w.drawFillRect(0, 0, 700, 100)
    w.drawFancyString(300, 50, "Tie", Color.black, 50)
    w.drawFancyString(110, 90, "Press > r < to restart the game", Color.black, 30)

  }
}



