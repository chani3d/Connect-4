import hevs.graphics.FunGraphics
import java.awt.Color


class Graphics {

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






}



