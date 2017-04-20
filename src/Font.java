import org.lwjgl.opengl.GL11;
import static org.lwjgl.opengl.ARBTextureRectangle.GL_TEXTURE_RECTANGLE_ARB;

public class Font {
	
	private static int fontset;
	
	public static void init(){
		fontset = TextureLoader.glLoadLinearPNG("img/fontset_w_thick.png");
	}

public static void drawString(String s, float x, float y, float scale, float width) {
	
	GL11.glEnable(GL_TEXTURE_RECTANGLE_ARB);
	
	GL11.glBindTexture(GL_TEXTURE_RECTANGLE_ARB, fontset);
	
      float startX = x;
      scale = scale * 0.25f;
      GL11.glColor3f(1.f, 1.f, 1.f);
      for (char c : s.toLowerCase().toCharArray()) {
    	  GL11.glBegin(GL11.GL_QUADS);
          switch(c){
          case ' ':
              x += scale*20;
              break;
          case '\n':
              y -= scale*75;
              x = startX;
              break;
          case 'a':
        	  GL11.glTexCoord2f(5,50);
              GL11.glVertex2f(x, y);
              GL11.glTexCoord2f(5,0);
              GL11.glVertex2f(x, y + scale*50);
              GL11.glTexCoord2f(45,0);
              GL11.glVertex2f(x + scale*40, y + scale*50);
              GL11.glTexCoord2f(45,50);
              GL11.glVertex2f(x + scale*40, y);
              x += scale*40; 
              break;
          case 'b':
        	  GL11.glTexCoord2f(45,50);
              GL11.glVertex2f(x, y);
              GL11.glTexCoord2f(45,0);
              GL11.glVertex2f(x, y + scale*50);
              GL11.glTexCoord2f(75,0);
              GL11.glVertex2f(x + scale*30, y + scale*50);
              GL11.glTexCoord2f(75,50);
              GL11.glVertex2f(x + scale*30, y);
              x += scale*30;
              break;
          case 'c':
        	  GL11.glTexCoord2f(80,50);
              GL11.glVertex2f(x, y);
              GL11.glTexCoord2f(80,0);
              GL11.glVertex2f(x, y + scale*50);
              GL11.glTexCoord2f(115,0);
              GL11.glVertex2f(x + scale*35, y + scale*50);
              GL11.glTexCoord2f(115,50);
              GL11.glVertex2f(x + scale*35, y);
              x += scale*35;
              break;
          case 'd':
        	  GL11.glTexCoord2f(115,50);
              GL11.glVertex2f(x, y);
              GL11.glTexCoord2f(115,0);
              GL11.glVertex2f(x, y + scale*50);
              GL11.glTexCoord2f(155,0);
              GL11.glVertex2f(x + scale*40, y + scale*50);
              GL11.glTexCoord2f(155,50);
              GL11.glVertex2f(x + scale*40, y);
              x += scale*40;
              break;

          case 'e':
        	  GL11.glTexCoord2f(155,50);
              GL11.glVertex2f(x, y);
              GL11.glTexCoord2f(155,0);
              GL11.glVertex2f(x, y + scale*50);
              GL11.glTexCoord2f(185,0);
              GL11.glVertex2f(x + scale*30, y + scale*50);
              GL11.glTexCoord2f(185,50);
              GL11.glVertex2f(x + scale*30, y);
              x += scale*30;
              break;
          case 'f':
        	  GL11.glTexCoord2f(185,50);
              GL11.glVertex2f(x, y);
              GL11.glTexCoord2f(185,0);
              GL11.glVertex2f(x, y + scale*50);
              GL11.glTexCoord2f(215,0);
              GL11.glVertex2f(x + scale*30, y + scale*50);
              GL11.glTexCoord2f(215,50);
              GL11.glVertex2f(x + scale*30, y);
              x += scale*30;
              break;
          case 'g':
        	  GL11.glTexCoord2f(255,50);
              GL11.glVertex2f(x, y);
              GL11.glTexCoord2f(255,0);
              GL11.glVertex2f(x, y + scale*50);
              GL11.glTexCoord2f(300,0);
              GL11.glVertex2f(x + scale*45, y + scale*50);
              GL11.glTexCoord2f(300,50);
              GL11.glVertex2f(x + scale*45, y);
              x += scale*45;
              break;
          case 'h':
        	  GL11.glTexCoord2f(300,50);
              GL11.glVertex2f(x, y);
              GL11.glTexCoord2f(300,0);
              GL11.glVertex2f(x, y + scale*50);
              GL11.glTexCoord2f(340,0);
              GL11.glVertex2f(x + scale*40, y + scale*50);
              GL11.glTexCoord2f(340,50);
              GL11.glVertex2f(x + scale*40, y);
              x += scale*40;
              break;
          case 'i':
        	  GL11.glTexCoord2f(335,50);
              GL11.glVertex2f(x, y);
              GL11.glTexCoord2f(335,0);
              GL11.glVertex2f(x, y + scale*50);
              GL11.glTexCoord2f(350,0);
              GL11.glVertex2f(x + scale*15, y + scale*50);
              GL11.glTexCoord2f(350,50);
              GL11.glVertex2f(x + scale*15, y);
              x += scale*15;
              break;
          case 'j':
        	  GL11.glTexCoord2f(350,50);
              GL11.glVertex2f(x, y);
              GL11.glTexCoord2f(350,0);
              GL11.glVertex2f(x, y + scale*50);
              GL11.glTexCoord2f(380,0);
              GL11.glVertex2f(x + scale*30, y + scale*50);
              GL11.glTexCoord2f(380,50);
              GL11.glVertex2f(x + scale*30, y);
              x += scale*30;
              break;
          case 'k':
        	  GL11.glTexCoord2f(380-5,50);
              GL11.glVertex2f(x, y);
              GL11.glTexCoord2f(380-5,0);
              GL11.glVertex2f(x, y + scale*50);
              GL11.glTexCoord2f(410,0);
              GL11.glVertex2f(x + scale*35, y + scale*50);
              GL11.glTexCoord2f(410,50);
              GL11.glVertex2f(x + scale*35, y);
              x += scale*35;
              break;
          case 'l':
        	  GL11.glTexCoord2f(410,50);
              GL11.glVertex2f(x, y);
              GL11.glTexCoord2f(410,0);
              GL11.glVertex2f(x, y + scale*50);
              GL11.glTexCoord2f(440,0);
              GL11.glVertex2f(x + scale*30, y + scale*50);
              GL11.glTexCoord2f(440,50);
              GL11.glVertex2f(x + scale*30, y);
              x += scale*30;
              break;
          case 'm':
        	  GL11.glTexCoord2f(440,50);
              GL11.glVertex2f(x, y);
              GL11.glTexCoord2f(440,0);
              GL11.glVertex2f(x, y + scale*50);
              GL11.glTexCoord2f(480,0);
              GL11.glVertex2f(x + scale*40, y + scale*50);
              GL11.glTexCoord2f(480,50);
              GL11.glVertex2f(x + scale*40, y);
              x += scale*40;
              break;
          case 'n':
        	  GL11.glTexCoord2f(480,50);
              GL11.glVertex2f(x, y);
              GL11.glTexCoord2f(480,0);
              GL11.glVertex2f(x, y + scale*50);
              GL11.glTexCoord2f(520,0);
              GL11.glVertex2f(x + scale*40, y + scale*50);
              GL11.glTexCoord2f(520,50);
              GL11.glVertex2f(x + scale*40, y);
              x += scale*40;
              break;
          case 'o':
        	  GL11.glTexCoord2f(520,50);
              GL11.glVertex2f(x, y);
              GL11.glTexCoord2f(520,0);
              GL11.glVertex2f(x, y + scale*50);
              GL11.glTexCoord2f(565,0);
              GL11.glVertex2f(x + scale*45, y + scale*50);
              GL11.glTexCoord2f(565,50);
              GL11.glVertex2f(x + scale*45, y);
              x += scale*45;
              break;
          case 'p':
        	  GL11.glTexCoord2f(565,50);
              GL11.glVertex2f(x, y);
              GL11.glTexCoord2f(565,0);
              GL11.glVertex2f(x, y + scale*50);
              GL11.glTexCoord2f(600,0);
              GL11.glVertex2f(x + scale*35, y + scale*50);
              GL11.glTexCoord2f(600,50);
              GL11.glVertex2f(x + scale*35, y);
              x += scale*35;
              break;
          case 'q':
        	  GL11.glTexCoord2f(600,50);
              GL11.glVertex2f(x, y);
              GL11.glTexCoord2f(600,0);
              GL11.glVertex2f(x, y + scale*50);
              GL11.glTexCoord2f(640,0);
              GL11.glVertex2f(x + scale*40, y + scale*50);
              GL11.glTexCoord2f(640,50);
              GL11.glVertex2f(x + scale*40, y);
              x+=scale*40;
              break;
          case 'r':
        	  GL11.glTexCoord2f(640,50);
              GL11.glVertex2f(x, y);
              GL11.glTexCoord2f(640,0);
              GL11.glVertex2f(x, y + scale*50);
              GL11.glTexCoord2f(675,0);
              GL11.glVertex2f(x + scale*35, y + scale*50);
              GL11.glTexCoord2f(675,50);
              GL11.glVertex2f(x + scale*35, y);
              x+=scale*35;
              break;
          case 's':
        	  GL11.glTexCoord2f(675,50);
              GL11.glVertex2f(x, y);
              GL11.glTexCoord2f(675,0);
              GL11.glVertex2f(x, y + scale*50);
              GL11.glTexCoord2f(705,0);
              GL11.glVertex2f(x + scale*30, y + scale*50);
              GL11.glTexCoord2f(705,50);
              GL11.glVertex2f(x + scale*30, y);
              x+=scale*30;
              break;
          case 't':
        	  GL11.glTexCoord2f(705,50);
              GL11.glVertex2f(x, y);
              GL11.glTexCoord2f(705,0);
              GL11.glVertex2f(x, y + scale*50);
              GL11.glTexCoord2f(740,0);
              GL11.glVertex2f(x + scale*35, y + scale*50);
              GL11.glTexCoord2f(740,50);
              GL11.glVertex2f(x + scale*35, y);
              x+=scale*35;
              break;
          case 'u':
        	  GL11.glTexCoord2f(740,50);
              GL11.glVertex2f(x, y);
              GL11.glTexCoord2f(740,0);
              GL11.glVertex2f(x, y + scale*50);
              GL11.glTexCoord2f(775,0);
              GL11.glVertex2f(x + scale*35, y + scale*50);
              GL11.glTexCoord2f(775,50);
              GL11.glVertex2f(x + scale*35, y);
              x+=scale*35;
              break;
          case 'v':
        	  GL11.glTexCoord2f(775,50);
              GL11.glVertex2f(x, y);
              GL11.glTexCoord2f(775,0);
              GL11.glVertex2f(x, y + scale*50);
              GL11.glTexCoord2f(815,0);
              GL11.glVertex2f(x + scale*40, y + scale*50);
              GL11.glTexCoord2f(815,50);
              GL11.glVertex2f(x + scale*40, y);
              x+=scale*40;
              break;
          case 'w':
        	  GL11.glTexCoord2f(815,50);
              GL11.glVertex2f(x, y);
              GL11.glTexCoord2f(815,0);
              GL11.glVertex2f(x, y + scale*50);
              GL11.glTexCoord2f(865,0);
              GL11.glVertex2f(x + scale*50, y + scale*50);
              GL11.glTexCoord2f(865,50);
              GL11.glVertex2f(x + scale*50, y);
              x+=scale*50;
              break;
          case 'x':
        	  GL11.glTexCoord2f(865,50);
              GL11.glVertex2f(x, y);
              GL11.glTexCoord2f(865,0);
              GL11.glVertex2f(x, y + scale*50);
              GL11.glTexCoord2f(900,0);
              GL11.glVertex2f(x + scale*35, y + scale*50);
              GL11.glTexCoord2f(900,50);
              GL11.glVertex2f(x + scale*35, y);
              x+=scale*35;
              break;
          case 'y':
        	  GL11.glTexCoord2f(900,50);
              GL11.glVertex2f(x, y);
              GL11.glTexCoord2f(900,0);
              GL11.glVertex2f(x, y + scale*50);
              GL11.glTexCoord2f(935,0);
              GL11.glVertex2f(x + scale*35, y + scale*50);
              GL11.glTexCoord2f(935,50);
              GL11.glVertex2f(x + scale*35, y);
              x+=scale*35;
              break;
          case 'z':
        	  GL11.glTexCoord2f(935,50);
              GL11.glVertex2f(x, y);
              GL11.glTexCoord2f(935,0);
              GL11.glVertex2f(x, y + scale*50);
              GL11.glTexCoord2f(970,0);
              GL11.glVertex2f(x + scale*35, y + scale*50);
              GL11.glTexCoord2f(970,50);
              GL11.glVertex2f(x + scale*35, y);
              x+=scale*35;
              break;
          case '1':
        	  GL11.glTexCoord2f(230,935);
              GL11.glVertex2f(x, y);
              GL11.glTexCoord2f(230,885);
              GL11.glVertex2f(x, y + scale*50);
              GL11.glTexCoord2f(250,885);
              GL11.glVertex2f(x + scale*20, y + scale*50);
              GL11.glTexCoord2f(250,935);
              GL11.glVertex2f(x + scale*20, y);
              x+=scale*20;
              break;
          case '2':
        	  GL11.glTexCoord2f(250,935);
              GL11.glVertex2f(x, y);
              GL11.glTexCoord2f(250,885);
              GL11.glVertex2f(x, y + scale*50);
              GL11.glTexCoord2f(280,885);
              GL11.glVertex2f(x + scale*30, y + scale*50);
              GL11.glTexCoord2f(280,935);
              GL11.glVertex2f(x + scale*30, y);
              x+=scale*30;
              break;
          case '3':
        	  GL11.glTexCoord2f(280,935);
              GL11.glVertex2f(x, y);
              GL11.glTexCoord2f(280,885);
              GL11.glVertex2f(x, y + scale*50);
              GL11.glTexCoord2f(315,885);
              GL11.glVertex2f(x + scale*35, y + scale*50);
              GL11.glTexCoord2f(315,935);
              GL11.glVertex2f(x + scale*35, y);
              x+=scale*35;
              break;
          case '4':
        	  GL11.glTexCoord2f(315,935);
              GL11.glVertex2f(x, y);
              GL11.glTexCoord2f(315,885);
              GL11.glVertex2f(x, y + scale*50);
              GL11.glTexCoord2f(345,885);
              GL11.glVertex2f(x + scale*30, y + scale*50);
              GL11.glTexCoord2f(345,935);
              GL11.glVertex2f(x + scale*30, y);
              x+=scale*30;
              break;
          case '5':
        	  GL11.glTexCoord2f(345,935);
              GL11.glVertex2f(x, y);
              GL11.glTexCoord2f(345,885);
              GL11.glVertex2f(x, y + scale*50);
              GL11.glTexCoord2f(380,885);
              GL11.glVertex2f(x + scale*35, y + scale*50);
              GL11.glTexCoord2f(380,935);
              GL11.glVertex2f(x + scale*35, y);
              x+=scale*35;
              break;
          case '6':
        	  GL11.glTexCoord2f(380,935);
              GL11.glVertex2f(x, y);
              GL11.glTexCoord2f(380,885);
              GL11.glVertex2f(x, y + scale*50);
              GL11.glTexCoord2f(410,885);
              GL11.glVertex2f(x + scale*30, y + scale*50);
              GL11.glTexCoord2f(410,935);
              GL11.glVertex2f(x + scale*30, y);
              x+=scale*30;
              break;
          case '7':
        	  GL11.glTexCoord2f(410,935);
              GL11.glVertex2f(x, y);
              GL11.glTexCoord2f(410,885);
              GL11.glVertex2f(x, y + scale*50);
              GL11.glTexCoord2f(440,885);
              GL11.glVertex2f(x + scale*30, y + scale*50);
              GL11.glTexCoord2f(440,935);
              GL11.glVertex2f(x + scale*30, y);
              x+=scale*30;
              break;
          case '8':
        	  GL11.glTexCoord2f(440,935);
              GL11.glVertex2f(x, y);
              GL11.glTexCoord2f(440,885);
              GL11.glVertex2f(x, y + scale*50);
              GL11.glTexCoord2f(470,885);
              GL11.glVertex2f(x + scale*30, y + scale*50);
              GL11.glTexCoord2f(470,935);
              GL11.glVertex2f(x + scale*30, y);
              x+=scale*30;
              break;
          case '9':
        	  GL11.glTexCoord2f(470,935);
              GL11.glVertex2f(x, y);
              GL11.glTexCoord2f(470,885);
              GL11.glVertex2f(x, y + scale*50);
              GL11.glTexCoord2f(505,885);
              GL11.glVertex2f(x + scale*35, y + scale*50);
              GL11.glTexCoord2f(505,935);
              GL11.glVertex2f(x + scale*35, y);
              x+=scale*35;
              break;
          case '0':
        	  GL11.glTexCoord2f(505,935);
              GL11.glVertex2f(x, y);
              GL11.glTexCoord2f(505,885);
              GL11.glVertex2f(x, y + scale*50);
              GL11.glTexCoord2f(535,885);
              GL11.glVertex2f(x + scale*30, y + scale*50);
              GL11.glTexCoord2f(535,935);
              GL11.glVertex2f(x + scale*30, y);
              x+=scale*30;
              break;
          case ':':
        	  GL11.glTexCoord2f(475,1145-5);
              GL11.glVertex2f(x, y);
              GL11.glTexCoord2f(475,1095-5);
              GL11.glVertex2f(x, y + scale*50);
              GL11.glTexCoord2f(485,1095-5);
              GL11.glVertex2f(x + scale*10, y + scale*50);
              GL11.glTexCoord2f(485,1145-5);
              GL11.glVertex2f(x + scale*10, y);
              x+=scale*20;
              break;
          case '-':
        	  GL11.glTexCoord2f(365,1210);
              GL11.glVertex2f(x, y);
              GL11.glTexCoord2f(365,1160);
              GL11.glVertex2f(x, y + scale*50);
              GL11.glTexCoord2f(380,1160);
              GL11.glVertex2f(x + scale*20, y + scale*50);
              GL11.glTexCoord2f(380,1210);
              GL11.glVertex2f(x + scale*20, y);
              x+=scale*20;
              break;
          case '.':
        	  GL11.glTexCoord2f(450,1145-5);
              GL11.glVertex2f(x, y);
              GL11.glTexCoord2f(450,1095-5);
              GL11.glVertex2f(x, y + scale*50);
              GL11.glTexCoord2f(460,1095-5);
              GL11.glVertex2f(x + scale*10, y + scale*50);
              GL11.glTexCoord2f(460,1145-5);
              GL11.glVertex2f(x + scale*10, y);
              x+=scale*20;
              break;
          }
          GL11.glEnd();
      }
      
      //GL11.glDisable(GL11.GL_LINE_WIDTH);
      
      GL11.glBindTexture(GL_TEXTURE_RECTANGLE_ARB, 0);
	    
	  GL11.glDisable(GL_TEXTURE_RECTANGLE_ARB);
   }

}