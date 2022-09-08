import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

public class GeradoraDeFigurinhas {
    
    //private static final Pattern REGEX_ITEMS_IMAGE = Pattern.compile(".\\([@][.])([\S]*)([.][a-z]{3})$");
    
    void cria(InputStream inputStream, String nomeArquivo) throws Exception{
        //leitura da imagem
        //InputStream inputStream = new URL("https://m.media-amazon.com/images/M/MV5BM2MyNjYxNmUtYTAwNi00MTYxLWJmNWYtYzZlODY3ZTk3OTFlXkEyXkFqcGdeQXVyNzkwMjQ5NzM@.jpg").openStream();

        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        //cria nova imagem em memoria com transparência e com tamanho novo
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        //copiar a imagem original para nova imagem (em memória)
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal,  0, 0, null);

        //configurar a fonte
        var fonte = new Font(Font.SANS_SERIF, Font.BOLD, 64);
        graphics.setColor(Color.YELLOW);
        graphics.setFont(fonte);
        //escrever uma frase na nova imagem
        graphics.drawString("TOPZEIRA", 0, novaAltura - 100);
        
        //escrever a nova iamgem em um arquivo
        ImageIO.write(novaImagem, "png", new File("saida/"+nomeArquivo));
    }

}
