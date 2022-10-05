package uet.oop.bomberman.entities;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
public class Config {
   public int width;
   public int height;

   public char config[][] = new char[100][100];
   public int l;

   public void buildConfig ()
   {
      try{
         File myfile = new File(
                 "src\\uet\\oop\\bomberman\\entities\\fileConfig.txt"
         );
         Scanner myReader = new Scanner(myfile);
         this.l = myReader.nextInt();
         this.height = myReader.nextInt();
         this.width = myReader.nextInt();

         String ignore = myReader.nextLine();
         for(int i = 0; i < height; ++i)
         {
            String str = myReader.nextLine();
            for(int j = 0; j < width; ++j)
            {
               config[i][j] = str.charAt(j);
            }
         }

         System.out.println(height + " " + width);
      }

      catch (FileNotFoundException e) {
         System.out.println("An error occurred.");
         e.printStackTrace();
      }
   }

   public char getConfigChar(int i, int j) {
      return config[i][j];
   }
}
